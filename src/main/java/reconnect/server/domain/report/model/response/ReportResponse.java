package reconnect.server.domain.report.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.global.model.entity.mysql.MissingPerson;
import reconnect.server.global.model.enums.AgeGroup;
import reconnect.server.global.model.enums.ReportGender;
import reconnect.server.global.model.enums.ReportSpecialFeature;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {

    private Long reportId;  // 제보 ID
    private MissingPerson missingPerson;  // 연관된 실종자
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