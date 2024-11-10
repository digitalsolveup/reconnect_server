package reconnect.server.domain.map.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.domain.map.model.dto.Location;
import reconnect.server.global.model.request.GptRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest extends GptRequest {
    private Location userLocation;
    Long missedUserSeq;
}
