package com.company.MonthConverter.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class MonthConverterController {
    @GetMapping(value = "/month/{monthNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getMonthConverter(Principal principal, @PathVariable int monthNumber) {
        switch(monthNumber) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                throw new NumberFormatException("You must enter a whole number between 1 and 12.");
        }
    }
}
