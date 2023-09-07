package service;

import dto.ReportDTO;
import model.ReportEntity;
import org.springframework.stereotype.Service;
import repository.ReportRepository;

@Service
public class ReportSaverServiceToDBImpl implements ReportSaverService {
    private final ReportRepository reportRepository;

    public ReportSaverServiceToDBImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public ReportDTO saveReport(ReportDTO reportDTO) {
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setFilePath(reportDTO.getPath());
        ReportEntity report = reportRepository.save(reportEntity);
        return ReportDTO.builder()
                .path(reportDTO.getReport())
                .path(report.getFilePath())
                .build();
    }
}
