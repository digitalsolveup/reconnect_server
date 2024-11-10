package reconnect.server.domain.missing_person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reconnect.server.global.model.entity.mysql.MissingPerson;

import java.util.List;

public interface MissingPersonRepository extends JpaRepository<MissingPerson, Long> {

    // 제보 수에 따른 정렬 (오름차순)
    @Query("SELECT m FROM MissingPerson m ORDER BY m.reportCount ASC")
    List<MissingPerson> findAllByOrderByReportCountAsc();

    // 등록순 정렬 (최신순)
    @Query("SELECT m FROM MissingPerson m ORDER BY m.id DESC")
    List<MissingPerson> findAllByOrderByIdDesc();
}
