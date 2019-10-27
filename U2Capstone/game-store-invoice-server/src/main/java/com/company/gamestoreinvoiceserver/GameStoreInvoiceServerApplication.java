package com.company.gamestoreinvoiceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GameStoreInvoiceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameStoreInvoiceServerApplication.class, args);
	}

}
