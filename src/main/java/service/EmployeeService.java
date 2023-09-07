package service;

import dto.EmployeeDTO;
import mappers.EmployeeMapper;
import model.EmployeeEntity;
import org.apache.el.stream.Optional;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import repository.EmployeeRepository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeDTO createEmployee(EmployeeEntity employee) {
        logger.info("Was invoked method for create employee" + employee);
        EmployeeEntity saveEmployee = employeeRepository.save(employee);
        EmployeeDTO employeeDTO = employeeMapper.toDto(saveEmployee);
        logger.debug("Employee successfully created" + employee);
        return employeeDTO;
    }

    public EmployeeDTO getInformationForEmployee(int id) {
        logger.info("The method of getting information about the employee was called" + id);
        EmployeeDTO employeeDTO = employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> { RuntimeException re = new RuntimeException();
                    logger.error("Employee information found" + re);
                    return re;});
        logger.debug("Employee information found" + id);
        return employeeDTO;
    }

    public EmployeeDTO updateEmployeeById(int id, EmployeeEntity employee) {
        logger.info("Was invoked method for update employee {} by id {}", employee, id);
        EmployeeDTO findEmployee = getInformationForEmployee(id);
        EmployeeEntity employeeEntity = employeeMapper.toEntity(findEmployee);
        employeeEntity.setName(employee.getName());
        employeeEntity.setSalary(employee.getSalary());
        employeeEntity.setPosition(employee.getPosition());
        EmployeeEntity employeeSave = employeeRepository.save(employee);
        EmployeeDTO employeeDTO = employeeMapper.toDto(employeeSave);
        logger.debug("Employee {} changed by id {}", employee, id);
        return employeeDTO;
    }

    public List<EmployeeDTO> getAllEmployee() {
        logger.info("The method of getting a list of all employees was called");
        List<EmployeeDTO> employeeDTOS = employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
        logger.debug("A list of all employees has been created");
        return employeeDTOS;
    }

    public void deleteById(int id) {
        logger.info("The method for deleting an employee was called by id = " + id);
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> { RuntimeException re = new RuntimeException();
                    logger.info("The employee with this id was not found" + re);
                    return re;});
        logger.error("The employee with this id was not found" + id);
        employeeRepository.delete(employee);
        logger.debug("The employee with this id has been deleted" + id);
    }

    public Collection<EmployeeDTO> getEmployeesBySalaryGreaterThan(double salary) {
        logger.info("The method of getting an list of employees with a salary of more than was called" + salary);
        Collection<EmployeeDTO> employeeDTOS = employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .filter(employee -> employee.getSalary() >= salary)
                .collect(Collectors.toList());
        logger.debug("The list of employees with a salary of more than created" + employeeDTOS);
        return employeeDTOS;

    }

    public List<EmployeeDTO> getEmployeeByPosition(Optional position) {
        logger.info("The method of searching for an employee by position was called" + position);
        List<EmployeeDTO> employeeDTOS = employeeRepository.getEmployeesByName(position).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
        logger.debug("Employee found" + position);
        return employeeDTOS;

    }

    public List<EmployeeEntity> getEmployeesByPage(int number) {
        logger.info("The method of searching for employees by page was called" + number);
        int pageSize = 10;
        Pageable pageable = PageRequest.of(number, pageSize);
        Page<EmployeeEntity> employeeDTOPage = employeeRepository.findAll(pageable);
        List<EmployeeEntity> employeeEntityList = employeeDTOPage.stream()
                .toList();
        logger.debug("Page with employees found" + number);
        return  employeeEntityList;
    }

}