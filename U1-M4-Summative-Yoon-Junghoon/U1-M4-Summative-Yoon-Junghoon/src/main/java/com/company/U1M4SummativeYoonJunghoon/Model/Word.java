package com.company.U1M4SummativeYoonJunghoon.Model;

public class Word {
    //word and definition declaration
    private String word;
    private String definition;

    //default constructor
    public Word() {

    }

    //constructor
    public Word(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    //getters & setters
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
