package com.company;
import java.util.Scanner;

public class RectPavingCompany {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("What is the width of the driveway in feet? ");
        int width = Integer.parseInt(scan.nextLine());

        System.out.print("What is the length of the driveway in feet? ");
        int length = Integer.parseInt(scan.nextLine());

        float cementCost = 0.0f;
        float framingCost = 0.0f;

        System.out.print("What is the cost of cement per square foot? ");
        cementCost = Float.parseFloat(scan.nextLine());

        System.out.print("What is the cost of framing/footers per linear foot? ");
        framingCost = Float.parseFloat(scan.nextLine());

        int area = length * width;
        int perimeter = 2*length + 2*width;

        System.out.format("The area of the driveway is %d square feet.%n", area);
        System.out.format("The perimeter of the driveway is %d square feet.%n", perimeter);

        System.out.format("The cost of the cement is %f.%n", area*cementCost);
        System.out.format("The cost of the framing/footers is %f.%n", perimeter*framingCost);

        /* Test Cases:
            After taking in the width and length,
            area = length * width;
            perimeter = (2 * length) + (2 * width);
            Since the perimeter is coming back negative, issue with perimeter formula?
            The logic doesn't ask for the price of the floatingCost, but rather the cementCost...
            If width = 15, length = 10.
            Perimeter = 50
            Cost of framing = 3 per linear foot
            Total cost of framing = $150
            Area = 150
            Cost of cement = 5 per sqft
            Total cost of cement = 750
         */
    }
}
