package com.company.U1M4SummativeYoonJunghoon.Service;

import com.company.U1M4SummativeYoonJunghoon.Model.Word;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Business Logic
@Service
public class WordService {

    // Setting arraylist
   private static List<Word> wordList = new ArrayList<>();

    // Setting preset Word to add to arraylist.
    static {
        wordList.add(new Word("Cheese", "Blocks of Life"));
        wordList.add(new Word("Water", "Springs of Life"));
        wordList.add(new Word("Marker", "Writes stuff"));
        wordList.add(new Word("Rice", "Carbs of everything"));
        wordList.add(new Word("Lemonade", "Tastes like lemon"));
        wordList.add(new Word("Chicken", "Very Good"));
        wordList.add(new Word("Monkeys", "My ancestor"));
        wordList.add(new Word("Notebook", "Something to write in"));
        wordList.add(new Word("Glasses", "Improves eyesight"));
        wordList.add(new Word("Chairs", "Something to sit in"));
    }


    //Retrieve word by random.
    public Word returnWordByRandom() {
        Random rand = new Random();
        Integer randNum = rand.nextInt(10-1) + 1;
        return wordList.get(randNum);
    }
}
