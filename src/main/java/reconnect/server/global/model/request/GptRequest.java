package reconnect.server.global.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GptRequest {
    private String model;
    private List<Map<String, String>> messages;
    private int max_tokens;

}