package reconnect.server.domain.search.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.domain.search.model.dto.MissingPersonDto;
import reconnect.server.global.model.enums.Gender;
import reconnect.server.global.model.enums.SpecialFeature;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {
    private Long id;
    private String name;
    private String imageURL;
    private SpecialFeature specialFeature;
    private Gender gender;
    private int age;
    private int height;
    private int weight;
    private String tops;
    private String bottoms;
    private String shoes;
    private String accessories;
    private String hair;

    public SearchResponse(MissingPersonDto missingPersonDto){
        this.id = missingPersonDto.getId();
        this.name = missingPersonDto.getName();
        this.imageURL = missingPersonDto.getImageURL();
        this.specialFeature = missingPersonDto.getSpecialFeature();
        this.gender = missingPersonDto.getGender();
        this.age = missingPersonDto.getHeight();
        this.weight = missingPersonDto.getWeight();
        this.tops = missingPersonDto.getTops();
        this.bottoms = missingPersonDto.getBottoms();
        this.shoes = missingPersonDto.getShoes();
        this.accessories = missingPersonDto.getAccessories();
        this.hair = missingPersonDto.getHair();
    }
}
