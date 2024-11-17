package reconnect.server.domain.map.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import reconnect.server.domain.map.model.dto.Location;
import reconnect.server.domain.map.model.request.LocationRequest;
import reconnect.server.domain.map.model.response.LocationInfoListResponse;
import reconnect.server.domain.map.model.response.MissingPersonResponse;
import reconnect.server.domain.map.service.MapService;
import reconnect.server.global.executor.GptExecutor;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MapController {

    private final SimpMessagingTemplate messagingTemplate;
    private final GptExecutor gptExecutor;
    private final MapService mapService;

    @MessageMapping("/ws/location")
    public void handleLocation(LocationRequest locationRequest) {

        List<Location> predictedLocations = mapService.getLocation(locationRequest);

        messagingTemplate.convertAndSend("/topic/predicted-locations", predictedLocations);
    }

    /**
     * 반경 내 실종자 정보 반환
     */
    @MessageMapping("/ws/radius/missing-persons")
    public void handleMissingPersonsInRadius(LocationRequest locationRequest) {

        List<MissingPersonResponse> missingPersons = mapService.getMissingPersonInRadius(locationRequest);

        messagingTemplate.convertAndSend("/topic/radius-missing-persons", missingPersons);
    }

    /**
     * 반경 내 위치 정보를 WebSocket으로 반환
     */
    @MessageMapping("/ws/radius/location-info")
    public void handleLocationInfoInRadius(LocationRequest locationRequest) {

        LocationInfoListResponse locationInfoResponses = mapService.getLocationInfoInRadius(locationRequest);

        messagingTemplate.convertAndSend("/topic/radius-location-info", locationInfoResponses);
    }
}
