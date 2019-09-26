package com.trilogyed.hellocloudservice.util.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "random-greeting-service")
public interface RandomGreetingClient {

    @GetMapping(value ="/greeting")
    public String getRandomGreeting();
}
