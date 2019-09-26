package com.company.randomgreetingservice.Controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RefreshScope
@ResponseStatus
public class RandomGreetingController {

    private List<String> greetingList = new ArrayList<>();

    private Random rand = new Random();

    public void RandomGreetingServiceController() {
        greetingList.add("HiYa");
        greetingList.add("Hello");
        greetingList.add("Howdy!");
        greetingList.add("AYAYA");
        greetingList.add("Moshi Moshi");
    }

    @GetMapping(value="/greeting")
    @ResponseStatus(value= HttpStatus.OK)
    public String getRandomGreeting() {
        RandomGreetingServiceController();
        int randomizer = rand.nextInt(5);
        return greetingList.get(randomizer);
    }
}
