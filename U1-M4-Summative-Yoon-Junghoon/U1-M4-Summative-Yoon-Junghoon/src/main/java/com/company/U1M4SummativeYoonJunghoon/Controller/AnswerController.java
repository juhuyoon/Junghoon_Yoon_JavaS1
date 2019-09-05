package com.company.U1M4SummativeYoonJunghoon.Controller;

import com.company.U1M4SummativeYoonJunghoon.Model.Answer;
import com.company.U1M4SummativeYoonJunghoon.Model.Question;
import com.company.U1M4SummativeYoonJunghoon.Service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/magic")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @PostMapping(value={})
    @ResponseStatus(value = HttpStatus.CREATED)
    public Answer askQuestion(@RequestBody @Valid Question question) {
        return answerService.returnWord();
    }
}
