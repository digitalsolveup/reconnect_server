package reconnect.server.domain.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reconnect.server.domain.report.model.request.ReportRequest;
import reconnect.server.domain.report.model.response.ReportResponse;
import reconnect.server.domain.report.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ReportResponse createReport(@RequestBody ReportRequest reportRequest) {
        return reportService.createReport(reportRequest);
    }
}
