package reconnect.server.domain.search.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reconnect.server.domain.search.model.dto.MissingPersonDto;
import reconnect.server.domain.search.model.request.SearchRequest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static reconnect.server.global.model.entity.mysql.QMissingPerson.missingPerson;


@Repository
@RequiredArgsConstructor
public class SearchQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<MissingPersonDto> getSearchInfo(SearchRequest searchRequest, Pageable pageable){
        BooleanBuilder builder = getSearchBooleanBuilder(searchRequest);
        List<MissingPersonDto> list = queryFactory.select(
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
                        )
                )
                .from(missingPerson)
                .where(builder)
                .orderBy(missingPerson.lastSeenDateTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count = Optional.ofNullable(queryFactory
                        .select(missingPerson.count())
                        .from(missingPerson)
                        .where(builder)
                        .fetchFirst())
                .orElse(0L);

        return new PageImpl<>(list, pageable, count);
    }

    private BooleanBuilder getSearchBooleanBuilder(SearchRequest searchRequest){
        BooleanBuilder builder = new BooleanBuilder();

        if (ObjectUtils.isNotEmpty(searchRequest.getName())) {
            builder.and(missingPerson.name.contains(searchRequest.getName()));
        }
        if (ObjectUtils.isNotEmpty(searchRequest.getAge())) {
            builder.and(missingPerson.age.eq(searchRequest.getAge()));
        }
        if (ObjectUtils.isNotEmpty(searchRequest.getGender())) {
            builder.and(missingPerson.gender.eq(searchRequest.getGender()));
        }
        if (ObjectUtils.isNotEmpty(searchRequest.getSpecialFeature())) {
            builder.and(missingPerson.specialFeature.eq(searchRequest.getSpecialFeature()));
        }
        if (ObjectUtils.isNotEmpty(searchRequest.getStartDate()) && ObjectUtils.isNotEmpty(searchRequest.getEndDate())) {
            LocalDateTime startDateTime = searchRequest.getStartDate().atStartOfDay();
            LocalDateTime endDateTime = searchRequest.getEndDate().atTime(LocalTime.MAX);
            builder.and(missingPerson.lastSeenDateTime.between(startDateTime, endDateTime));
        }
        if (ObjectUtils.isNotEmpty(searchRequest.getLastSeenLocation())) {
            builder.and(missingPerson.lastSeenLocation.eq(searchRequest.getLastSeenLocation()));
        }

        return builder;
    }
}
