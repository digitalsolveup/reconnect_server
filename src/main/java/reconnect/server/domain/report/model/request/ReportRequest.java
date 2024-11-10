package reconnect.server.domain.report.model.request;

import lombok.Data;
import reconnect.server.global.model.enums.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReportRequest {
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
}
