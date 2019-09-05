package com.company.U1M4SummativeYoonJunghoon.Controller;

import com.company.U1M4SummativeYoonJunghoon.Model.Word;
import com.company.U1M4SummativeYoonJunghoon.Service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordController {

    @Autowired
    WordService wordService;

    /**
     * Return a preset number of words with their definitions in a random order.
     * @return word
     */
    @GetMapping(value="/word")
    @ResponseStatus(value = HttpStatus.OK)
    public Word getWordByRandom() {
        return wordService.returnWordByRandom();
    }



}
