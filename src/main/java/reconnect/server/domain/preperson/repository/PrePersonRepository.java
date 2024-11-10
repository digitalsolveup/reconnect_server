package reconnect.server.domain.preperson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reconnect.server.global.model.entity.mysql.PrePerson;

public interface PrePersonRepository extends JpaRepository<PrePerson, Long> {
}