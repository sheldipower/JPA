package com.example.demo.controller;


import dto.EmployeeDto;
import org.apache.el.stream.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.EmployeeService;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/salary/sum")
    public int getSumOfSalaries() {
        return employeeService.getSumOfSalaries();
    }
    @GetMapping("/salary/min")
    public EmployeeDto getEmployeeWithMinSalary() {
        return employeeService.getEmployeeWithMinSalary();
    }
    @GetMapping("/salary/max")
    public EmployeeDto getEmployeeWithMaxSalary() {
        return employeeService.getEmployeeWithMaxSalary();
    }

    @GetMapping("/high-salary")
    public List<EmployeeDto> getEmployeeWithSalaryHigherThanAverage() {
        return employeeService.getEmployeeWithSalaryHigherThanAverage();
    }

    // Создание множества новых сотрудников
    @PostMapping
    public List<EmployeeDto> createManyEmployee(@RequestBody List<EmployeeDto> employeeList){
        return employeeService.createManyEmployee(employeeList);
    }
    // Редактирование сотрудника с указанным id;
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody EmployeeDto employee){
        employeeService.update(id, employee);
    }
    //Возвращение информации о сотруднике с переданным id;
    @GetMapping("/{id}")
    public EmployeeDto get(@PathVariable int id) {
        return employeeService.get(id);
    }
    //Удаление сотрудника с переданным id.
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        employeeService.delete(id);
    }
    //Метод возвращения всех сотрудников, зарплата которых выше переданного параметра salary.
    @GetMapping("/salaryHigherThan")
    public List<EmployeeDto> getFindEmployeeSalaryHigherThan(@RequestParam int salary) {
        return employeeService.getFindEmployeeSalaryHigherThan(salary);
    }
    // Возвращение информации о сотрудниках с самой высокой зарплатой в фирме;
    @GetMapping("/withHighestSalary")
    public List<EmployeeDto> getEmployeesWithHighestSalary() {
        return employeeService.getEmployeesWithHighestSalary();
    }
    @GetMapping
    public List<EmployeeDto> getEmployees(@RequestParam(required = false) String position) {
        return employeeService.getEmployees(
                Optional.ofNullable(position)
                        .filter(pos -> !pos.isEmpty())
                        .orElse(null)
        );
    }
    //Метод возвращающий полную информацию о сотруднике (имя, зарплата, название должности) с переданным в пути запроса идентификатором.
    @GetMapping("/{id}/fullInfo")
    public EmployeeDto getFullInfo(@PathVariable int id) {
        return employeeService.getFullInfo(id);
    }
    //Метод возвращающий информацию о сотрудниках, основываясь на номере страницы.
    // Если страница не указана, то возвращается первая страница.
    // Номера страниц начинаются с 0. Лимит на количество сотрудников на странице — 10 человек.
    @GetMapping("page")
    public List<EmployeeDto> getEmployeesFromPage(@RequestParam(required = false, defaultValue = "0") int page) {
        return employeeService.getEmployeesFromPage(page);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestPart("employees") MultipartFile file) {
        employeeService.upload(file);
    }


}
