package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.EmployeeEntity;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String name;
    private double salary;
    private PositionDTO position;
    private DepartmentDTO dto;
    List<EmployeeEntity> employees;
    public static EmployeeDTO fromEmployee(EmployeeEntity employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        return employeeDTO;
    }
}
