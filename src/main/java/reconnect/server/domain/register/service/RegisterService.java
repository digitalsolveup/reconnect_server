package reconnect.server.domain.register.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reconnect.server.domain.auth.security.LoginManager;
import reconnect.server.domain.preperson.model.response.PrePersonResponse;
import reconnect.server.domain.preperson.repository.PrePersonRepository;
import reconnect.server.domain.register.mapper.PrePersonResponseMapper;
import reconnect.server.domain.register.model.request.PrePersonRegisterRequest;
import reconnect.server.global.model.entity.mysql.PrePerson;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {

    private final PrePersonResponseMapper prePersonResponseMapper;
    private final PrePersonRepository prePersonRepository;

    public List<PrePersonResponse> getPrePerson() {
        Long userSeq = Objects.requireNonNull(LoginManager.getUserDetails()).getUserSeq();
        return prePersonResponseMapper.toDto(prePersonRepository.findAllByRegisteredUserSeq(userSeq));

    }

    public void reportPrePerson(PrePersonRegisterRequest request) {
        PrePerson prePerson = prePersonRepository.findById(request.getPrePersonSeq()).orElse(null);
        
    }
}
