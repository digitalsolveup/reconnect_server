package reconnect.server.domain.report.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reconnect.server.domain.report.model.request.ReportRequest;
import reconnect.server.domain.report.model.response.ReportResponse;
import reconnect.server.domain.report.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "Report", description = "제보 관련 컨트롤러")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // 제보 등록
    @Operation(summary = "제보 등록", description = """
            한 실종자에 해당하는 제보를 추가한다.
            """)
    @PostMapping
    public ResponseEntity<ReportResponse> createReport(@RequestBody ReportRequest reportRequest) {
        return ResponseEntity.ok(reportService.createReport(reportRequest));
    }

    // 제보 수정
    @Operation(summary = "제보 수정", description = """
            한 실종자에 해당하는 제보를 수정한다.
            """)
    @PutMapping("/{id}")
    public ResponseEntity<ReportResponse> updateReport(
            @PathVariable("id") Long reportId,
            @RequestBody ReportRequest reportRequest) {
        return ResponseEntity.ok(reportService.updateReport(reportId, reportRequest));
    }

    // 나의 제보 목록 조회
    @Operation(summary = "제보 목록 조회", description = """
            내가 제보한 제보 목록을 조회한다.
            """)
    @GetMapping
    public ResponseEntity<List<ReportResponse>> getMyReports() {
        return ResponseEntity.ok(reportService.getMyReports());
    }
}
