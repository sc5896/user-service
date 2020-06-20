package com.nagp.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Springboot Application launcher class
 * 
 * @author santoshkumar02
 *
 */
@SpringBootApplication
public class UserServiceApplication {

	/**
	 * Launches the application
	 * 
	 * @param args
	 *            - Application startup arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
