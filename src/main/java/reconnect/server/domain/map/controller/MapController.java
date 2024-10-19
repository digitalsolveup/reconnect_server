package reconnect.server.domain.map.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import reconnect.server.domain.map.model.dto.Location;
import reconnect.server.domain.map.model.request.LocationRequest;
import reconnect.server.global.executor.GptExecutor;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MapController {

    private final SimpMessagingTemplate messagingTemplate;
    private final GptExecutor gptExecutor;

    @MessageMapping("/ws/location")
    public void handleLocation(LocationRequest locationRequest) {

        // 실시간 사용자 위치 처리 후 GPT API에 요청
        List<Location> predictedLocations = gptExecutor.getPredictedLocations(locationRequest);

        // 클라이언트에게 실종자 가능 위치를 실시간 전송
        messagingTemplate.convertAndSend("/topic/predicted-locations", predictedLocations);
    }
}
