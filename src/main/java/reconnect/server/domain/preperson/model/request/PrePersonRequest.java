package reconnect.server.domain.preperson.model.request;

import lombok.Data;
import reconnect.server.global.model.entity.mysql.PrePerson;
import reconnect.server.global.model.enums.Gender;
import reconnect.server.global.model.enums.Personality;
import reconnect.server.global.model.enums.RegistrationStatus;
import reconnect.server.global.model.enums.SpecialFeature;

import java.time.LocalDate;

@Data
public class PrePersonRequest {
    private String name;                 // 5-1) 이름
    private Gender gender;               // 5-1) 성별
    private LocalDate birthDate;         // 5-1) 생년월일

    private Integer height;              // 5-2) 키
    private Integer weight;              // 5-2) 몸무게

    private String photoUrl;             // 5-3) 얼굴 사진 URL

    private SpecialFeature specialFeature; // 5-4) 특이사항
    private Personality personality;       // 5-5) 성격

    // 5-6) 평소 자주 가는 장소
    private double lastSeenLatitude; // 위도
    private double lastSeenLongitude; // 경도

    private String additionalInfo;       // 5-7) 추가 특징
    private String familyCertificateUrl; // 5-8) 가족관계증명서 URL (선택 사항)

    // PrePerson 엔티티로 변환
    public PrePerson toEntity() {
        return PrePerson.builder()
                .name(this.name)
                .gender(this.gender)
                .birthDate(this.birthDate)
                .height(this.height)
                .weight(this.weight)
                .photoUrl(this.photoUrl)
                .specialFeature(this.specialFeature)
                .personality(this.personality)
                .lastSeenLatitude(this.lastSeenLatitude)
                .lastSeenLongitude(this.lastSeenLongitude)
                .additionalInfo(this.additionalInfo)
                .familyCertificateUrl(this.familyCertificateUrl)
                .build();
    }
}