package servise;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import repository.EmployeeRepository;
import repository.PagingEmployeeRepository;

import java.awt.print.Pageable;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private final PagingEmployeeRepository pagingEmployeeRepository;
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
        Pageable employeeOfConcretePage = PageRequest.of(page, size);
        Page<Employee> employeePage = pagingEmployeeRepository.findAll(employeeOfConcretePage);
        return employeePage.stream().toList();
    }

}
