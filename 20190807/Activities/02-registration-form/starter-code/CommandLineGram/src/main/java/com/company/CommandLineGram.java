package com.company;

import java.util.Scanner;

public class CommandLineGram {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your first name:");
        String firstName = scan.nextLine();
        System.out.println("Enter your last name:");
        String lastName = scan.nextLine();
        System.out.println("Enter your email:");
        String email = scan.nextLine();
        System.out.println("Enter your Twitter handle:");
        String twitter = scan.nextLine();
        System.out.println("Enter your age:");
        String age = scan.nextLine();
        System.out.println("Enter your Country:");
        String country = scan.nextLine();
        System.out.println("Enter your Profession:");
        String profession = scan.nextLine();
        System.out.println("Enter your Favorite Operating System:");
        String favOS = scan.nextLine();
        System.out.println("Enter your Favorite Programming Language");
        String favLang = scan.nextLine();
        System.out.println("Enter your Favorite Computer Scientist");
        String favSci = scan.nextLine();
        System.out.println("Enter your Favorite Keyboard Shortcut");
        String favKey = scan.nextLine();
        System.out.println("Have you ever built your own computer? Answer Yes or No");
        String buildComp = scan.nextLine();
            if(buildComp == "Yes") {
                System.out.println("Yes");
            } else if(buildComp == "No") {
                System.out.println("No");
            } else {
                System.out.println("Type in yes or no!");
            }
        System.out.println("If you could be any superhero, who would it be?");
            String superHero = scan.nextLine();

        System.out.println(
                firstName + "\n" +
                lastName + "\n" +
                email + "\n" +
                twitter + "\n" +
                age + "\n" +
                country + "\n" +
                profession + "\n" +
                favOS + "\n" +
                favLang + "\n" +
                favSci + "\n" +
                favKey + "\n" +
                buildComp + "\n" +
                superHero
        );
    }
}
