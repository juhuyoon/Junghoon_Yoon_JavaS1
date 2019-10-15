package com.company.REST.Producer.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingControllerImpl implements com.company.REST.Producer.GreetingController {

    @Override
    public String greeting(@PathVariable("username") String username) {
        return String.format("Hello %s!\n", username);
    }
}
