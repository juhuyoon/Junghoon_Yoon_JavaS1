package com.company.JunghoonYoonmagiceightballservice.Controller;

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
public class MagicEightController {




    private List<String> eightResponses = new ArrayList<>();
    Random rand = new Random();

    public void addResponses() {
        eightResponses.add("No");
        eightResponses.add("Yes");
        eightResponses.add("Looking cloudy");
        eightResponses.add("Not sure");
        eightResponses.add("Absolutely!");
        eightResponses.add("Ask again");
        eightResponses.add("Umm");
        eightResponses.add("Not a chance");
    }

    @GetMapping(value="/eightballanswer")
    @ResponseStatus(value = HttpStatus.OK)
    public String eightBalLController(){
        addResponses();
        int randomizer = rand.nextInt(8);
        return eightResponses.get(randomizer);
    }

}
