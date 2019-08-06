package com.company;

import java.util.Scanner;

public class RectPavingCompany {

    public static void main(String[] args) {
        //==================== VERSION 1 ===========================
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the width of the driveway in feet.");
        double width = Double.parseDouble(scan.nextLine());
        System.out.println("Enter the length of the driveway in feet.");
        double length = Double.parseDouble(scan.nextLine());


        double area = (width * length);
        double perimeter = ((width*2) + (length*2));

        System.out.println("The area is " + area + "\n");
        System.out.println("The perimeter is " + perimeter + "\n");

        System.out.format("%.2f", (area * 12.50));
        System.out.format("%.2f", (perimeter * 8.25));

        //==================== VERSION 2 ===========================

        double cementCost;
        double frameFooterCost;

        System.out.println("Enter the cost of the cement:");
            cementCost = Double.parseDouble(scan.nextLine() + "\n");
        System.out.println("Enter the cost of the framing/footers:");
            frameFooterCost = Double.parseDouble(scan.nextLine() + "\n");
        System.out.println("The total cost of cement is " + (area*cementCost));
        System.out.println("The total cost of the framing/footers is " + (perimeter * frameFooterCost));


    }
}
