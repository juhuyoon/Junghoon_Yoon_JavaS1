package com.company.JunghoonYoonrandomquoteservice.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@RestController
@RefreshScope
@ResponseStatus
public class RandomQuoteController {
    @Value("${randomQuotes}")
    private ArrayList randomQuotes;

    Random rand = new Random();

    @GetMapping(value="/quotes")
    @ResponseStatus(value= HttpStatus.OK)
    public Object getQuotes() {
        return randomQuotes.get(rand.nextInt(randomQuotes.size()));

    }


}
