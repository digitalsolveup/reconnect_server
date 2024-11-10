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

import java.time.LocalDateTime;

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
}