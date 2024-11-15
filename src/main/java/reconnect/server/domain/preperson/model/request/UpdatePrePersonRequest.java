package reconnect.server.domain.preperson.model.request;

import lombok.Data;
import reconnect.server.global.model.enums.Gender;
import reconnect.server.global.model.enums.Personality;
import reconnect.server.global.model.enums.RegistrationStatus;
import reconnect.server.global.model.enums.SpecialFeature;

import java.time.LocalDate;

@Data
public class UpdatePrePersonRequest {
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
}