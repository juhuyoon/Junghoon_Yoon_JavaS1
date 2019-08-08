package com.company;

import java.util.Scanner;

public class FavoriteProgrammingLanguage {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String userInput;
        do {
            System.out.println("Input the name of your favorite coding language!");
            userInput = scan.nextLine();
        } while(!userInput.equals("Java"));
        System.out.println("That's what I was looking for! Java is definitely the answer!");
    }
}
