package com.company;

import java.util.Scanner;

public class HiLo {
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
        int guessNumber = (int) Math.ceil(Math.random() * 100);
        int counter = 0;
        int userGuess = 0;
        System.out.println("Welcome to Hi-Low!");
        System.out.println("Please enter your name:");
        String userName = scan.nextLine();
        System.out.println("OK, " + userName + ", here are the rules:\n");
        System.out.println("You will guess a number \n" +
                " And if you are correct, it will say Congratulations! \n" +
                " And let you know how many tries it took you! \n" +
                " We'll let you know if your number is too low or too high from the actual answer! \n" +
                "Input your number now and good luck!");

        System.out.println(guessNumber);

        do{
            userGuess = Integer.parseInt(scan.nextLine());
            if (userGuess < guessNumber) {
                counter++;
                System.out.println("Too low \n" + "Try again!");

            } else if (userGuess > guessNumber) {
                counter++;
                System.out.println("Too high! \n" + "Try again!");
            }
        }
        while(guessNumber != userGuess);
        System.out.println("Congratulations, " + userName + "! You win! \n");
        System.out.println("It took you " + counter + " guesses to find my number!");
    }
}
