package com.example.demo.servise;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.EmployeeFullInfo;
import com.example.demo.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * GET возвращать информацию о сотруднике с переданным id
     */
    List<EmployeeFullInfo> getBuIdEmployeeFull(int id);
/**
 * GET  возвращать самой высокой зарплатой
 */
List<Employee> employeeHighSalary (Integer salary);
/**
 * GET возвращать информацию о сотруднике с переданным position
      */
List<EmployeeFullInfo>getBuPositionToEmployee(String role);
    /**
     * GET возвращать информацию о сотрудниках на странице.
     */
    List<Employee> getEmployeesPaging( int page, int size);

    List<EmployeeFullInfo> getFull();

    List<EmployeeDTO> getAllEmployees();
}

