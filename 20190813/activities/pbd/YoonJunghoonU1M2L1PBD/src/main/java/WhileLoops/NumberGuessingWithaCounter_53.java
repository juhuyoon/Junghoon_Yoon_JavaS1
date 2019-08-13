package WhileLoops;

import java.util.Scanner;

public class NumberGuessingWithaCounter_53 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int min = 1;
        int max = 10;
        int range = max - min +1;
        int randomNumber = ((int)(Math.random() * range) + min);
        int count = 0;

        System.out.println("I'm thinking of a number from 1 to 10.");
        System.out.println("Your guess: ");
        int userInput = Integer.parseInt(scan.nextLine());

        while(userInput != randomNumber) {
            System.out.println("This is incorrect. Guess again.");
            count++;
            userInput = Integer.parseInt(scan.nextLine());
        }
        System.out.printf("That's right! My secret number was " + randomNumber + ". \n");
        System.out.println("It only took you " + count + " tries.");
    }
}
