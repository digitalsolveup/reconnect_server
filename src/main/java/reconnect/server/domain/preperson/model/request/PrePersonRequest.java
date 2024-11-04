package reconnect.server.domain.preperson.model.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PrePersonRequest {
    private String name;
    private String gender;
    private LocalDate birthDate;
    private Integer height;
    private Integer weight;
    private String photoUrl;
    private String specialFeature;
    private String personality;
    private String frequentPlace;
    private String additionalInfo;
    private String familyCertificateUrl;
}