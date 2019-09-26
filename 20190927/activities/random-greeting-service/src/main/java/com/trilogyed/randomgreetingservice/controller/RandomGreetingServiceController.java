package com.trilogyed.randomgreetingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class RandomGreetingServiceController {

    // something to hold our greetings
    private List<String> greetingList = new ArrayList<>();
    // so we can randomly return a greeting
    private Random rndGenerator = new Random();

    public RandomGreetingServiceController() {

        // some greetings
        greetingList.add("HiYa!");
        greetingList.add("Hello!!!");
        greetingList.add("Howdy!");
        greetingList.add("Greetings!");
        greetingList.add("Hi!!!!!");
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String getRandomGreeting() {

        // select and return a random greeting
        int whichGreeting = rndGenerator.nextInt(5);
        return greetingList.get(whichGreeting);
    }



}
