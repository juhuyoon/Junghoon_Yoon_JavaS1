package WhileLoops;

import java.util.Random;
import java.util.Scanner;

public class Hi_LoWithLimitedTries_54 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int number = 1 + rand.nextInt(100);
        int count = 7;
        int tries = 1;
        System.out.println("I'm thinking of a number between 1 and 100. You have 7 guesses.");
        int userGuess = Integer.parseInt(scan.nextLine());
        System.out.println("First guess: " + userGuess);
        System.out.println(number);
        while(userGuess != number && count != 0) {
            tries++;
            count--;
            if(userGuess < number) {
                System.out.println("Guess # = " + tries);
                System.out.println("Sorry, that guess is too low.");
            } else if (userGuess > number) {
                System.out.println("Guess # = " + tries);
                System.out.println("Sorry, that guess is too high.");
            }
            userGuess = Integer.parseInt(scan.nextLine());
        }
        if(userGuess == number) {
            System.out.println("You guessed it! What are the odds?");
        } else if(count == 0) {
            System.out.println("Sorry, you didn't guess it in 7 tries. You lose.");
        }
    }
}
