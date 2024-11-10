package reconnect.server.domain.preperson.model.response;

import lombok.Data;
import reconnect.server.global.model.enums.RegistrationStatus;

@Data
public class PrePersonResponse {
    private Long id;
    private String name;
    private RegistrationStatus status;
}