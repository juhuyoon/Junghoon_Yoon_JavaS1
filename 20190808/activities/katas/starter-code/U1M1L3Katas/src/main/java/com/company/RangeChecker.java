package com.company;

import java.util.Scanner;

public class RangeChecker {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Put in a number between 15 and 32 (not inclusive!)");
        int userInput = Integer.parseInt(scan.nextLine());

        do{
            System.out.println("Number is out of bounds. Try again");
            userInput = Integer.parseInt(scan.nextLine());
        }while((userInput < 15) || (userInput > 32));
        System.out.println(userInput);
    }
}
