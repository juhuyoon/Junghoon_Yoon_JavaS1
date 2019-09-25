package com.company.JunghoonYoonmagiceightballservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JunghoonYoonMagicEightBallServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunghoonYoonMagicEightBallServiceApplication.class, args);
	}

}
