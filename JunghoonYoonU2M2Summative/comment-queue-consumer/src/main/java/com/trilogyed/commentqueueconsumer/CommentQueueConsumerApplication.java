package com.trilogyed.commentqueueconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CommentQueueConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentQueueConsumerApplication.class, args);
	}

}
