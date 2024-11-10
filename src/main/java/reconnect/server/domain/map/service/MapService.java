package reconnect.server.domain.map.service;

import org.springframework.stereotype.Service;
import reconnect.server.domain.map.model.dto.Location;
import reconnect.server.domain.map.model.request.LocationRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MapService {
    private static final double EARTH_RADIUS_KM = 6371.0;

    public List<Location> sendDummyData(LocationRequest locationRequest) {
        Location userLocation = locationRequest.getUserLocation();
        Random random = new Random();
        int num = 10 + random.nextInt(11);
        return generateRandomLocations(userLocation, num);
    }

    /**
     * 주어진 위도, 경도에서 1km 반경 내에 랜덤으로 위치를 생성
     */
    public List<Location> generateRandomLocations(Location userLocation, int num) {
        Random random = new Random();
        List<Location> locations = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            double distance = random.nextDouble();
            double angle = random.nextDouble() * 2 * Math.PI;

            // 랜덤으로 생성된 거리와 각도를 사용하여 위도와 경도를 계산
            double deltaLat = distance / EARTH_RADIUS_KM;
            double deltaLon = distance / (EARTH_RADIUS_KM * Math.cos(Math.PI * userLocation.getLatitude() / 180.0));

            double newLat = userLocation.getLatitude() + (deltaLat * 180.0 / Math.PI) * Math.cos(angle);
            double newLon = userLocation.getLongitude() + (deltaLon * 180.0 / Math.PI) * Math.sin(angle);

            locations.add(new Location(newLat, newLon));
        }

        return locations;
    }
}
