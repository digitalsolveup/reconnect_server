package reconnect.server.domain.preperson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reconnect.server.global.model.entity.mysql.PrePerson;

import java.util.List;

public interface PrePersonRepository extends JpaRepository<PrePerson, Long> {

    // 실종자 리스트 조회
    List<PrePerson> findBy();
}