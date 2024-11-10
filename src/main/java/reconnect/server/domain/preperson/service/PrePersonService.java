package reconnect.server.domain.preperson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reconnect.server.domain.preperson.model.entity.PrePerson;
import reconnect.server.domain.preperson.repository.PrePersonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrePersonService {

    private final PrePersonRepository prePersonRepository;

    @Transactional(readOnly = true)
    public List<PrePerson> getAllPrePersons() {
        return prePersonRepository.findAll();
    }

    @Transactional
    public PrePerson registerPrePerson(PrePerson prePerson) {
        return prePersonRepository.save(prePerson);
    }
}