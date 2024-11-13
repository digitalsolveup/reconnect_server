package reconnect.server.global.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reconnect.server.global.config.gpt.GptClientConfig;
import reconnect.server.global.model.request.GptRequest;
import reconnect.server.global.model.response.GptResponse;
@FeignClient(name = "gptClient", url = "https://api.openai.com", configuration = GptClientConfig.class)
public interface GptClient {

    @PostMapping("/v1/chat/completions")
    GptResponse getPredictedLocations(@RequestBody GptRequest request);
}


