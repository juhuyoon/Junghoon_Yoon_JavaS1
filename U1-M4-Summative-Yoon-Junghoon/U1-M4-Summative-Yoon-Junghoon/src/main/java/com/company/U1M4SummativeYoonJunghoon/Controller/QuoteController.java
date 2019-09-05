package com.company.U1M4SummativeYoonJunghoon.Controller;

import com.company.U1M4SummativeYoonJunghoon.Model.Quote;
import com.company.U1M4SummativeYoonJunghoon.Service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

    @Autowired
    QuoteService quoteService;

    /**
     * Return a preset number of quotes with authors displaying randomly.
     * @return quote
     */
    @GetMapping(value="/quote")
    @ResponseStatus(value = HttpStatus.OK)
    public Quote getQuoteByRandom() {
        return quoteService.returnQuoteByRandom();
    }
}
