package repository;

import department.Report;
import model.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends CrudRepository<ReportEntity, Integer> {
    @Query("SELECT r.filePath FROM ReportEntity r WHERE r.id =:id")
    ReportEntity readReportById(int id);
}