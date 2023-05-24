package miu.edu.onlineRetailSystem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.admin.Config;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "miu.edu.onlineRetailSystem.repository")
@EntityScan(basePackages = "miu.edu.onlineRetailSystem.domain")
public class AppConfig {

	@Bean
	ModelMapper modelMapper() {
		// Maps any object to another object of similar structure such as Entity to Response
		return new ModelMapper();
	}

	@Bean
	ObjectMapper jsonMapper() {
		//Maps any object to JSON
		return new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .registerModule(new JavaTimeModule());
	}
	
}
