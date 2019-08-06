package com.company;

import java.util.Scanner;

public class SubtractTwo {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input first number");
        int firstNum = Integer.parseInt(scan.nextLine());
        System.out.println("Input second number");
        int secondNum = Integer.parseInt(scan.nextLine());
        int difference = (firstNum - secondNum);
        System.out.println("The difference is: " + difference);

    }
}
