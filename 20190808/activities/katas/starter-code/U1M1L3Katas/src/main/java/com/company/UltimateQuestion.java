package com.company;

import java.util.Scanner;

public class UltimateQuestion {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int userInput;
        do{
            System.out.println("Put in an input between 1 and 42!");
            userInput = Integer.parseInt(scan.nextLine());
        } while(userInput != 42);
        System.out.println("That's the number I was looking for! 42 is definitely the answer!");
    }
}
