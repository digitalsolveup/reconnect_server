package reconnect.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableFeignClients
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class ReconnectServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReconnectServerApplication.class, args);
    }

}
