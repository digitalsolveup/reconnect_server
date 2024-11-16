package reconnect.server.domain.report.model.response;

import lombok.Data;
import reconnect.server.global.model.entity.mysql.Report;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReportResponse {
    private Long reportId;
    private Long missingPersonId;
    private String missingPersonName;
    private LocalDateTime reportedAt;
    private List<String> foundImageUrls;

    public ReportResponse(Report report) {
        this.reportId = report.getReportId();
        this.missingPersonId = report.getMissingPerson().getId();
        this.missingPersonName = report.getMissingPerson().getName();
        this.reportedAt = report.getReportedAt();
        this.foundImageUrls = report.getFoundImageUrls();
    }
}
