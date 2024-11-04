package reconnect.server.domain.preperson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reconnect.server.domain.preperson.model.entity.PrePerson;

public interface PrePersonRepository extends JpaRepository<PrePerson, Long> {
}