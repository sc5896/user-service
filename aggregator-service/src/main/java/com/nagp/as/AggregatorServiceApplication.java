package com.nagp.as;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Springboot Application launcher class
 * 
 * @author santoshkumar02
 *
 */
@SpringBootApplication
public class AggregatorServiceApplication {

	/**
	 * Launches the application
	 * 
	 * @param args - Application startup arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AggregatorServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
