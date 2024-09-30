package reconnect.server.auth.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;

    private String pw;
}
