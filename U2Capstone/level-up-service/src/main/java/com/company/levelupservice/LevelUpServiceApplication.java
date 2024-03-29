package com.company.levelupservice;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class LevelUpServiceApplication {

	public static final String TOPIC_EXCHANGE_NAME = "level-up-exchange";
	public static final String QUEUE_NAME = "level-up-reward-queue";
	public static final String ROUTING_KEY = "level.up.reward.#";

	@Bean
	Queue queue(){
		return new Queue(QUEUE_NAME, false);
	}

	@Bean
	TopicExchange exchange(){
		return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
	}

	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}


	public static void main(String[] args) {
		SpringApplication.run(LevelUpServiceApplication.class, args);
	}

}
