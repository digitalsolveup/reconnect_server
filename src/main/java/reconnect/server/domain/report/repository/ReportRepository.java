package reconnect.server.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reconnect.server.global.model.entity.mysql.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByMissingPersonId(Long missingPersonId);
}