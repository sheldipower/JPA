package repository;

import dto.EmployeeFullInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pojo.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository <Employee, Integer> {

    /**
     * GET самой высокой зарплатой
     */
    @Query(value = "SELECT * FROM employee WHERE salary > :lowBoard",
            nativeQuery = true)
    List<Employee> employeeUserHighSalary(@Param("lowBoard") Integer lowBoard);

    /**
     * GET возвращать полную информацию о сотруднике
     */

    @Query("SELECT new ru.skypro.lessons.springboot.springweb.dto. " +
            "EmployeeFullInfo(e.id,e.name , e.salary , p.role) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p")
    List<EmployeeFullInfo> getFullEmployee();

    /**
     * GET возвращать информацию о сотруднике с переданным position
     */

    @Query("SELECT new ru.skypro.lessons.springboot.springweb.dto." +
            "EmployeeFullInfo(e.id,e.name , e.salary , p.role) " +
            "FROM Employee e JOIN FETCH Position p " +
            "WHERE e.position= p AND p.role = :role")
    List<EmployeeFullInfo> buPositionToEmployee(String role);

    /**
     * GET возвращать информацию о сотруднике с переданным id
     */
    @Query("SELECT new ru.skypro.lessons.springboot.springweb.dto." +
            "EmployeeFullInfo(e.id,e.name , e.salary , p.role) " +
            "FROM Employee e  JOIN FETCH Position p " +
            "WHERE e.position = p AND e.id = :id")
    List<EmployeeFullInfo> buIdEmployeeINfo(int id);
}