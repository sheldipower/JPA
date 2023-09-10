package mappers;

import dto.ReportDTO;
import model.ReportEntity;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    ReportDTO toReportDto(ReportEntity reportEntity);
    ReportEntity toEntity(ReportDTO reportDTO);
}
