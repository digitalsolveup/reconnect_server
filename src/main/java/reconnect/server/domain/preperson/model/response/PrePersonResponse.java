package reconnect.server.domain.preperson.model.response;

import lombok.Data;
import reconnect.server.global.model.entity.mysql.PrePerson;
import reconnect.server.global.model.enums.RegistrationStatus;

@Data
public class PrePersonResponse {
    private Long id;
    private String name;
    private RegistrationStatus status;

    // 엔티티를 받아서 DTO로 변환하는 생성자 추가
    public PrePersonResponse(PrePerson person) {
        this.id = person.getId();
        this.name = person.getName();
        this.status = person.getStatus();
    }
}

