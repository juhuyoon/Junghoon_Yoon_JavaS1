package com.company.Exercise4;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayFunUserArray {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] userArray = new int[5];
        int count = 5;
        for(int i = 0; i < userArray.length; i++) {
            if(count > 0) {
                System.out.println("Enter a number! Guesses: " + count);
                count--;

                int userInput = Integer.parseInt(scan.nextLine());
                userArray[i] = userInput;
            }
        }
        System.out.print(Arrays.toString(userArray));
    }
}
