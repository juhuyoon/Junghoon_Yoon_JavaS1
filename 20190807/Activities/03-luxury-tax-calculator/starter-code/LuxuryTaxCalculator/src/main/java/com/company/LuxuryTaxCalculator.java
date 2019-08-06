package com.company;

import java.util.Scanner;

public class LuxuryTaxCalculator {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter your Player 1's Salary:");
        int salaryP1 = Integer.parseInt(scan.nextLine());
        System.out.println("Enter your Player 2's Salary:");
        int salaryP2 = Integer.parseInt(scan.nextLine());
        System.out.println("Enter your Player 3's Salary:");
        int salaryP3 = Integer.parseInt(scan.nextLine());

        int totalSalary = (salaryP1 + salaryP2 + salaryP3);
        System.out.println(totalSalary);
        if(totalSalary > 40000000) {
           double payTax = ((totalSalary - 40000000) * 0.18);
            System.out.println(payTax);
        } else {
            System.out.println(totalSalary);
        }

    }
}
