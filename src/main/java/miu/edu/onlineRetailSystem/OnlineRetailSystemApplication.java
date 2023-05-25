package miu.edu.onlineRetailSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class OnlineRetailSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineRetailSystemApplication.class, args);
    }

}
