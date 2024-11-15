package reconnect.server.domain.missing_person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reconnect.server.domain.missing_person.model.response.MissingPersonDetailResponse;
import reconnect.server.domain.missing_person.model.response.MissingPersonListResponse;
import reconnect.server.domain.missing_person.repository.MissingPersonRepository;
import reconnect.server.global.model.entity.mysql.MissingPerson;
import reconnect.server.global.model.enums.MissingStatus;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissingPersonService {

    private final MissingPersonRepository missingPersonRepository;

    @Autowired
    public MissingPersonService(MissingPersonRepository missingPersonRepository) {
        this.missingPersonRepository = missingPersonRepository;
    }

    // 실종자 목록 조회
    public List<MissingPersonListResponse> getMissingPersons(String sortBy, double currentLatitude, double currentLongitude) {
        List<MissingPerson> missingPersons = missingPersonRepository.findAllByMissingStatusNot(MissingStatus.FOUND);

        Comparator<MissingPerson> comparator;
        switch (sortBy.toUpperCase()) {
            case "DISTANCE":
                // 거리 계산 로직
                comparator = Comparator.comparingDouble(missingPerson -> calculateDistance(
                        currentLatitude, currentLongitude,
                        missingPerson.getLastSeenLatitude(), missingPerson.getLastSeenLongitude()
                ));
                break;
            case "REPORT_COUNT":
                comparator = Comparator.comparingInt(MissingPerson::getReportCount);
                break;
            case "REGISTRATION_DATE":
            default:
                comparator = Comparator.comparing(MissingPerson::getLastSeenDateTime).reversed(); // 최신순 정렬
                break;
        }

        return missingPersons.stream()
                .sorted(comparator)
                .map(MissingPersonListResponse::new)
                .collect(Collectors.toList());
    }

    // 실종자 상세 정보 조회
    public MissingPersonDetailResponse getMissingPersonDetails(Long id) {
        MissingPerson missingPerson = missingPersonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("MissingPerson not found with id: " + id));
        return new MissingPersonDetailResponse(missingPerson);
    }

    // 거리 계산
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 간단한 Haversine Formula 구현 (위도와 경도로 거리 계산)
        double R = 6371; // Earth radius in kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
