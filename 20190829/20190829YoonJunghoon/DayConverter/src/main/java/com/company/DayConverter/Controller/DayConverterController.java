package com.company.DayConverter.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class DayConverterController {

    @GetMapping(value = "/day/{dayNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String dayConverter(@PathVariable int dayNumber) {
        switch(dayNumber) {
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
            default:
                throw new NumberFormatException("Please input in a number between 1 and 7");
        }
    }
}
