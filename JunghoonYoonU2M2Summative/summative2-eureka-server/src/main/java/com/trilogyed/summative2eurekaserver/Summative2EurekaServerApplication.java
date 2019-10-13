package com.trilogyed.summative2eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Summative2EurekaServerApplication {

	// Sets up the Eureka Service Registry on Default Port
	public static void main(String[] args) {
		SpringApplication.run(Summative2EurekaServerApplication.class, args);
	}

}
