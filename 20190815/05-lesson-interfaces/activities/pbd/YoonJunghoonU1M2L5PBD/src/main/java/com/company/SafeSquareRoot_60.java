package com.company;

import java.util.Scanner;

public class SafeSquareRoot_60 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("SQUARE ROOT");
        System.out.print("Enter a number: ");
        int userInput = Integer.parseInt(scan.nextLine());
        while(userInput != 0) {
            if(userInput < 0) {
                System.out.println("You can't take the square root of a negative number, silly.");
                break;
            } else {
                System.out.println("The square root of " + userInput + " is " + Math.sqrt(userInput));
                break;
            }
        }
    }
}
