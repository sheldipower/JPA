package service;

import dto.ReportDTO;
import mappers.ReportMapper;
import model.DepartmentEntity;
import model.ReportEntity;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import repository.ReportRepository;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final DepartmentService departmentService;
    private final ReportSaverService reportSaverServiceToJson;
    private final ReportSaverService reportSaverServiceToDB;
    private final ReportMapper reportMapper;
    Logger logger = LoggerFactory.getLogger(ReportService.class);

    public ReportService(ReportRepository reportRepository, DepartmentService departmentService,
                         @Qualifier("reportSaverServiceToJsonImpl") ReportSaverService reportSaverServiceToJson,
                         @Qualifier("reportSaverServiceToDBImpl") ReportSaverService reportSaverServiceToDB,
                         ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.departmentService = departmentService;
        this.reportSaverServiceToJson = reportSaverServiceToJson;
        this.reportSaverServiceToDB = reportSaverServiceToDB;
        this.reportMapper = reportMapper;
    }

    public int saveReportToJsonAndDB() {
        logger.info("The method of saving the report in json format in the database was called");
        ReportDTO report = createReport();
        reportSaverServiceToJson.saveReport(report);
        ReportDTO saveReport = reportSaverServiceToDB.saveReport(report);
        logger.debug("The file was saved successfully");
        return saveReport.getId();
    }

    public ReportDTO createReport() {
        logger.info("The report creation method was called");
        List<DepartmentEntity> departmentEntityList = departmentService.    getAllDepartmentEntity();
        ReportDTO reportDTO = new ReportDTO();
        StringBuilder stringBuilder = new StringBuilder();
        departmentEntityList.forEach(departmentEntity -> {
            String nameOfDepartment = departmentEntity.getName();
            Integer amountOfEmployees = departmentService.getAmountEmployeeInDepartment(departmentEntity.getId());
            Double minSalary = departmentService.getMinimumSalaryEmployeeInDepartment(departmentEntity.getId());
            Double avgSalary = departmentService.getEmployeesSalaryAboveAverageInDepartment(departmentEntity.getId());
            Double maxSalary = departmentService.getMaximumSalaryEmployeeInDepartment(departmentEntity.getId());
            stringBuilder.append(String.format("Department: %s\n",nameOfDepartment));
            stringBuilder.append(String.format("Amount of employees: %s\n",amountOfEmployees));
            stringBuilder.append(String.format("Minimum salary: %s\n",minSalary));
            stringBuilder.append(String.format("Average salary: %s\n",avgSalary));
            stringBuilder.append(String.format("Maximum salary: %s\n",maxSalary));
        });
        reportDTO.setReport(stringBuilder.toString());
        logger.debug("The report was created successfully");
        return  reportDTO;
    }

    public ReportEntity getReportById(int id) {
        logger.info("The method of searching for the report by id was called" + id);
        ReportEntity report = reportRepository.readReportById(id);
        logger.debug("Report found successfully" + id);
        return report;
    }
}
