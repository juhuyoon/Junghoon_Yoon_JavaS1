package com.company;

import java.util.Random;
import java.util.Scanner;

public class ThreeCardMonte_47 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        int randomChoice = rand.nextInt(4-1) + 1;

        System.out.println("You slide up to Fast Eddie's card table and plop down your cash.");
        System.out.println("He glances at you out of the corner of his eye and starts shuffling.");
        System.out.println("He lays down three cards.");
        System.out.println("Which is the ace?");

        System.out.println("##   ##   ##");
        System.out.println("##   ##   ##");
        System.out.println("1    2    3");

        int userInput = Integer.parseInt(scan.nextLine());
        if(userInput == 1) {
            if(userInput == randomChoice) {
                System.out.println("You nailed it!");
                System.out.println("AA   ##   ##");
                System.out.println("AA   ##   ##");
                System.out.println("1    2    3");
            } else if(userInput != randomChoice) {
                System.out.println("Ha! Fast Eddie wins again! The ace was card number " + randomChoice);
                if(randomChoice == 1) {
                    System.out.println("AA   ##   ##");
                    System.out.println("AA   ##   ##");
                    System.out.println("1    2    3");
                } else if (randomChoice == 2) {
                    System.out.println("##   AA   ##");
                    System.out.println("##   AA   ##");
                    System.out.println("1    2    3");
                } else {
                    System.out.println("##   ##   AA");
                    System.out.println("##   ##   AA");
                    System.out.println("1    2    3");
                }
            }
        } else if(userInput == 2) {
            if(userInput == randomChoice) {
                System.out.println("You nailed it!");
                System.out.println("##   AA   ##");
                System.out.println("##   AA   ##");
                System.out.println("1    2    3");
            } else if(userInput != randomChoice) {
                System.out.println("Ha! Fast Eddie wins again! The ace was card number " + randomChoice);
                if(randomChoice == 1) {
                    System.out.println("AA   ##   ##");
                    System.out.println("AA   ##   ##");
                    System.out.println("1    2    3");
                } else if (randomChoice == 2) {
                    System.out.println("##   AA   ##");
                    System.out.println("##   AA   ##");
                    System.out.println("1    2    3");
                } else {
                    System.out.println("##   ##   AA");
                    System.out.println("##   ##   AA");
                    System.out.println("1    2    3");
                }
            }
        } else if(userInput == 3) {
            if(userInput == randomChoice) {
                System.out.println("You nailed it!");
                System.out.println("##   ##   AA");
                System.out.println("##   ##   AA");
                System.out.println("1    2    3");
            } else if(userInput != randomChoice) {
                System.out.println("Ha! Fast Eddie wins again! The ace was card number " + randomChoice);
                if(randomChoice == 1) {
                    System.out.println("AA   ##   ##");
                    System.out.println("AA   ##   ##");
                    System.out.println("1    2    3");
                } else if (randomChoice == 2) {
                    System.out.println("##   AA   ##");
                    System.out.println("##   AA   ##");
                    System.out.println("1    2    3");
                } else {
                    System.out.println("##   ##   AA");
                    System.out.println("##   ##   AA");
                    System.out.println("1    2    3");
                }
            }
        } else {
            System.out.println("You did not input one of the options. Try again!");
        }
    }
}
