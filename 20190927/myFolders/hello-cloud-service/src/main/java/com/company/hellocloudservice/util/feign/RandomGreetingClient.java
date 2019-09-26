package com.company.hellocloudservice.util.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "random-greeting-service")
public interface RandomGreetingClient {

    @GetMapping("/greeting")
    public String getRandomGreeting();
}
