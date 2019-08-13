package IfStatements;

import java.util.Scanner;

public class SpaceBoxing_26 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double Venus = 0.78;
        double Mars = 0.39;
        double Jupiter = 2.65;
        double Saturn = 1.17;
        double Uranus = 1.05;
        double Neptune = 1.23;

        System.out.println("Please enter your current earth weight: ");
        int earthWeight = Integer.parseInt(scan.nextLine());

        System.out.println("I have information for the following planets: \n" + "1. Venus   2. Mars   3. Jupiter   \n" +
                "4. Saturn   5. Uranus   6. Neptune");

        int userChoice = Integer.parseInt(scan.nextLine());

        switch(userChoice) {
            case(1) :
                System.out.println("Your weight would be " + (earthWeight*Venus) + " on that planet");
                break;
            case(2) :
                System.out.println("Your weight would be " + (earthWeight*Mars) + " on that planet");
                break;
            case(3) :
                System.out.println("Your weight would be " + (earthWeight*Jupiter) + " on that planet");
                break;
            case(4):
                System.out.println("Your weight would be " + (earthWeight*Saturn) + " on that planet");
                break;
            case(5):
                System.out.println("Your weight would be " + (earthWeight*Uranus) + " on that planet");
                break;
            case(6):
                System.out.println("Your weight would be " + (earthWeight*Neptune) + " on that planet");
                break;
            default:
                System.out.println("Please input one of the following choices.");
                userChoice = Integer.parseInt(scan.nextLine());
        }
    }
}
