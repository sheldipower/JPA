package com.example.demo.servise;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.EmployeeFullInfo;
import com.example.demo.pojo.Employee;
import com.example.demo.pojo.Report;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    /**
     * GET возвращать информацию о сотруднике с переданным id
     */
    List<EmployeeFullInfo> getBuIdEmployee(int id);


    /**
     * GET  возвращать самой высокой зарплатой
     */
    List<Employee> withHighestSalary(Integer salary);

    /**
     * GET возвращать информацию о сотруднике с переданным position
     */
    List<EmployeeFullInfo> getBuIdEmployeePosition(String role);

    /**
     * GET возвращать информацию о сотрудниках на странице.
     */
    List<Employee> getEmployeesPaging(int page, int size);

    /**
     * GET возвращать полную информацию о сотруднике
     */
    List<EmployeeFullInfo> getFull();

    /**
     * POST  принимать на вход файл JSON
     */
    void postJsonFileEmployeeRead(MultipartFile file) throws IOException;
    /**
     * POST формировать  статистикой по отделам:
     */
    int generateReport() ;

    /**
     *GET
     *  находить и возвращать созданный ранее файл в формате JSON по идентификатору.
     */
    Optional<Report> generateReportId(Integer id);

}

