package reconnect.server.domain.missing_person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reconnect.server.global.model.entity.mysql.MissingPerson;
import reconnect.server.global.model.enums.MissingStatus;

import java.util.List;

public interface MissingPersonRepository extends JpaRepository<MissingPerson, Long> {

    List<MissingPerson> findAllByMissingStatusNot(MissingStatus missingStatus);
    MissingPerson findMissingPersonById(Long id);
}
