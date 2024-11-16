package reconnect.server.domain.register.model.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PrePersonRegisterRequest {
    Long prePersonSeq;
    private double latitude;
    private double longitude;
}
