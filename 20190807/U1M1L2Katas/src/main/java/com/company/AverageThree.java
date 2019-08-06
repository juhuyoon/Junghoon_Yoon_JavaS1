package com.company;

import java.util.Scanner;

public class AverageThree {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double x = Double.parseDouble(scan.nextLine());

        double y = Double.parseDouble(scan.nextLine());

        double z = Double.parseDouble(scan.nextLine());

        System.out.format("%.2f", (x + y + z)/3.00);
    }
}
