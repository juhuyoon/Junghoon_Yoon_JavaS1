package com.company.gamestoreconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class GameStoreConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameStoreConfigServerApplication.class, args);
	}

}
