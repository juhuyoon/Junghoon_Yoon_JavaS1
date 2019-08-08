package com.company;

import java.util.Scanner;

public class AgeAgain {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("How old are you?");
        int userAge = Integer.parseInt(scan.nextLine());
        if(userAge < 14) {
            System.out.println("Cool! Then what grade are you in?");
            String userGrade = scan.nextLine();
            System.out.println("Wow! " + userGrade + " grade - that sounds exciting!");
        } else if (userAge >= 14 && userAge <= 18) {
            System.out.println("Are you planning on going to college? Answer yes or no.");
            String userCollege = scan.nextLine();
            if(userCollege.equals("yes")) {
                System.out.println("Great! What college are you planning on going to?");
                String userChoiceCollege = scan.nextLine();
                System.out.println(userChoiceCollege + " is a great school!");
            } else if(userCollege.equals("no")) {
                System.out.println("Then what do you want to do after high school?");
                String userChoiceOutOfSchool = scan.nextLine();
                System.out.println("Wow, " + userChoiceOutOfSchool + " sounds like a plan!");
            }
        } else if (userAge > 18) {
            System.out.println("Then what is your job right now?");
            String userJob = scan.nextLine();
            System.out.println(userJob + " sounds like a great job!");
        }

    }
}
