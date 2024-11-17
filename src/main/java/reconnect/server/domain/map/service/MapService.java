package reconnect.server.domain.map.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reconnect.server.domain.map.model.dto.Location;
import reconnect.server.domain.map.model.request.LocationRequest;
import reconnect.server.domain.map.model.response.LocationInfoListResponse;
import reconnect.server.domain.map.model.response.LocationInfoResponse;
import reconnect.server.domain.map.model.response.MissingPersonResponse;
import reconnect.server.domain.map.repository.MissingPersonQueryRepository;
import reconnect.server.domain.missing_person.repository.MissingPersonRepository;
import reconnect.server.global.client.GoogleMapClient;
import reconnect.server.global.model.entity.mysql.MissingPerson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MapService {
    @Value("${google.api.map.key}")
    private String apiToken;
    private static final double EARTH_RADIUS_KM = 6371.0;
    private final MissingPersonQueryRepository missingPersonQueryRepository;
    private final MissingPersonRepository missingPersonRepository;
    private final KeywordService keywordService;
    private final GoogleMapClient googleMapClient;

    /**
     * 반경 Km 안 실종자 정보 반환
     */
    public List<MissingPersonResponse> getMissingPersonInRadius(LocationRequest locationRequest) {
        int limit = new Random().nextInt(6) + 5; // 5 ~ 10개의 데이터를 가져옴
        return missingPersonQueryRepository.findRandomMissingPersons(limit)
                .stream()
                .map(MissingPersonResponse::new)
                .toList();
    }

    public List<Location> getLocation(LocationRequest locationRequest){
        LocationInfoListResponse locationInfoListResponse = this.getLocationInfoInRadius(locationRequest);
        List<LocationInfoResponse> locationInfoResponses = locationInfoListResponse.getResults();
        List<Location> locations = new ArrayList<>();

        for(LocationInfoResponse locationInfo : locationInfoResponses){
            locations.add(locationInfo.getGeometry().getLocation());
        }

        return locations;
    }

    /**
     * 반경 Km 안 위치 정보 반환 (실종자의 특성을 기반으로 장소를 검색)
     */
    public LocationInfoListResponse getLocationInfoInRadius(LocationRequest locationRequest) {
        Location userLocation = locationRequest.getUserLocation();

        // 실종자 정보를 기반으로 장소 추천
        MissingPerson missingPersons = missingPersonRepository.findMissingPersonById(locationRequest.getMissedUserSeq());
        LocationInfoListResponse results = null;

        // 실종자의 연령대와 성별에 맞는 키워드 가져오기
        String keyword = keywordService.getKeyword(missingPersons);
        String location = userLocation.getLat() + "," +  userLocation.getLng();

        // 카카오 API를 통해 장소 검색
        try {
            log.info("Calling API with params: location={}, radius={}, keyword={}", location, locationRequest.getRadius() * 100, keyword);

            results = googleMapClient.fetchLocations(
                    location,
                    locationRequest.getRadius() * 100,
                    keyword,
                    apiToken
            );
        } catch (Exception e) {
            log.error("Failed to fetch locations for keyword: {}, error: {}", keyword, e.getMessage());
        }

        return results;
    }

    /**
     * 주어진 위도, 경도에서 1km 반경 내에 랜덤으로 위치를 생성
     */
    @Deprecated
    private List<Location> generateRandomLocations(LocationRequest locationRequest, int num) {
        Random random = new Random();
        List<Location> locations = new ArrayList<>();
        Location userLocation = locationRequest.getUserLocation();

        for (int i = 0; i < num; i++) {
            double distance = random.nextDouble();
            double angle = random.nextDouble() * 2 * Math.PI;

            double deltaLat = distance / EARTH_RADIUS_KM * locationRequest.getRadius();
            double deltaLon = distance / (EARTH_RADIUS_KM * Math.cos(Math.PI * userLocation.getLat() / 180.0)) * locationRequest.getRadius();

            double newLat = userLocation.getLat() + (deltaLat * 180.0 / Math.PI) * Math.cos(angle);
            double newLon = userLocation.getLng() + (deltaLon * 180.0 / Math.PI) * Math.sin(angle);

            locations.add(new Location(newLat, newLon));
        }

        return locations;
    }
}
