package service;

import dto.ReportDTO;
import dto.ReportToFileJsonDTO;
import org.springframework.stereotype.Service;

@Service
public interface ReportReaderService {
    ReportToFileJsonDTO readerReport (ReportDTO reportDTO);
}