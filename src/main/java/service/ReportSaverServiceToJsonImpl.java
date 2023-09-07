package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dto.ReportDTO;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

@Service
public class ReportSaverServiceToJsonImpl implements ReportSaverService{
    Logger logger = LoggerFactory.getLogger(ReportSaverServiceToJsonImpl.class);
    @Override
    public ReportDTO saveReport(ReportDTO reportDTO) {
        logger.info("The report creation method was called" + reportDTO);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            File file = new File("report.json");
            objectMapper.writeValue(file,reportDTO);
            String folderPath = file.getAbsolutePath();
            reportDTO.setPath(folderPath);
            logger.debug("The data has been successfully saved to a file" + reportDTO);
        } catch (IOException e){
            logger.error("Error saving JSON data" + e);
            e.getMessage();
        }
        return reportDTO;
    }


}