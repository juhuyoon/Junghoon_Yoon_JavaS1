package com.company;

import java.util.Scanner;

public class DoubleAndAddFive {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the first number to be doubled:");
        int doubleNum = (Integer.parseInt(scan.nextLine())*2);
        int finalNumber = (doubleNum + 5);

        System.out.println("The final number after doubling and adding 5 is: " + finalNumber);

    }
}
