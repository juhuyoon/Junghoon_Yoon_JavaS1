package RandomNumbers;

import java.util.Random;

public class Dice_45 {
    public static void main(String[] args) {
        Random rand = new Random();
        int rollOne = 1 + rand.nextInt(6);
        int rollTwo = 1 + rand.nextInt(6);
        int sum = rollOne + rollTwo;

        System.out.println("Roll #1: " + rollOne);
        System.out.println("Roll #2: " + rollTwo);
        System.out.printf("The total is %d!", sum);
    }
}
