package com.company.WeatherAPI.Controllers;

import com.company.WeatherAPI.DTO.Condition;
import com.company.WeatherAPI.DTO.Temperature;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController

public class WeatherAPIController {
    @GetMapping(value = "/temp/{zipcode}")
    @ResponseStatus(value = HttpStatus.OK)

    public Temperature retTemperature(Principal principal, @PathVariable String zipcode) {
        Temperature currentTemp = new Temperature();
        currentTemp.setFahrenheit(80);
        currentTemp.setCelsius(20);

        return currentTemp;
    }

    @GetMapping(value = "/conditions/{zipcode}")
    @ResponseStatus(value = HttpStatus.OK)
    public Condition retCondition(Principal principal, @PathVariable int zipcode) {
        Condition currentCondition = new Condition();
        currentCondition.setCelsius(30);
        currentCondition.setFahrenheit(100);
        currentCondition.setPrecipitation("Rainy");
        currentCondition.setSkies("Cloudy");
        currentCondition.setWindDirection("North");
        currentCondition.setWindSpeed(100);

        return currentCondition;
    }


}
