package reconnect.server.domain.map.strategy;

import reconnect.server.global.model.entity.mysql.MissingPerson;

public interface KeywordStrategy {
    String getKeyword(MissingPerson person);
}


