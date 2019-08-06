package com.company;

import java.util.Scanner;

public class AddFiveAndDouble {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number you want to add 5 and double to: ");
        int number = Integer.parseInt(scan.nextLine());

        int finishedNumber = ((number+5)*2);

        System.out.println("Your final number is: " + finishedNumber);

    }
}
