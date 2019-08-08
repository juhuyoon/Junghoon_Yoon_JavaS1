package com.company;

import java.util.Scanner;

public class App {

    public static int subtract(int a, int b) {
        return (a - b);
    }

    public static int subtractOrZero(int a, int b) {
        if((a - b) < 0) {
            return 0;
        } else {
            return (a - b);
        }
    }

    public static int max(int a, int b, int c) {
        int max = Math.max(Math.max(a,b),c);
        return max;
    }

    public static int min(int a, int b, int c) {
        int min = Math.min(Math.min(a,b),c);
        return min;
    }

    public static boolean isLarger(int first, int second){
        boolean isLarger = false;
        if(first > second) {
            isLarger = true;
        }
        return isLarger;
    }

    public static int scanCall(String msg) {
        Scanner scan = new Scanner(System.in);
        int userInput = Integer.parseInt(scan.nextLine());
        return userInput;
    }


    public static void main(String[] args) {
//
//        int userInput = scanCall("3");
//
//        subtract(userInput, userInput);
//        subtractOrZero(userInput, userInput);
//        max(userInput, userInput, userInput);
//        min(userInput, userInput, userInput);
//        isLarger(userInput, userInput);

    }

}
