package com.company.U1M4SummativeYoonJunghoon.Service;

import com.company.U1M4SummativeYoonJunghoon.Model.Quote;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Business Logic
@Service
public class QuoteService {

    //Creating an arraylist.
    private static List<Quote> quoteList = new ArrayList<>();

    //Adding preset Quote to quoteList
    static {
        quoteList.add(new Quote("Hemingway", "Boats are cool."));
        quoteList.add(new Quote("Zappa", "So many books, so little time."));
        quoteList.add(new Quote("Snicket", "Never trust anyone who has not brought a book with them."));
        quoteList.add(new Quote("Twain", "Classic - a book which people praise and don't read."));
        quoteList.add(new Quote("King", "Books are a uniquely portable magic."));
        quoteList.add(new Quote("Herz", "Be careful about reading health books. Some fine days you'll die of a misprint."));
        quoteList.add(new Quote("Austen", "I shall be miserable if I have not an excellent library."));
        quoteList.add(new Quote("Wilde", "The books that the world calls immoral are books that show the world its own shame."));
        quoteList.add(new Quote("Erasmus", "When I have a little money, I buy books; and if I have any left, I buy food and clothes."));
        quoteList.add(new Quote("Alcott", "She is too fond of books, and it has turned her brain."));
        quoteList.add(new Quote("Beecher", "WHere is human nature so weak as in the bookstore?"));
    }

    //Retrieve Quote by random.
    public Quote returnQuoteByRandom() {
        Random rand = new Random();
        Integer randNum = rand.nextInt(10) + 1;
        return quoteList.get(randNum);
    }
}
