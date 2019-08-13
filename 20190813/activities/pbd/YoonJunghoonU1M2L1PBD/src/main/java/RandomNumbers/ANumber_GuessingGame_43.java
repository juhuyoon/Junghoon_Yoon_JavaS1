package RandomNumbers;

import java.util.Scanner;

public class ANumber_GuessingGame_43 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int min = 1;
        int max = 10;
        int range = max - min +1;
        int randomNumber = ((int)(Math.random() * range) + min);

        System.out.println("I'm thinking of a number from 1 to 10.");
        System.out.println("Your guess: ");
        int userInput = Integer.parseInt(scan.nextLine());

        if(userInput != randomNumber) {
            System.out.printf("Sorry! but I was really thinking of " + randomNumber);
        } else {
            System.out.printf("That's right! My secret number was " + randomNumber);
        }
    }
}
