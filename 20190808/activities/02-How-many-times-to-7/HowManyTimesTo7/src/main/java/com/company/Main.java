package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int totalRolls = 0;
        int rollsToFirstSeven = 0;
        int totalRollsOfSeven = 0;
        int resultOfPair = 0;

        System.out.println("How many times do you want to roll the dice?");
        int userRoll = Integer.parseInt(scan.nextLine());

        System.out.println("Rolling A Pair of Dice...");

        do{
            Random randomNumber = new Random();
            int rollValue = randomNumber.nextInt(6) +1;
            int rollValue2 = randomNumber.nextInt(6) +1;
            resultOfPair = rollValue + rollValue2;
            System.out.println("First roll is: " + rollValue +
                                " Second roll is: " + rollValue2 +
                                " Result is: " + resultOfPair);
            totalRolls++;
            if(resultOfPair == 7) {
                totalRollsOfSeven++;
            }
            if((resultOfPair != 7)  && (totalRollsOfSeven == 0)) {
                rollsToFirstSeven++;
            }
        } while(totalRolls < (userRoll + 1));

        System.out.println("total Rolls of Seven: " + totalRollsOfSeven);
        System.out.println("Rolls to First Seven:" + rollsToFirstSeven);


    }
}
