package reconnect.server.domain.map.service;

import org.springframework.stereotype.Service;
import reconnect.server.global.model.entity.mysql.MissingPerson;
import reconnect.server.global.model.enums.Gender;
import reconnect.server.global.model.enums.PreferenceGroup;

import java.util.Map;
import java.util.function.Function;

@Service
public class KeywordService {
    private static final Map<Gender, Function<Integer, String>> genderKeywordMap = Map.of(
            Gender.MALE, age -> PreferenceGroup.fromAge(age).getKeyword(Gender.MALE),
            Gender.FEMALE, age -> PreferenceGroup.fromAge(age).getKeyword(Gender.FEMALE)
    );

    /**
     * 사용자 특성에 따라 장소 추천 키워드 반환
     */
    public String getKeyword(MissingPerson person) {
        return genderKeywordMap.get(person.getGender()).apply(person.getAge());
    }
}

