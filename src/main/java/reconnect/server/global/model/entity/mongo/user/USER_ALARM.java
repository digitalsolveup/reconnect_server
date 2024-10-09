package reconnect.server.global.model.entity.mongo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import reconnect.server.domain.alarm.model.vo.UserAlarmInfo;
import reconnect.server.global.constants.MongoField;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "USER_ALARM")
public class USER_ALARM {
    @Id
    @Field(MongoField._ID)
    String _id;
    @Field(MongoField.USER_ID)
    Long userId;
    @Field(MongoField.ALARM_INFOS)
    List<UserAlarmInfo> alarmInfos;
    @Field(MongoField.CREATE_TIME)
    private LocalDate createTime;
}
