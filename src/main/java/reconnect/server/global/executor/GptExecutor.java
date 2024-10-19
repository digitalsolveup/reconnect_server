package reconnect.server.global.executor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reconnect.server.domain.map.model.dto.Location;
import reconnect.server.domain.map.model.request.LocationRequest;
import reconnect.server.global.client.GptClient;
import reconnect.server.global.model.response.GptResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GptExecutor {

    private final GptClient gptClient;

    /**
     * GPT 에게 요청을 보내 예측된 실종자 장소 List 반환
     */
    public List<Location> getPredictedLocations(LocationRequest locationRequest) {

        // TODO: 실종자 정보 같이 넘겨주기
        GptResponse gptResponse = gptClient.getPredictedLocations(locationRequest);

        return gptResponse.getPredictedLocations();
    }

}

