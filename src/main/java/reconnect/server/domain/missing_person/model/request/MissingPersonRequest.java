package reconnect.server.domain.missing_person.model.request;

import lombok.Data;
import reconnect.server.global.model.enums.*;

@Data
public class MissingPersonRequest {
    private String name;
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
    private String lastSeenLocation;
    private Nationality nationality;
    private String imageURL;
}
