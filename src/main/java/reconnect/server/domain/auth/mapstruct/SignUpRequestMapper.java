package reconnect.server.domain.auth.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import reconnect.server.domain.auth.model.request.SignUpRequest;
import reconnect.server.global.mapper.EntityMapper;
import reconnect.server.global.model.entity.mysql.UserInfo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SignUpRequestMapper extends EntityMapper<SignUpRequest, UserInfo> {
    SignUpRequestMapper INSTANCE = Mappers.getMapper(SignUpRequestMapper.class);
}
