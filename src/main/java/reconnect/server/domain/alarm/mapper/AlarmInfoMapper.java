package reconnect.server.domain.alarm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import reconnect.server.domain.alarm.model.request.AlarmRequest;
import reconnect.server.domain.alarm.model.vo.UserAlarmInfo;
import reconnect.server.global.mapper.EntityMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlarmInfoMapper extends EntityMapper<AlarmRequest, UserAlarmInfo> {
    AlarmInfoMapper INSTANCE = Mappers.getMapper(AlarmInfoMapper.class);
}
