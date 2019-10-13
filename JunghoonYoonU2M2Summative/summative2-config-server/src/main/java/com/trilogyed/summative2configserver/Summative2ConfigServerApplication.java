package com.trilogyed.summative2configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Summative2ConfigServerApplication {

	//Sets up the config server on localhost 1999
	public static void main(String[] args) {
		SpringApplication.run(Summative2ConfigServerApplication.class, args);
	}

}
