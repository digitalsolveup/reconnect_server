package reconnect.server.domain.report.model.response;

import lombok.Data;
import reconnect.server.global.model.enums.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReportResponse {
    private Long reportId;
    private Long missingPersonId;
    private ReportGender gender;
    private AgeGroup ageGroup;
    private ReportSpecialFeature specialFeature;
    private List<String> clothingItems;
    private String foundImageUrl;
    private String locationFound;
    private String additionalDescription;
    private String surroundingImageUrl;
    private String additionalReport;
    private LocalDateTime reportedAt;
    private int reportCount;
}
