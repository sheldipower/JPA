package mappers;

import dto.DepartmentDTO;
import model.DepartmentEntity;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDTO toDepDto (DepartmentEntity department);
    DepartmentEntity toDepEntity (DepartmentDTO depDto);
}
