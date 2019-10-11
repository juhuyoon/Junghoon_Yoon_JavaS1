package com.company.WeatherAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class WeatherApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApiApplication.class, args);
	}

}
