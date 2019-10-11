package com.company.HelloWebServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class HelloWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWebServicesApplication.class, args);
	}

}
