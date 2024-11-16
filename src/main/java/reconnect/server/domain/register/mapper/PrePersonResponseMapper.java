package reconnect.server.domain.register.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import reconnect.server.domain.preperson.model.response.PrePersonResponse;
import reconnect.server.global.mapper.EntityMapper;
import reconnect.server.global.model.entity.mysql.PrePerson;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrePersonResponseMapper extends EntityMapper<PrePersonResponse, PrePerson> {
    PrePersonResponseMapper INSTANCE = Mappers.getMapper(PrePersonResponseMapper.class);
}
