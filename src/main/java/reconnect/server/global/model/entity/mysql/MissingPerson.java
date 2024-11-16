package reconnect.server.global.model.entity.mysql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.global.model.enums.BodyType;
import reconnect.server.global.model.enums.FaceType;
import reconnect.server.global.model.enums.Gender;
import reconnect.server.global.model.enums.SpecialFeature;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "missing_person")
public class MissingPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageURL;

    @Enumerated(EnumType.STRING)
    private SpecialFeature specialFeature; // 특이사항

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;
    private int height;
    private int weight;

    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    @Enumerated(EnumType.STRING)
    private FaceType faceType;

    private String tops;
    private String bottoms;
    private String shoes;
    private String accessories;
    private String hair;

    private LocalDateTime lastSeenDateTime;
    private String lastSeenLocation;

    public MissingPerson(PrePerson prePerson){
        this.id = prePerson.getId();
        this.name = prePerson.getName();
        this.imageURL = prePerson.getPhotoUrl();
        this.specialFeature = prePerson.getSpecialFeature();
        this.gender = prePerson.getGender();
        this.age = Period.between(prePerson.getBirthDate(), LocalDate.now()).getYears(); // 만 나이
        this.height = prePerson.getHeight();
        this.weight = prePerson.getWeight();
        this.lastSeenDateTime = LocalDateTime.now();
    }
}