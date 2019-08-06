package com.company;

import java.util.Scanner;

public class AddFive {

    public static void main(String[] args) {
        int sum = 0;
        int number;
        Scanner scan = new Scanner(System.in);
        int count = 0;
        while(count < 5){
            System.out.println("Type in your number: ");
            number = Integer.parseInt(scan.nextLine());
            sum = sum+number;
            count++;
        }
        System.out.println("Total Number is " + sum);
    }
}
