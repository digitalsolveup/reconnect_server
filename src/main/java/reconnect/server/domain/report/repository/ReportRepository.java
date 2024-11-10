package reconnect.server.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reconnect.server.global.model.entity.mysql.Report;
import reconnect.server.global.model.entity.mysql.MissingPerson;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    // 특정 실종자 정보 조회 쿼리
    @Query("SELECT m.imageURL, m.name, m.gender, m.age, m.height, m.weight, m.specialFeature, " +
            "m.tops, m.bottoms, m.shoes, m.accessories, m.hair " +
            "FROM MissingPerson m WHERE m.id = :missingPersonId")
    Object[] findMissingPersonInfoById(@Param("missingPersonId") Long missingPersonId);

    // 명시적 INSERT 쿼리
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO report (missing_person_id, gender, age_group, special_feature, " +
            "found_image_url, location_found, additional_description, surrounding_image_url, additional_report, reported_at, report_count) " +
            "VALUES (:missingPersonId, :gender, :ageGroup, :specialFeature, :foundImageUrl, :locationFound, " +
            ":additionalDescription, :surroundingImageUrl, :additionalReport, :reportedAt, :reportCount)", nativeQuery = true)
    void insertReport(
            @Param("missingPersonId") Long missingPersonId,
            @Param("gender") String gender,
            @Param("ageGroup") String ageGroup,
            @Param("specialFeature") String specialFeature,
            @Param("foundImageUrl") String foundImageUrl,
            @Param("locationFound") String locationFound,
            @Param("additionalDescription") String additionalDescription,
            @Param("surroundingImageUrl") String surroundingImageUrl,
            @Param("additionalReport") String additionalReport,
            @Param("reportedAt") LocalDateTime reportedAt,
            @Param("reportCount") int reportCount
    );
}
