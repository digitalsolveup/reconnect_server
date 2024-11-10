package reconnect.server.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reconnect.server.domain.report.model.entity.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByMissingPersonId(Long missingPersonId);
}