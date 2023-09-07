package dto;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private String name;
    private EmployeeDTO employeeDTO;
}
