package reconnect.server.global.config.mongo.user;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import reconnect.server.global.config.mongo.MongoConfig;

@Configuration
@ConfigurationProperties(prefix = "datastore.mongodb.user")
public class UserMongoConfig extends MongoConfig {
}
