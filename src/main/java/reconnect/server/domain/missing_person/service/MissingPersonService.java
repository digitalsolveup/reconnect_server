package reconnect.server.domain.missing_person.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reconnect.server.global.model.entity.mysql.MissingPerson;
import reconnect.server.domain.missing_person.repository.MissingPersonRepository;

import java.util.List;



@Service
@RequiredArgsConstructor
public class MissingPersonService {
    private final MissingPersonRepository missingPersonRepository;

    public List<MissingPerson> getAllMissingPersons() {
        return missingPersonRepository.findAll();
    }
}