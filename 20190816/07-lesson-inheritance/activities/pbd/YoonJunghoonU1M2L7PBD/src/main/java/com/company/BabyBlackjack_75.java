package com.company;

import java.util.Random;

public class BabyBlackjack_75 {

    public static void main(String[] args) {
        Random rand = new Random();
       int playerCards1 = (rand.nextInt(10-1) + 1);
       int playerCards2 = (rand.nextInt(10-1) + 1);
       int dealerCards1 = (rand.nextInt(10-1) + 1);
       int dealerCards2 = (rand.nextInt(10-1) + 1);
       int playerSum = playerCards1 + playerCards2;
       int dealerSum = dealerCards1 + dealerCards2;

        System.out.println("Baby Blackjack!");
        System.out.println("You drew " + playerCards1 + " and " + playerCards2);
        System.out.println("Your total is " + playerSum);

        System.out.println(" ");

        System.out.println("The dealer has " + dealerCards1 + " and " + dealerCards2);
        System.out.println("Dealer's total is " + dealerSum);

        if(playerSum > dealerSum) {
            System.out.println("YOU WIN!");
        } else {
            System.out.println("DEALER WINS!");
        }
    }
}
