package reconnect.server.global.repository.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import reconnect.server.global.repository.AbstractMongoRepository;

public abstract class UserMongoRepository<T> extends AbstractMongoRepository<T> {

    @Autowired
    @Qualifier("userMongoTemplate")
    private MongoTemplate userMongoTemplate;

    @Override
    public MongoTemplate getMongoTemplate() {
        return userMongoTemplate;
    }
}
