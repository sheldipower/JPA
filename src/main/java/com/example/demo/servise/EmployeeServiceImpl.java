package com.example.demo.servise;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.EmployeeFullInfo;
import com.example.demo.dto.ReportDTO;
import com.example.demo.pojo.Employee;
import com.example.demo.pojo.Report;
import com.example.demo.repository.ReportRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PagingEmployeeRepository;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PagingEmployeeRepository pagingEmployeeRepository;
    private final EmployeeMaper employeeMaper;
    private final ObjectMapper objectMapper;
    private final ReportRepository reportRepository;


    /**
     * GET возвращать информацию о сотруднике с переданным id
     */

    @Override
    public List<EmployeeFullInfo> getBuIdEmployee(int id) {
        return employeeRepository.buIdEmployeeINfo(id);
    }

    /**
     * GET самой высокой зарплатой
     */
    @Override
    public List<Employee> withHighestSalary(Integer lowBoard) {
        return employeeRepository.employeeUserHighSalary(lowBoard);
    }


    /**
     * GET возвращать информацию о сотруднике с переданным position
     */
    @Override
    public List<EmployeeFullInfo> getBuIdEmployeePosition(String role) {
        return employeeRepository.buPositionToEmployee(role);
    }

    /**
     * GET возвращать полную информацию о сотруднике
     */
    @Override
    public List<EmployeeFullInfo> getFull() {

        return employeeRepository.getFullEmployee();
    }


    /**
     * GET возвращать информацию о сотрудниках на странице.
     */
    @Override
    public List<Employee> getEmployeesPaging(int page, int size) {
        Pageable employeeOfConcretePage = PageRequest.of(page, size);
        Page<Employee> employeePage = pagingEmployeeRepository.findAll(employeeOfConcretePage);
        return employeePage.stream().toList();
    }

    /**
     * POST  принимать на вход файл JSON,
     * Все сотрудники из загружаемого файла должны быть сохранены в базе данных.
     */


    @Override
    public void postJsonFileEmployeeRead(MultipartFile file) {
        writeToFile(file);
        String filePath = "test.json";
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<EmployeeDTO> listEmployeeDto = objectMapper.readValue(Paths.get(filePath).toFile(), new TypeReference<>() {
            });
            System.out.println(listEmployeeDto);
            listEmployeeDto.stream()
                    .map(employeeMaper::toEntity)
                    .forEach(employeeRepository::save);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * POST формировать  статистикой по отделам:
     */
    public int generateReport() {
        List<ReportDTO> report = reportRepository.buildReport();
        try {
            String statistic = objectMapper.writeValueAsString(report);

            Report reportEntity = new Report();
            reportEntity.setData(statistic);
            return reportRepository.save(reportEntity).getReportId();
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Error", e);
        }

    }

    /**
     * GET
     * находить и возвращать созданный ранее файл в формате JSON по идентификатору.
     */
    @Override
    public Optional<Report> generateReportId(Integer id) {
        Optional<Report> reportsfindById = reportRepository.findById(id);
        return reportsfindById;


    }
}