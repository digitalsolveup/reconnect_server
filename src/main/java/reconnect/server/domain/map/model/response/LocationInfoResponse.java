package reconnect.server.domain.map.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.domain.map.model.dto.Geometry;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationInfoResponse {
    private String name;  // 장소 이름
    private String formatted_address;  // 장소 주소
    private Geometry geometry;
}
