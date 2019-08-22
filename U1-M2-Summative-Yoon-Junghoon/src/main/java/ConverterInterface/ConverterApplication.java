package ConverterInterface;

import java.util.Scanner;

public class ConverterApplication {
    public static void main(String[] args) {
        //Import of Scanner
        Scanner scan = new Scanner(System.in);

        //Instantiation of ConvertIf obj && ConverterSwitch obj
        ConverterIf ifClass = new ConverterIf();
        ConverterSwitch switchClass = new ConverterSwitch();

        //Gameplay using Scanner.
        System.out.println("Please put in a number between 1 and 12 to get the month for an if statement");
        System.out.println(ifClass.convertMonth(Integer.parseInt(scan.nextLine())));

        System.out.println("Please put in a number between 1 and 12 to get the month for a switch statment");
        System.out.println(switchClass.convertMonth(Integer.parseInt(scan.nextLine())));

        System.out.println("Please put in a number between 1 and 7 to get the day for an if statement");
        System.out.println(ifClass.convertDay(Integer.parseInt(scan.nextLine())));

        System.out.println("Please put in a number between 1 and 7 to get the day for a switch statement");
        System.out.println(switchClass.convertDay(Integer.parseInt(scan.nextLine())));
    }
}
