package com.example.demo.servise;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.EmployeeFullInfo;
import com.example.demo.pojo.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PagingEmployeeRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }
/** * GET возвращать информацию о сотруднике с переданным id
 */
@Override
public List<EmployeeFullInfo> getBuIdEmployeeFull(int id) {
    return employeeRepository.buIdEmployeeINfo(id);
}

/** * GET самой высокой зарплатой
 */
    @Override
    public List<Employee> employeeHighSalary(Integer lowBoard) {
        return employeeRepository.employeeUserHighSalary(lowBoard);
    }


/** * GET возвращать информацию о сотруднике с переданным position
 */
    @Override
    public List<EmployeeFullInfo> getBuPositionToEmployee(String role) {
        return employeeRepository.buPositionToEmployee(role);
    }

/**   * GET возвращать полную информацию о сотруднике
 */
    @Override
    public List<EmployeeFullInfo> getFull() {
        return employeeRepository.getFullEmployee();
    }


/** * GET возвращать информацию о сотрудниках на странице.
 */
    @Override
    public List<Employee> getEmployeesPaging(int page, int size) {
        PageRequest employeeOfConcretePage = PageRequest.of(page, size);
        Page<Employee> employeePage = employeeRepository.findAll(employeeOfConcretePage);
        return employeePage.getContent();
    }

}
