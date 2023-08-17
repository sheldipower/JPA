package com.example.demo.controller;

import com.example.demo.servise.EmployeeService;
import com.example.demo.dto.EmployeeFullInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.pojo.Employee;

import java.util.List;

@RestController
@RequestMapping ("/employees")
public class EmployeeController {
    private static EmployeeService employeeService;
    public EmployeeController (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    /** * GET  возвращать самой высокой зарплатой
     */
    @GetMapping("withHighestSalary")
    public List<Employee> withHighestSalary (@RequestParam(value = "salary", required = false) Integer salary) {
        return employeeService.employeeHighSalary(salary);
    }

/*** GET возвращать информацию о сотруднике с переданным position
 */  @GetMapping(params = "position")
public List<EmployeeFullInfo> getBuIdEmployeePosition(@RequestParam("position") String position) {
    try { return employeeService.getBuPositionToEmployee(position);
    } catch (Throwable t) {
        String message = "Нет такого position";
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }
}

/** * GET возвращать полную информацию о сотруднике
 */
@GetMapping("/full")
public List<EmployeeFullInfo> getFull() {
    return employeeService.getFull();
}

/** * GET возвращать информацию о сотруднике с переданным id
      */
@GetMapping("/{id}/fullinfo")

public List<EmployeeFullInfo> getBuIdEmployee(@PathVariable int id) {
    try { return employeeService.getBuIdEmployeeFull(id);
    } catch (Throwable t) {
        String message = "Нет такого id"; throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }
}

/*** GET возвращать информацию о сотрудниках на странице.
 */
   @GetMapping("/{page}")
public List<Employee> getEmployeesPaging(@PathVariable int page) {
    int size = 2;
    return employeeService.getEmployeesPaging(page, size);
}

}
