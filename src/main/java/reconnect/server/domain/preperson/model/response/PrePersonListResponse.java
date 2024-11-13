package reconnect.server.domain.preperson.model.response;

import lombok.Data;
import reconnect.server.global.model.entity.mysql.PrePerson;
import reconnect.server.global.model.enums.RegistrationStatus;

@Data
public class PrePersonListResponse {
    private Long id;
    private String name;
    private RegistrationStatus status;

    // 생성자
    public PrePersonListResponse(PrePerson person) {
        this.id = person.getId();
        this.name = person.getName();
        this.status = person.getStatus();
    }
}