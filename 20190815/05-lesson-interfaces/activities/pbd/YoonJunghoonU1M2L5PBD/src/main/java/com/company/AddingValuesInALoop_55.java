package com.company;

import java.util.Scanner;

public class AddingValuesInALoop_55 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int sum = 0;
        int number = 0;
        System.out.println("I will add up the numbers you give me.");
        int userInput = Integer.parseInt(scan.nextLine());
        number = userInput;
        sum += number;
        System.out.println("Number: " + number);
        System.out.println("The total so far is: " + sum);

        while(number != 0){
            userInput = Integer.parseInt(scan.nextLine());
            number = userInput;
            sum += number;
            System.out.println("Number: " + number);
            System.out.println("The total so far is: " + sum);
        };
        System.out.println("The total is " + sum);
    }
}
