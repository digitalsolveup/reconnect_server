package reconnect.server.domain.alarm.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.domain.alarm.model.vo.UserAlarmInfo;
import reconnect.server.global.model.response.Response;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmResponse extends Response {
    public List<UserAlarmInfo> userAlarmInfos;
}

