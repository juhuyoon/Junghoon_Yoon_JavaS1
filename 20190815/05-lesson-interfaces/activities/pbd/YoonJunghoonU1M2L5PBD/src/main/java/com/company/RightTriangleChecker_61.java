package com.company;

import java.util.Scanner;

public class RightTriangleChecker_61 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int sideOne = 0;
        int sideTwo = 0;
        int sideThree = 0;

        System.out.println("Enter three integers: ");
        System.out.print("Side 1: ");
        int userInput = Integer.parseInt(scan.nextLine());
        sideOne = userInput;
        System.out.print("Side 2: ");
        userInput = Integer.parseInt(scan.nextLine());
        sideTwo = userInput;

        while (sideOne > sideTwo){
            System.out.println(sideTwo + " is smaller than " + sideOne + ". Try again.");
            userInput = Integer.parseInt(scan.nextLine());
            sideTwo = userInput;
            System.out.println("Side 2: " + sideTwo);
        };
        System.out.print("Side 3: ");
        userInput = Integer.parseInt(scan.nextLine());
        sideThree = userInput;
        while (sideThree < sideTwo) {
            System.out.println(sideThree + " is smaller than " + sideTwo + ". Try again.");
            userInput = Integer.parseInt(scan.nextLine());
            sideThree = userInput;
            System.out.println("Side 3: " + sideThree);
        }

            System.out.printf("Your three sides are %d, %d, %d", sideOne, sideTwo, sideThree);
            if(Math.pow(sideThree,2) == (Math.pow(sideOne,2) + Math.pow(sideTwo,2))) {
                System.out.println("\n These sides *do* make a right triangle. Yippy-skippy!");
            } else {
                System.out.println("\n NO! These sides do not make a right triangle!");
            }
        }
    }
