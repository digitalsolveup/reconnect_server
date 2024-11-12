package reconnect.server.domain.map.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reconnect.server.domain.search.model.dto.MissingPersonDto;

import java.util.List;

import static reconnect.server.global.model.entity.mysql.QMissingPerson.missingPerson;

@Repository
@RequiredArgsConstructor
public class MissingPersonQueryRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * 랜덤으로 지정된 개수의 MissingPerson 반환
     */
    public List<MissingPersonDto> findRandomMissingPersons(int limit) {

        return queryFactory.select(
                Projections.fields(MissingPersonDto.class,
                        missingPerson.id
                        , missingPerson.name
                        , missingPerson.imageURL
                        , missingPerson.gender
                        , missingPerson.specialFeature
                        , missingPerson.age
                        , missingPerson.height
                        , missingPerson.weight
                        , missingPerson.tops
                        , missingPerson.bottoms
                        , missingPerson.shoes
                        , missingPerson.accessories
                        , missingPerson.hair
                ))
                .from(missingPerson)
                .orderBy(Expressions.numberTemplate(Double.class, "function('RAND')").asc())
                .limit(limit)
                .fetch();
    }
}
