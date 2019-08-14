package com.company;

import java.util.Random;

public class DiceDoubles_50 {
    public static void main(String[] args) {
        Random rand = new Random();
        int randomDice1 = rand.nextInt((7-1)) + 1;
        int randomDice2 = rand.nextInt((7-1)) + 1;
        int sum = randomDice2 + randomDice1;
        while(randomDice1 != randomDice2) {
            System.out.println("Roll #1: " + randomDice1 + "\n");
            System.out.println("Roll #2: " + randomDice2 + "\n");
            System.out.println("The total is: " + sum + "! \n");
            randomDice1 = rand.nextInt((7 - 1)) + 1;
            randomDice2 = rand.nextInt((7 - 1)) + 1;
            sum = randomDice1 + randomDice2;
        }
        System.out.println("Roll #1: " + randomDice1 + "\n");
        System.out.println("Roll #2: " + randomDice2 + "\n");
        System.out.println("The total is: " + sum + "! \n");
    }
}
