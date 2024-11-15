package reconnect.server.domain.preperson.service;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reconnect.server.domain.preperson.model.request.PrePersonRequest;
import reconnect.server.domain.preperson.model.request.UpdatePrePersonRequest;
import reconnect.server.domain.preperson.model.response.PrePersonDetailResponse;
import reconnect.server.domain.preperson.model.response.PrePersonListResponse;
import reconnect.server.domain.preperson.repository.PrePersonRepository;
import reconnect.server.global.model.entity.mysql.PrePerson;
import reconnect.server.global.model.enums.RegistrationStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PrePersonService {

    private final PrePersonRepository prePersonRepository;

    @Autowired
    public PrePersonService(PrePersonRepository prePersonRepository) {
        this.prePersonRepository = prePersonRepository;
    }

    // 모든 PrePerson을 조회
    public List<PrePersonListResponse> getPrePersonResponses() {
        return prePersonRepository.findBy().stream()
                .map(PrePersonListResponse::new)
                .collect(Collectors.toList());
    }

    // 실종자 사전 등록
    public PrePersonListResponse createPrePerson(PrePersonRequest prePersonRequest) {
        PrePerson prePerson = prePersonRequest.toEntity();
        prePerson.setStatus(RegistrationStatus.PENDING); // 생성 시 기본 상태 설정
        PrePerson savedPrePerson = prePersonRepository.save(prePerson);
        return new PrePersonListResponse(savedPrePerson);
    }

    // 사전 등록 실종자 수정
    public PrePersonDetailResponse updatePrePerson(Long id, UpdatePrePersonRequest updateRequest) {
        PrePerson prePerson = prePersonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PrePerson not found with id " + id));

        // updateRequest에서 값을 가져와 엔티티의 각 필드를 업데이트
        prePerson.setName(updateRequest.getName());
        prePerson.setGender(updateRequest.getGender());
        prePerson.setBirthDate(updateRequest.getBirthDate());
        prePerson.setHeight(updateRequest.getHeight());
        prePerson.setWeight(updateRequest.getWeight());
        prePerson.setPhotoUrl(updateRequest.getPhotoUrl());
        prePerson.setSpecialFeature(updateRequest.getSpecialFeature());
        prePerson.setPersonality(updateRequest.getPersonality());
        prePerson.setLastSeenLatitude(updateRequest.getLastSeenLatitude());
        prePerson.setLastSeenLongitude(updateRequest.getLastSeenLongitude());
        prePerson.setAdditionalInfo(updateRequest.getAdditionalInfo());
        prePerson.setFamilyCertificateUrl(updateRequest.getFamilyCertificateUrl());
        prePerson.setStatus(updateRequest.getStatus());

        // 수정된 엔티티 저장
        PrePerson updatedPrePerson = prePersonRepository.save(prePerson);
        return new PrePersonDetailResponse(updatedPrePerson);
    }


    // 사전 등록 실종자 삭제
    public void deletePrePerson(Long id) {
        if (!prePersonRepository.existsById(id)) {
            throw new ResourceNotFoundException("PrePerson not found with id " + id);
        }
        prePersonRepository.deleteById(id);
    }
}
