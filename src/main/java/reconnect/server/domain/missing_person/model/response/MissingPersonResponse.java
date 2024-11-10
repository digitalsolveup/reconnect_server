package reconnect.server.domain.missing_person.model.response;

import lombok.Data;
import reconnect.server.global.model.enums.*;
import java.time.LocalDateTime;

@Data
public class MissingPersonResponse {
    private Long id;
    private String name;
    private String imageURL;
    private SpecialFeature specialFeature;
    private Gender gender;
    private int age;
    private int height;
    private int weight;
    private BodyType bodyType;
    private FaceType faceType;
    private String tops;
    private String bottoms;
    private String shoes;
    private String accessories;
    private String hair;
    private LocalDateTime lastSeenDateTime;
    private String lastSeenLocation;
    private Nationality nationality;
    private int reportCount;
}
