package reconnect.server.domain.preperson.model.response;

import lombok.Data;
import reconnect.server.global.model.entity.mysql.PrePerson;
import reconnect.server.global.model.enums.Gender;
import reconnect.server.global.model.enums.Personality;
import reconnect.server.global.model.enums.RegistrationStatus;
import reconnect.server.global.model.enums.SpecialFeature;

import java.time.LocalDate;

// PrePersonDetailResponse.java
@Data
public class PrePersonDetailResponse {
    private Long id;
    private String name;
    private Gender gender;
    private LocalDate birthDate;
    private Integer height;
    private Integer weight;
    private String photoUrl;
    private SpecialFeature specialFeature;
    private Personality personality;
    private double lastSeenLatitude;
    private double lastSeenLongitude;
    private String additionalInfo;
    private String familyCertificateUrl;
    private RegistrationStatus status;

    // 생성자
    public PrePersonDetailResponse(PrePerson prePerson) {
        this.id = prePerson.getId();
        this.name = prePerson.getName();
        this.gender = prePerson.getGender();
        this.birthDate = prePerson.getBirthDate();
        this.height = prePerson.getHeight();
        this.weight = prePerson.getWeight();
        this.photoUrl = prePerson.getPhotoUrl();
        this.specialFeature = prePerson.getSpecialFeature();
        this.personality = prePerson.getPersonality();
        this.lastSeenLatitude = prePerson.getLastSeenLatitude();
        this.lastSeenLongitude = prePerson.getLastSeenLongitude();
        this.additionalInfo = prePerson.getAdditionalInfo();
        this.familyCertificateUrl = prePerson.getFamilyCertificateUrl();
        this.status = prePerson.getStatus();
    }
}

