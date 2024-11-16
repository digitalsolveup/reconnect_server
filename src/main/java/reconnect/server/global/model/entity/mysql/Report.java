package reconnect.server.global.model.entity.mysql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.global.model.enums.*;

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
    private Long reportId; // 제보 id

    @ManyToOne
    @JoinColumn(name = "missing_person_id", nullable = false)
    private MissingPerson missingPerson; // 실종자 id (연관 관계)

    @Enumerated(EnumType.STRING)
    private Gender gender; // (enum) 성별

    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup; // (enum) 나이 그룹

    @Enumerated(EnumType.STRING)
    private SpecialFeature specialFeature; // (enum) 특이사항

    private boolean tops; // 상의 일치 여부
    private boolean bottoms; // 하의 일치 여부
    private boolean shoes; // 신발 일치 여부
    private boolean accessories; // 악세사리 일치 여부
    private boolean hair; // 두발 일치 여부

    @ElementCollection
    private List<String> foundImageUrls; // 제보 사진 URL들

    private String locationFound; // 발견 위치
    private double foundLatitude; // 발견 위도
    private double foundLongitude; // 발견 경도

    private String additionalDescription; // 추가 설명

    @ElementCollection
    private List<String> surroundingImageUrls; // 주변 사진 URL들

    private String additionalReport; // 추가 제보 내용

    private LocalDateTime reportedAt; // 제보 일시
}
