package reconnect.server.domain.preperson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reconnect.server.domain.preperson.model.request.PrePersonRequest;
import reconnect.server.domain.preperson.repository.PrePersonRepository;
import reconnect.server.global.model.entity.mysql.PrePerson;
import reconnect.server.global.model.enums.RegistrationStatus;

import java.util.List;

@Service
public class PrePersonService {

    private final PrePersonRepository prePersonRepository;

    @Autowired
    public PrePersonService(PrePersonRepository prePersonRepository) {
        this.prePersonRepository = prePersonRepository;
    }

    // 모든 PrePerson을 조회
    public List<PrePerson> getPrePersons() {
        return prePersonRepository.findAll();
    }

    // PrePersonRequest를 받아서 PrePerson 엔터티로 변환하여 저장
    public PrePerson createPrePerson(PrePersonRequest prePersonRequest) {
        PrePerson prePerson = PrePerson.builder()
                .name(prePersonRequest.getName())
                .status(RegistrationStatus.PENDING)
                .gender(prePersonRequest.getGender())
                .birthDate(prePersonRequest.getBirthDate())
                .height(prePersonRequest.getHeight())
                .weight(prePersonRequest.getWeight())
                .photoUrl(prePersonRequest.getPhotoUrl())
                .specialFeature(prePersonRequest.getSpecialFeature())
                .personality(prePersonRequest.getPersonality())
                .frequentPlace(prePersonRequest.getFrequentPlace())
                .additionalInfo(prePersonRequest.getAdditionalInfo())
                .familyCertificateUrl(prePersonRequest.getFamilyCertificateUrl())
                .build();
        return prePersonRepository.save(prePerson);
    }
}
