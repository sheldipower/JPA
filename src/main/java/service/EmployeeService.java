package service;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import department.Employee;
import department.Report;
import dto.EmployeeDto;
import exeption.EmployeeNotFoundExeption;
import exeption.EmployeeNotValidExeption;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repository.EmployeeRepository;
import repository.ReportRepository;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ReportRepository reportRepository;
    private final EmployeeMapper employeeMapper;
    private final ObjectMapper objectMapper;

    public EmployeeService(EmployeeRepository employeeRepository,
                           ReportRepository reportRepository,
                           EmployeeMapper employeeMapper,
                           ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.reportRepository = reportRepository;
        this.employeeMapper = employeeMapper;
        this.objectMapper = objectMapper;
    }

//    @PostConstruct
//    public void init() {
//        employeeRepository.deleteAll();
//        employeeRepository.saveAll(
//                List.of(
//                        new Employee("Катя", 90_000),
//                        new Employee("Дима", 102_000),
//                        new Employee("Олег", 80_000),
//                        new Employee("Вика", 165_000),
//                        new Employee("Женя", 175_000)
//                )
//        );
//    }

    public int getSumOfSalaries() {
        return employeeRepository.getSumOfSalaries();
    }

    public EmployeeDto getEmployeeWithMinSalary() {
        Page<EmployeeDto> page = employeeRepository.getEmployeeWithMinSalary(PageRequest.of(0, 1));
        if (page.isEmpty()) {
            return null;
        }
        return page.getContent().get(0);
    }

    public EmployeeDto getEmployeeWithMaxSalary() {
        List<EmployeeDto> employeeWithMaxSalary = getEmployeesWithHighestSalary();
        if (employeeWithMaxSalary.isEmpty()) {
            return null;
        }
        return employeeWithMaxSalary.get(0);
    }

    public List<EmployeeDto> getEmployeeWithSalaryHigherThanAverage() {
        double average = employeeRepository.getAverageOfSalaries();
        return getFindEmployeeSalaryHigherThan(average);
    }

    //  в данном методе сделана валидация (если у сотрудника, которого мы хотим добавить оплата <=0 или имя равно null или не указано, тогда кидается исключение  )
    public List<EmployeeDto> createManyEmployee(List<EmployeeDto> employeeList) {
        Optional<EmployeeDto> incorrectEmployee = employeeList.stream()
                .filter(employee -> employee.getSalary() <= 0 ||
                        employee.getName() == null || employee.getName().isEmpty())
                .findFirst();

        if (incorrectEmployee.isPresent()) {
            throw new EmployeeNotValidExeption(incorrectEmployee.get());
        }
        // Тот кто отправляет запрос на создание id не должен управлять id т.е. менять
        return employeeRepository.saveAll(employeeList.stream()
                        .map(employeeMapper::toEntity)
                        .collect(Collectors.toList()))
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());

    }

    public void update(int id, EmployeeDto employee) {
        Employee oldEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundExeption(id));
        oldEmployee.setSalary(employee.getSalary());
        oldEmployee.setName(employee.getName());
        employeeRepository.save(oldEmployee);
    }

    public EmployeeDto get(int id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .map(employeeDto -> {
                    employeeDto.setPosition(null);
                    return employeeDto;
                })
                .orElseThrow(() -> new EmployeeNotFoundExeption(id));
    }

    public void delete(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundExeption(id));
        employeeRepository.delete(employee);
    }

    public List<EmployeeDto> getFindEmployeeSalaryHigherThan(double salary) {
        return employeeRepository.findEmployeesBySalaryIsGreaterThen(salary);
    }

    public List<EmployeeDto> getEmployeesWithHighestSalary() {
        return employeeRepository.getEmployeeWithMaxSalary();
    }

    public List<EmployeeDto> getEmployees(@Nullable String position) {
        return Optional.ofNullable(position)
                .map(employeeRepository::findEmployeesByPosition_PositionContainingIgnoreCase)
                .orElseGet(employeeRepository::findAll)
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public EmployeeDto getFullInfo(int id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new EmployeeNotFoundExeption(id));
    }

    public List<EmployeeDto> getEmployeesFromPage(int page) {
        return employeeRepository.findAll(PageRequest.of(page, 10)).stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public int generateReport() {
        var report = employeeRepository.buildReport();
        try {

            var content = objectMapper.writeValueAsString(report);
            var path = generateReportFile(content);

            var reportEntity = new Report();
            reportEntity.setReport(content);
            reportEntity.setPath(path);
            return reportRepository.save(reportEntity).getId();
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Cannot generate report", e);
        }
    }

    public String generateReportFile(String content){
        var f = new File("report_" + System.currentTimeMillis() + ".json");
        try (var writer = new FileWriter(f)) {
            writer.write(content);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot generate report file", e);
        }
        return f.getName();
    }

    public Resource findReport(int id) {
        return new ByteArrayResource(
                reportRepository.findById(id)
                        .orElseThrow(() -> new IllegalStateException("Report with id " + id + " not found"))
                        .getReport()
                        .getBytes(StandardCharsets.UTF_8)
        );
    }

    public void upload(MultipartFile file) {
        try {
            List<EmployeeDto> dtos = objectMapper.readValue(file.getBytes(), new TypeReference<>() {
            });
            dtos.stream()
                    .map(employeeMapper::toEntity)
                    .forEach(employeeRepository::save);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // метод, который возвращает нам файл
    public File findReportFile(int id) {
        return reportRepository.findById(id)
                .map(Report::getPath)
                .map(File::new)
                .orElse(null);
    }
}
