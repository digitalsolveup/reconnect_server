package reconnect.server.domain.preperson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reconnect.server.global.model.entity.mysql.PrePerson;

import java.util.List;

public interface PrePersonRepository extends JpaRepository<PrePerson, Long> {

    // 1. SELECT 쿼리로 id, name, status 필드만 조회
//    @Query("SELECT p.id, p.name, p.status FROM PrePerson p")
//    List<Object[]> findIdNameStatus();

    List<PrePerson> findBy();

    // 2. INSERT 쿼리
    @Query(value = "INSERT INTO pre_person " +
            "(name, gender, birth_date, height, weight, photo_url, special_feature, personality, frequent_place, additional_info, family_certificate_url, status) " +
            "VALUES (:name, :gender, :birthDate, :height, :weight, :photoUrl, :specialFeature, :personality, :frequentPlace, :additionalInfo, :familyCertificateUrl, :status)",
            nativeQuery = true)
    void insertPrePerson(
            @Param("name") String name,
            @Param("gender") String gender,
            @Param("birthDate") String birthDate,
            @Param("height") Integer height,
            @Param("weight") Integer weight,
            @Param("photoUrl") String photoUrl,
            @Param("specialFeature") String specialFeature,
            @Param("personality") String personality,
            @Param("frequentPlace") String frequentPlace,
            @Param("additionalInfo") String additionalInfo,
            @Param("familyCertificateUrl") String familyCertificateUrl,
            @Param("status") String status
    );
}
