package com.company.U1M4SummativeYoonJunghoon.Service;

import com.company.U1M4SummativeYoonJunghoon.Model.Answer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Business Logic
@Service
public class AnswerService {

    private static List<Answer> answerList = new ArrayList<>();

    static {
        answerList.add(new Answer("It is certain."));
        answerList.add(new Answer("Most likely."));
        answerList.add(new Answer("Ask again later."));
        answerList.add(new Answer("Better not tell you now."));
        answerList.add(new Answer("Don't count on it. "));
        answerList.add(new Answer("Very doubtful."));
    }



    //Retrieve word by ID, for random.
    public Answer returnWord() {
        Random rand = new Random();
        Integer randNum = rand.nextInt(6-1) + 1;
        return answerList.get(randNum);
    }
}
