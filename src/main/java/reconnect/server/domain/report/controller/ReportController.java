package reconnect.server.domain.report.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reconnect.server.global.model.entity.mysql.Report;
import reconnect.server.domain.report.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/missing-person/{id}")
    public List<Report> getReportsByMissingPerson(@PathVariable Long id) {
        return reportService.getReportsByMissingPerson(id);
    }

    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.createReport(report);
    }
}