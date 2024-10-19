package reconnect.server.global.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.domain.map.model.dto.Location;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GptResponse {
    private List<Location> predictedLocations;
}
