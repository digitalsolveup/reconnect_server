package reconnect.server.domain.map.strategy;

import reconnect.server.global.model.entity.mysql.MissingPerson;
import reconnect.server.global.model.enums.Gender;
import reconnect.server.global.model.enums.PreferenceGroup;

public class MaleKeywordStrategy implements KeywordStrategy {
    @Override
    public String getKeyword(MissingPerson person) {
        PreferenceGroup preferenceGroup = PreferenceGroup.fromAge(person.getAge());
        return preferenceGroup.getKeyword(Gender.MALE);
    }
}
