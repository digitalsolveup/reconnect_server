package reconnect.server.domain.map.model.response;

import lombok.Data;

import java.util.List;

@Data
public class LocationInfoListResponse {
    private List<LocationInfoResponse> results;
    private String status;
}
