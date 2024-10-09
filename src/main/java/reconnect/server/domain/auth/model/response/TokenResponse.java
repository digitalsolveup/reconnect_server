package reconnect.server.domain.auth.model.response;

import lombok.*;
import reconnect.server.global.model.response.Response;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse extends Response {
    private String accessToken;
}
