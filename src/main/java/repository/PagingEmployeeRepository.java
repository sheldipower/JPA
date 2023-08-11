package repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pojo.Employee;

public interface PagingEmployeeRepository extends PagingAndSortingRepository<Employee,Integer> {

}
