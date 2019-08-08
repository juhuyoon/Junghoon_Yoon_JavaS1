package com.company;

import java.util.Scanner;

public class YourAgeCan {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("How old are you?");
        int userAge = Integer.parseInt(scan.nextLine());
        if(userAge >= 18 && userAge < 21) {
            System.out.println("Vote");
        } else if(userAge >= 21 && userAge < 35) {
            System.out.println("Vote");
            System.out.println("Drink Alcohol");
        } else if( userAge >= 35 && userAge < 55) {
            System.out.println("Vote");
            System.out.println("Drink alcohol");
            System.out.println("Be president");
        } else if(userAge >= 55 && userAge < 65) {
            System.out.println("Vote");
            System.out.println("Drink alcohol");
            System.out.println("Be president");
            System.out.println("Is eligible for AARP");
        } else if(userAge >= 65 && userAge < 80) {
            System.out.println("Vote");
            System.out.println("Drink alcohol");
            System.out.println("Be president");
            System.out.println("Is eligible for AARP");
            System.out.println("Can retire");
        } else if(userAge >=80 && userAge <= 89) {
            System.out.println("Vote");
            System.out.println("Drink alcohol");
            System.out.println("Be president");
            System.out.println("Is eligible for AARP");
            System.out.println("Can retire");
            System.out.println("Is an octogenerian");
        } else if(userAge >= 90 && userAge < 100) {
            System.out.println("Vote");
            System.out.println("Drink alcohol");
            System.out.println("Be president");
            System.out.println("Is eligible for AARP");
            System.out.println("Can retire");
        } else if(userAge >= 100) {
            System.out.println("Vote");
            System.out.println("Drink alcohol");
            System.out.println("Be president");
            System.out.println("Is eligible for AARP");
            System.out.println("Can retire");
            System.out.println("Is an octogenerian");
            System.out.println("Is more than a century old");
        }
    }
}
