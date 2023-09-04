package repository;

import department.Employee;
import dto.EmployeeDto;
import dto.ReportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT SUM(e.salary) FROM Employee e")
    int getSumOfSalaries();

    @Query("SELECT AVG(e.salary) FROM Employee e")
    double getAverageOfSalaries();

    //ниже приведен пример переноса строк "Текстовые блоки" без использования плюсов как при конкатенации в двух последних
    @Query("""
    SELECT new ru.skypro.workingwithfiles2.dto.EmployeeDto(e.id, e.name, e.salary, e.position.position) 
    FROM Employee e 
    WHERE e.salary = (SELECT MIN(e.salary) FROM Employee e)
    """)
    Page<EmployeeDto> getEmployeeWithMinSalary(Pageable pageable);

    @Query("""
    SELECT new ru.skypro.workingwithfiles2.dto.EmployeeDto(e.id, e.name, e.salary, e.position.position)
    FROM Employee e 
    WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)
    """)
    List<EmployeeDto> getEmployeeWithMaxSalary();

    @Query("SELECT new ru.skypro.workingwithfiles2.dto.EmployeeDto(e.id, e.name, e.salary, p.position) " +
            "FROM Employee e LEFT JOIN FETCH Position p WHERE e.salary > :salary")
    List<EmployeeDto> findEmployeesBySalaryIsGreaterThen(double salary);

    List<Employee> findEmployeesByPosition_PositionContainingIgnoreCase(String position);

    @Query("""
    SELECT new ru.skypro.workingwithfiles2.dto.ReportDto
    (e.position.position, count(e.id), max(e.salary), min(e.salary), avg(e.salary)) FROM Employee e
    GROUP BY e.position.position
    """)
    List<ReportDto> buildReport();
}
