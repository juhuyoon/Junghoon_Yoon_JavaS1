package com.company;

import java.util.Scanner;

public class AddThirteen {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number you want to add 13 to:");
        int number = Integer.parseInt(scan.nextLine());

        int sum = (number + 13);

        System.out.println("Your sum total after adding 13 is: " + sum);
    }
}
