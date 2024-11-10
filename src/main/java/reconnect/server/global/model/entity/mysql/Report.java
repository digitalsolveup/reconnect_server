package reconnect.server.global.model.entity.mysql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.global.model.enums.AgeGroup;
import reconnect.server.global.model.enums.ReportGender;
import reconnect.server.global.model.enums.ReportSpecialFeature;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @ManyToOne
    @JoinColumn(name = "missing_person_id", nullable = false)
    private MissingPerson missingPerson;

    @Enumerated(EnumType.STRING)
    private ReportGender gender;

    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;

    @Enumerated(EnumType.STRING)
    private ReportSpecialFeature specialFeature;

    @ElementCollection
    private List<String> clothingItems;

    private String foundImageUrl;
    private String locationFound;
    private String additionalDescription;
    private String surroundingImageUrl;
    private String additionalReport;
    private LocalDateTime reportedAt;
}