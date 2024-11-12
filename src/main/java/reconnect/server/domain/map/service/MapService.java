package reconnect.server.domain.map.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reconnect.server.domain.map.model.dto.Location;
import reconnect.server.domain.map.model.request.LocationRequest;
import reconnect.server.domain.map.model.response.LocationInfoResponse;
import reconnect.server.domain.map.model.response.MissingPersonResponse;
import reconnect.server.domain.map.repository.MissingPersonQueryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MapService {
    private static final double EARTH_RADIUS_KM = 6371.0;
    private final MissingPersonQueryRepository missingPersonQueryRepository;

    /**
     * 지도 표시를 위한 위치 반환
     */
    public List<Location> sendDummyData(LocationRequest locationRequest) {
        Random random = new Random();
        int num = 10 + random.nextInt(11);
        return generateRandomLocations(locationRequest, num);
    }


    /**
     * 반경 Km 안 실종자 정보 반환
     */
    public List<MissingPersonResponse> getMissingPersonInRadius(LocationRequest locationRequest){
        Random random = new Random();
        int limit = 5 + random.nextInt(6);

        // 랜덤 개수 만큼 실종자 정보 조회
       return missingPersonQueryRepository.findRandomMissingPersons(limit)
               .stream().map(MissingPersonResponse::new).toList();
    }

    /**
     * 반경 Km 안 위치 정보 반환
     */
    public List<LocationInfoResponse> getLocationInfoInRadius(LocationRequest locationRequest){
        Random random = new Random();
        int num = 10 + random.nextInt(11);
        List<LocationInfoResponse> locationInfoResponses = new ArrayList<>();

        for(int i=0; i < num; i++) {
            locationInfoResponses.add(LocationInfoResponse.builder()
                            .name("OO 식당")
                            .build());
        }
        return locationInfoResponses;
    }

    /**
     * 주어진 위도, 경도에서 1km 반경 내에 랜덤으로 위치를 생성
     */
    private List<Location> generateRandomLocations(LocationRequest locationRequest, int num) {
        Random random = new Random();
        List<Location> locations = new ArrayList<>();
        Location userLocation = locationRequest.getUserLocation();


        for (int i = 0; i < num; i++) {
            double distance = random.nextDouble();
            double angle = random.nextDouble() * 2 * Math.PI;

            // 랜덤으로 생성된 거리와 각도를 사용하여 위도와 경도를 계산
            double deltaLat = distance / EARTH_RADIUS_KM * locationRequest.getRadius();
            double deltaLon = distance / (EARTH_RADIUS_KM * Math.cos(Math.PI * userLocation.getLatitude() / 180.0)) * locationRequest.getRadius();

            double newLat = userLocation.getLatitude() + (deltaLat * 180.0 / Math.PI) * Math.cos(angle);
            double newLon = userLocation.getLongitude() + (deltaLon * 180.0 / Math.PI) * Math.sin(angle);

            locations.add(new Location(newLat, newLon));
        }

        return locations;
    }
}
