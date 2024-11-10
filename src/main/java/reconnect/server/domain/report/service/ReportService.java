package reconnect.server.domain.report.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reconnect.server.global.model.entity.mysql.Report;
import reconnect.server.domain.report.repository.ReportRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public List<Report> getReportsByMissingPerson(Long missingPersonId) {
        return reportRepository.findByMissingPersonId(missingPersonId);
    }

    public Report createReport(Report report) {
        return reportRepository.save(report);
    }
}