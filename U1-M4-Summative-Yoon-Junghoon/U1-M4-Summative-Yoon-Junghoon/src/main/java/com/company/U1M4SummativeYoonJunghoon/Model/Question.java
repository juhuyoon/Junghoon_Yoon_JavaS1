package com.company.U1M4SummativeYoonJunghoon.Model;

import javax.validation.constraints.NotEmpty;

public class Question {
    //declaration of question
    @NotEmpty(message = "Enter a question!")
    private String question;

    //Default Constructor
    public Question() {

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
