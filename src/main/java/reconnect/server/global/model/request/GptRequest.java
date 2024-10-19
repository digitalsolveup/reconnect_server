package reconnect.server.global.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GptRequest {
    private String model;
    private String prompt;
    private int max_tokens;

}