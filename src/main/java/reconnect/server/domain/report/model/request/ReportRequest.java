package reconnect.server.domain.report.model.request;

import lombok.Data;
import reconnect.server.global.model.enums.*;

import java.util.List;

@Data
public class ReportRequest {
    private Long missingPersonId;
    private Gender gender;
    private AgeGroup ageGroup;
    private SpecialFeature specialFeature;
    private boolean tops;
    private boolean bottoms;
    private boolean shoes;
    private boolean accessories;
    private boolean hair;
    private List<String> foundImageUrls;
    private String locationFound;
    private double foundLatitude;
    private double foundLongitude;
    private String additionalDescription;
    private List<String> surroundingImageUrls;
    private String additionalReport;
}
