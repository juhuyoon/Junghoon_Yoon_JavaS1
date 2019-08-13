package com.company.Exercise4;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayFun5Words {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count = 0;
        String[] wordsArray = new String[5];
        for(int i = 0; i < wordsArray.length; i++) {
            if(count < 5) {
                System.out.println("Put in a word you want to put in the array!");
                String userInput = scan.nextLine();
                wordsArray[i] = userInput;
                count++;
            }
        }
        System.out.println(Arrays.toString(wordsArray));
    }
}
