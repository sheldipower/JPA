package mappers;

import dto.EmployeeDTO;
import model.EmployeeEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDTO toDto(EmployeeEntity employee);
    EmployeeEntity toEntity(EmployeeDTO dto);
    List<EmployeeDTO> toDto(List<EmployeeEntity> employees);
    List<EmployeeEntity> toEntity(List<EmployeeDTO> dtos);
}
