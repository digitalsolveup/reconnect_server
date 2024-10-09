package reconnect.server.domain.alarm.model.request;

import lombok.Data;
import reconnect.server.domain.alarm.model.enums.AlarmType;
import reconnect.server.global.annotation.enums.EnumValue;

@Data
public class AlarmRequest {
    @EnumValue(enumClass = AlarmType.class, ignoreCase = true)
    AlarmType alarmType;

    public String title;

    public String content;

    public String redirectUrl;

}