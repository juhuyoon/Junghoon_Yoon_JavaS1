package com.company.JunghoonYooncloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class JunghoonYoonCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunghoonYoonCloudConfigServerApplication.class, args);
	}

}
