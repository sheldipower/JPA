package mappers;

import dto.PositionDTO;
import model.PositionEntity;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);
    PositionDTO toPositionDTO(PositionEntity position);
}