package reconnect.server.global.model.entity.mysql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.global.model.enums.Gender;
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
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationStatus status;  // 등록 상태 (심사 완료 등)

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    private Integer height;
    private Integer weight;

    private String photoUrl;  // 얼굴 사진 URL

    @Enumerated(EnumType.STRING)
    private SpecialFeature specialFeature; // 특이사항

    private String personality;

    private String frequentPlace;  // 자주 가는 장소

    @Column(columnDefinition = "TEXT")
    private String additionalInfo; // 기타 특징

    private String familyCertificateUrl;  // 가족관계증명서 URL

    private Long registeredUserSeq;
}