package com.company;

import java.util.Scanner;

public class LoanCalculator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double mortPayment = 0;
        double loanAmount = 0;
        double month = 0;
        double monthlyInterestRate = 0;
        double actualRate = 0;
        //P = L[c(1+c)^n] / [(1+c)^n -1]  L = loan of dollars c = monthly interest rate.

        System.out.println("Insert your loan amount.");
        loanAmount = Integer.parseInt(scan.nextLine());
        System.out.println("Insert the amount of months on your payment.");
        month = Integer.parseInt(scan.nextLine());
        System.out.println("Insert the monthly interest rate.");

        monthlyInterestRate = (scan.nextDouble()/100);
        actualRate = (monthlyInterestRate/12);

        double exponential = Math.pow((1+actualRate), month);
        //System.out.println(exponential);



        mortPayment = (loanAmount * (actualRate * exponential) / (exponential -1));        System.out.println(mortPayment);

    }
}
