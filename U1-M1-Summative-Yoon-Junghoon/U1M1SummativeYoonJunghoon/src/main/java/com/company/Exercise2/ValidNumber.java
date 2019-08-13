package com.company.Exercise2;

import java.util.Scanner;

public class ValidNumber {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a number between 1 and 10.");
        int userNumber = Integer.parseInt(scan.nextLine());
        while(userNumber < 1 || userNumber > 10) {
            System.out.println("You must enter a number between 1 and 10, please try again.");
            userNumber = Integer.parseInt(scan.nextLine());
        }
        System.out.println(userNumber);
        scan.close();
    }
}
