package autoTrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AutoTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoTradeApplication.class, args);
    }

}
