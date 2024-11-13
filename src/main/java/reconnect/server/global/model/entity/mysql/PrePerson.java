package reconnect.server.global.model.entity.mysql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.global.model.enums.Gender;
import reconnect.server.global.model.enums.Personality;
import reconnect.server.global.model.enums.SpecialFeature;
import reconnect.server.global.model.enums.RegistrationStatus;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pre_person")
public class PrePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 사전 실종자 고유 id

    @Column(nullable = false)
    private String name; // 사전 실종자 이름

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationStatus status;  // (enum) 사전 등록 상태  - PENDING (미완료), COMPLETED(등록 완료), UNDER_REVIEW(심사중)

    @Enumerated(EnumType.STRING)
    private Gender gender; // (enum) 성별 - MALE(남자), FEMALE(여자)

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate; // 생년월일 yyyy-mm-dd

    private Integer height; // 키
    private Integer weight; // 몸무게

    private String photoUrl;  // 얼굴 사진 URL

    @Enumerated(EnumType.STRING)
    private SpecialFeature specialFeature; // (enum) 특이사항 - NONE(없음), NON_DISABLED_CHILD(비장애아동), DISABILITY(장애), DEMENTIA(치매), RUNAWAY(가출인), OTHER(기타)

    @Enumerated(EnumType.STRING)
    private Personality personality; // (enum) 성격 - INTROVERTED(내성적인), EXTROVERTED(외향적인), ACTIVE(활발한), CALM(차분한), AGGRESSIVE(공격적인), QUIET(조용한), TALKATIVE(말이 많은)

    // 자주 가는 장소
    private double lastSeenLatitude; // 위도
    private double lastSeenLongitude; // 경도

    @Column(columnDefinition = "TEXT")
    private String additionalInfo; // 기타 특징

    private String familyCertificateUrl;  // 가족관계증명서 URL
}