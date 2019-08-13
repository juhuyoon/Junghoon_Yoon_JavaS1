package IfStatements;

import java.util.Scanner;

public class ALittleQuiz_27 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int correctAnswer = 0;
        System.out.println("Are you ready for the quiz?");
        String enterQuiz = scan.nextLine().toUpperCase();
        if (enterQuiz.equals("Y")) {
            System.out.println("Q1) What is the capital of Alaska? \n" +
                                "1) Melbourne \n" +
                                "2) Anchorage \n" +
                                "3) Juneau \n");
            int userChoice = Integer.parseInt(scan.nextLine());
            if(userChoice == 3) {
                System.out.println("That's right!");
                correctAnswer++;
            } else {
                System.out.println("Sorry, the capital of Alaska is Juneau.");
            }

            System.out.println("Q2) Can you store the value `cat` in a variable of type int? \n" +
                    "1) yes \n" +
                    "2) no \n");
            int userChoice2 = Integer.parseInt(scan.nextLine());

            if(userChoice2 == 2) {
                System.out.println("That's right!");
                correctAnswer++;
            } else {
                System.out.println("Sorry, cat is a string. Ints can only store numbers");
            }


            System.out.println("Q3) What is the result of 9+6/3? \n" +
                               "1) 5 \n" +
                               "2) 11 \n " +
                               "3) 15/3 \n");
            int userChoice3 = Integer.parseInt(scan.nextLine());
            if(userChoice3 == 2) {
                System.out.println("That's correct!");
                correctAnswer++;
            } else {
                System.out.println("Sorry, the answer is 11.");
            }

            System.out.println("Overall, you got " + correctAnswer + " out of 3 correct. \n Thanks for playing!");
        } else {
            System.out.println("Aww too bad! Run to play again!");
        }
    }
}
