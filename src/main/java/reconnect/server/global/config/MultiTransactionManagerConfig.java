package reconnect.server.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class MultiTransactionManagerConfig {

    private final DataSource dataSource;
    private final MongoDatabaseFactory mongoDbFactory;

    public MultiTransactionManagerConfig(DataSource dataSource, MongoDatabaseFactory mongoDbFactory) {
        this.dataSource = dataSource;
        this.mongoDbFactory = mongoDbFactory;
    }

    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mongoTransactionManager")
    public MongoTransactionManager mongoTransactionManager() {
        return new MongoTransactionManager(mongoDbFactory);
    }
}
