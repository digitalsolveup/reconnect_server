package reconnect.server.domain.missing_person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reconnect.server.global.model.entity.mysql.MissingPerson;
import java.util.List;

public interface MissingPersonRepository extends JpaRepository<MissingPerson, Long> {

    List<MissingPerson> findAllByOrderByReportCountAsc();  // 제보 수에 따른 정렬
    List<MissingPerson> findAllByOrderByIdDesc();  // 등록순 정렬
}
