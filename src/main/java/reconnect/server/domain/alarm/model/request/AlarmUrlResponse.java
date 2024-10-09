package reconnect.server.domain.alarm.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.global.model.response.Response;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmUrlResponse extends Response {
    // Redirect url
    public String redirectUrl;
}

