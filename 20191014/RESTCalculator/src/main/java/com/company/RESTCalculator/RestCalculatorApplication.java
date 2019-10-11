package com.company.RESTCalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class RestCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestCalculatorApplication.class, args);
	}

}
