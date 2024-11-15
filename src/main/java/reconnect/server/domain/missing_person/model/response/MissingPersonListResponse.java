package reconnect.server.domain.missing_person.model.response;

import lombok.Data;
import reconnect.server.global.model.entity.mysql.MissingPerson;

@Data
public class MissingPersonListResponse {

    private Long id;
    private String name;
    private String imageURL;
    private String gender;
    private int age;
    private int height;
    private int weight;
    private String specialFeature;
    private String tops;
    private String bottoms;
    private String shoes;
    private String accessories;
    private String hair;
    private double latitude;
    private double longitude;

    public MissingPersonListResponse(MissingPerson missingPerson) {
        this.id = missingPerson.getId();
        this.name = missingPerson.getName();
        this.imageURL = missingPerson.getImageURL();
        this.gender = missingPerson.getGender().name();
        this.age = missingPerson.getAge();
        this.height = missingPerson.getHeight();
        this.weight = missingPerson.getWeight();
        this.specialFeature = missingPerson.getSpecialFeature().name();
        this.tops = missingPerson.getTops();
        this.bottoms = missingPerson.getBottoms();
        this.shoes = missingPerson.getShoes();
        this.accessories = missingPerson.getAccessories();
        this.hair = missingPerson.getHair();
        this.latitude = missingPerson.getLastSeenLatitude();
        this.longitude = missingPerson.getLastSeenLongitude();
    }
}
