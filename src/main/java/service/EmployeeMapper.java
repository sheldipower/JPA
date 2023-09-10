package service;

import department.Employee;
import department.Position;
import dto.EmployeeDto;
import dto.PositionDto;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setSalary(employeeDto.getSalary());

        var positionDto = employeeDto.getPosition();
        var position = new Position();
        position.setId(positionDto.getId());
        position.setPosition(positionDto.getName());
        employee.setPosition(position);
        return employee;
    }

    public EmployeeDto toDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setSalary(employee.getSalary());

        var position = employee.getPosition();
        var positionDto = new PositionDto(position.getId(), position.getPosition());

        employeeDto.setPosition(positionDto);
        return employeeDto;
    }
}
