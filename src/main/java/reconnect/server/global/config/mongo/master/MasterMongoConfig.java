package reconnect.server.global.config.mongo.master;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reconnect.server.global.config.mongo.MongoConfig;

@Primary
@Configuration
@ConfigurationProperties(prefix = "datastore.mongodb.master")
public class MasterMongoConfig extends MongoConfig {
}
