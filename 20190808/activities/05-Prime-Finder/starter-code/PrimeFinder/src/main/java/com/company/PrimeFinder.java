package com.company;

import java.util.Scanner;
//Print all the prime numbers.
//Remember: prime is the number only divisible by itself.
public class PrimeFinder {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Submit a number to check for primes.");
        int number = Integer.parseInt(scan.nextLine());

        for (int i = 2; i <= (number); i++) {
            boolean primeCheck = true;

            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    primeCheck = false;
                    break;
                }
            }
            if (primeCheck) {
                System.out.println(i);
            }
        }
    }
};