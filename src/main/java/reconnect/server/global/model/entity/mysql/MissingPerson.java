package reconnect.server.global.model.entity.mysql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.global.model.enums.*;

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
    private Long id; // 실종자 고유 id

    private String name; // 실종자 이름
    private String imageURL; // 실종자 사진 URL

    @Enumerated(EnumType.STRING)
    private SpecialFeature specialFeature; // (enum) 특이사항 - NONE(없음), NON_DISABLED_CHILD(비장애아동), DISABILITY(장애), DEMENTIA(치매), RUNAWAY(가출인), OTHER(기타)

    @Enumerated(EnumType.STRING)
    private Gender gender; // (enum) 성별 - MALE(남자), FEMALE(여자)
    private int age; // 나이
    private int height; // 키
    private int weight; // 몸무게

    @Enumerated(EnumType.STRING)
    private BodyType bodyType; // (enum) 체형 - SLIM(마름), AVERAGE(보통), OBESE(비만)

    @Enumerated(EnumType.STRING)
    private FaceType faceType; // (enum) 얼굴형 - OVAL(계란형), SQUARE(사각형), LONG(긴), TRIANGLE(삼각형)

    private String tops; // 상의
    private String bottoms; // 하의
    private String shoes; // 신발
    private String accessories; // 악세사리
    private String hair; // 두발

    private LocalDateTime lastSeenDateTime; // 실종 발생 일시

    private String lastSeenLocation; // 실종 마지막 발견 위치

    private double lastSeenLatitude; // 실종 위도
    private double lastSeenLongitude; // 실종 경도

    @Enumerated(EnumType.STRING)
    private Nationality nationality; // (enum) 내외국인 - DOMESTIC(내국인), FOREIGN(외국인)

    private int reportCount; // 제보 수

    @Enumerated(EnumType.STRING)
    private MissingStatus missingStatus; // (enum) 실종 상태 - SEARCHING(수색중), FOUND(발견완료)
}