package reconnect.server.domain.missing_person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reconnect.server.global.model.entity.mysql.MissingPerson;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissingPersonRepository extends JpaRepository<MissingPerson, Long> {

    MissingPerson findMissingPersonById(Long id);

    // 등록순 정렬
    @Query("SELECT m FROM MissingPerson m ORDER BY m.lastSeenDateTime DESC")
    List<MissingPerson> findAllOrderByRegistrationDate();

//    // 거리순 정렬 (Haversine formula 사용)
//    @Query("SELECT m, (6371 * acos(cos(radians(:latitude)) * cos(radians(m.latitude)) * cos(radians(m.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(m.latitude)))) AS distance " +
//            "FROM MissingPerson m " +
//            "ORDER BY distance")
//    List<MissingPerson> findAllOrderByDistance(@Param("latitude") Double latitude, @Param("longitude") Double longitude);
}