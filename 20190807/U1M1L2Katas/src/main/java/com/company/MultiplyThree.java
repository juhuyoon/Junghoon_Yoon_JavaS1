package com.company;

import java.util.Scanner;

public class MultiplyThree {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter first number:");
        int firstNum = Integer.parseInt(scan.next());
        System.out.println("Enter second number:");
        int secondNum = Integer.parseInt(scan.next());
        System.out.println("Enter third number:");
        int thirdNum = Integer.parseInt(scan.next());

        int product = (firstNum * secondNum * thirdNum);

        System.out.println("The product of the three numbers are: " + product);
    }
}