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
    public List<Location> getPredictedLocations(LocationRequest locationRequest, Long missedUserSeq) {
        // 사용자 위치 정보 및 실종자 정보를 기반으로 프롬프트 작성

        /**
        MissingPerson missingPerson = missingPersonRepository.findById(missedUserSeq);
        String prompt = String.format(
                "실종자는 %d세 %s으로, 마지막으로 %s에서 목격되었습니다. 사용자의 현재 위치는 위도 %f, 경도 %f 입니다. 사용자의 반경 1km 이내에 실종자가 있을 가능성이 있는 위치를 예측해주세요. 주변 환경은 %s이며, 실종자는 보통 %s 지역을 선호합니다. 예측된 위치의 위도와 경도를 알려주세요.",
                missingPerson.getAge(),
                missingPerson.getGender(),
                missingPerson.getLastSeenLocation(),
                locationRequest.getUserLocation().getLatitude(),
                locationRequest.getUserLocation().getLongitude(),
                locationRequest.getEnvironment(),
                missingPerson.getPreferredAreas()
        );
         **/

        // GptRequest에 프롬프트 및 필요한 매개변수 설정
        locationRequest.setModel("gpt-4o");
        // locationRequest.setPrompt(prompt);
        locationRequest.setMax_tokens(1500);

        // GPT API 호출
        GptResponse gptResponse = gptClient.getPredictedLocations(locationRequest);

        // 예측된 위치 리스트 반환
        return gptResponse.getPredictedLocations();
    }

}

