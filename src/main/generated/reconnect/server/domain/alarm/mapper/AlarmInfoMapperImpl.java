package reconnect.server.domain.alarm.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reconnect.server.domain.alarm.model.request.AlarmRequest;
import reconnect.server.domain.alarm.model.vo.UserAlarmInfo;
import reconnect.server.domain.alarm.model.vo.UserAlarmInfo.UserAlarmInfoBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T17:43:01+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class AlarmInfoMapperImpl implements AlarmInfoMapper {

    @Override
    public UserAlarmInfo toEntity(AlarmRequest dto) {
        if ( dto == null ) {
            return null;
        }

        UserAlarmInfoBuilder userAlarmInfo = UserAlarmInfo.builder();

        userAlarmInfo.title( dto.getTitle() );
        userAlarmInfo.content( dto.getContent() );
        userAlarmInfo.redirectUrl( dto.getRedirectUrl() );

        return userAlarmInfo.build();
    }

    @Override
    public AlarmRequest toDto(UserAlarmInfo entity) {
        if ( entity == null ) {
            return null;
        }

        AlarmRequest alarmRequest = new AlarmRequest();

        alarmRequest.setTitle( entity.getTitle() );
        alarmRequest.setContent( entity.getContent() );
        alarmRequest.setRedirectUrl( entity.getRedirectUrl() );

        return alarmRequest;
    }

    @Override
    public List<UserAlarmInfo> toEntity(List<AlarmRequest> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<UserAlarmInfo> list = new ArrayList<UserAlarmInfo>( dtoList.size() );
        for ( AlarmRequest alarmRequest : dtoList ) {
            list.add( toEntity( alarmRequest ) );
        }

        return list;
    }

    @Override
    public List<AlarmRequest> toDto(List<UserAlarmInfo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AlarmRequest> list = new ArrayList<AlarmRequest>( entityList.size() );
        for ( UserAlarmInfo userAlarmInfo : entityList ) {
            list.add( toDto( userAlarmInfo ) );
        }

        return list;
    }
}
