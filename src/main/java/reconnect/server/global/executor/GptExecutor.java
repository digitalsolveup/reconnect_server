package reconnect.server.global.executor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reconnect.server.domain.map.model.dto.Location;
import reconnect.server.domain.map.model.request.LocationRequest;
import reconnect.server.domain.missing_person.repository.MissingPersonRepository;
import reconnect.server.global.client.GptClient;
import reconnect.server.global.model.entity.mysql.MissingPerson;
import reconnect.server.global.model.request.GptRequest;
import reconnect.server.global.model.response.GptResponse;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GptExecutor {

    private final GptClient gptClient;
    private final MissingPersonRepository missingPersonRepository;

    /**
     * deprecated
     * GPT 에게 요청을 보내 예측된 실종자 장소 List 반환
     */
    public List<Location> getPredictedLocations(LocationRequest locationRequest) {

        // 사용자 위치 정보 및 실종자 정보를 기반으로 프롬프트 작성
        MissingPerson missingPerson = missingPersonRepository.findMissingPersonById(locationRequest.getMissedUserSeq());
        String prompt = String.format(
                "실종자는 %d세 %s으로, 마지막으로 %s에서 목격되었습니다. 사용자의 현재 위치는 위도 %f, 경도 %f 입니다. 사용자의 반경 1km 이내에 실종자가 있을 가능성이 있는 위치를 예측해주세요. 예측된 위치의 위도와 경도를 알려주세요.",
                missingPerson.getAge(),
                missingPerson.getGender(),
                missingPerson.getLastSeenLocation(),
                locationRequest.getUserLocation().getLatitude(),
                locationRequest.getUserLocation().getLongitude()
        );

        // GptRequest에 프롬프트 및 필요한 매개변수 설정
        GptRequest gptRequest = GptRequest.builder()
                                .model("gpt-3.5-turbo")
                                .messages(List.of(
                                            Map.of("role", "user", "content", prompt)
                                ))
                                .max_tokens(1500)
                                .build();

        // GPT API 호출
        GptResponse gptResponse = gptClient.getPredictedLocations(gptRequest);

        // 예측된 위치 리스트 반환
        return gptResponse.getPredictedLocations();
    }

}

