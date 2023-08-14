package servise;

import dto.EmployeeDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import repository.EmployeeRepository;
import repository.PagingEmployeeRepository;

import java.awt.print.Pageable;
import java.util.stream.Collectors;

@Service

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAllEmployees().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PagingEmployeeRepository pagingEmployeeRepository) {
        this.employeeRepository = employeeRepository;
        this.pagingEmployeeRepository = pagingEmployeeRepository;
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
        Pageable employeeOfConcretePage = PageRequest.of (pageIndex, unitPerPage);
        Page<Employee> employeePage = PagingEmployeeRepository.findAll (employeeOfConcretePage);
        return page.stream()
                .toList();
    }

}
