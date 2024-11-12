package reconnect.server.domain.search.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.global.model.enums.Gender;
import reconnect.server.global.model.enums.SpecialFeature;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissingPersonDto {
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
}
