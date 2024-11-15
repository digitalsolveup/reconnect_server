package reconnect.server.domain.missing_person.model.response;

import lombok.Data;
import reconnect.server.global.model.entity.mysql.MissingPerson;

import java.time.LocalDateTime;

@Data
public class MissingPersonDetailResponse {

    private String name;
    private String imageURL;
    private String specialFeature;
    private String nationality;
    private String gender;
    private int age;
    private int height;
    private int weight;
    private String bodyType;
    private String faceType;
    private String tops;
    private String bottoms;
    private String shoes;
    private String accessories;
    private String hair;
    private LocalDateTime lastSeenDateTime;
    private double lastSeenLatitude;
    private double lastSeenLongitude;

    public MissingPersonDetailResponse(MissingPerson missingPerson) {
        this.name = missingPerson.getName();
        this.imageURL = missingPerson.getImageURL();
        this.specialFeature = missingPerson.getSpecialFeature().name();
        this.nationality = missingPerson.getNationality().name();
        this.gender = missingPerson.getGender().name();
        this.age = missingPerson.getAge();
        this.height = missingPerson.getHeight();
        this.weight = missingPerson.getWeight();
        this.bodyType = missingPerson.getBodyType().name();
        this.faceType = missingPerson.getFaceType().name();
        this.tops = missingPerson.getTops();
        this.bottoms = missingPerson.getBottoms();
        this.shoes = missingPerson.getShoes();
        this.accessories = missingPerson.getAccessories();
        this.hair = missingPerson.getHair();
        this.lastSeenDateTime = missingPerson.getLastSeenDateTime();
        this.lastSeenLatitude = missingPerson.getLastSeenLatitude();
        this.lastSeenLongitude = missingPerson.getLastSeenLongitude();
    }
}
