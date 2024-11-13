package reconnect.server.domain.missing_person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reconnect.server.domain.missing_person.model.response.MissingPersonResponse;
import reconnect.server.domain.missing_person.repository.MissingPersonRepository;
import reconnect.server.global.model.entity.mysql.MissingPerson;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissingPersonService {

    private final MissingPersonRepository missingPersonRepository;

    @Autowired
    public MissingPersonService(MissingPersonRepository missingPersonRepository) {
        this.missingPersonRepository = missingPersonRepository;
    }

    // 실종자 목록 조회 - 정렬 기준에 따라
    public List<MissingPersonResponse> getMissingPersons(String sortBy) {
        List<MissingPerson> persons;
        if (sortBy.equals("REPORT_COUNT")) {
            persons = missingPersonRepository.findAllByOrderByReportCountAsc();
        } else {
            persons = missingPersonRepository.findAllByOrderByIdDesc();
        }
        return persons.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    // 실종자 상세 정보 조회
    public MissingPersonResponse getMissingPersonDetails(Long id) {
        MissingPerson person = missingPersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MissingPerson not found"));
        return mapToResponse(person);
    }

    // MissingPerson을 MissingPersonResponse로 변환
    private MissingPersonResponse mapToResponse(MissingPerson person) {
        MissingPersonResponse response = new MissingPersonResponse();
        response.setId(person.getId());
        response.setName(person.getName());
        response.setImageURL(person.getImageURL());
        response.setSpecialFeature(person.getSpecialFeature());
        response.setGender(person.getGender());
        response.setAge(person.getAge());
        response.setHeight(person.getHeight());
        response.setWeight(person.getWeight());
        response.setBodyType(person.getBodyType());
        response.setFaceType(person.getFaceType());
        response.setTops(person.getTops());
        response.setBottoms(person.getBottoms());
        response.setShoes(person.getShoes());
        response.setAccessories(person.getAccessories());
        response.setHair(person.getHair());
        response.setLastSeenDateTime(person.getLastSeenDateTime());
        response.setLastSeenLocation(person.getLastSeenLocation());
        response.setNationality(person.getNationality());
        response.setReportCount(person.getReportCount());
        return response;
    }
}
