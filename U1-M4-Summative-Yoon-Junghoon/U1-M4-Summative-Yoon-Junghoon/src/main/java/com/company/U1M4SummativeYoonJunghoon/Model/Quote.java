package com.company.U1M4SummativeYoonJunghoon.Model;

public class Quote {

    //author and quote declarations.
    private String author;
    private String quote;

    //default constructor
    public Quote() {

    }

    //constructor
    public Quote(String author, String quote) {
        this.author = author;
        this.quote = quote;
    }

    //Getters and Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
