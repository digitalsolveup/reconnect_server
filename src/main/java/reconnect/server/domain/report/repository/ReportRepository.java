package reconnect.server.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reconnect.server.global.model.entity.mysql.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
    // 기본 CRUD 및 필요한 쿼리 메소드 정의
}
