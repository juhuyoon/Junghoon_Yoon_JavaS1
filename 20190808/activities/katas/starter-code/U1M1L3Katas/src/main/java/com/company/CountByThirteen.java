package com.company;

import java.util.Scanner;

public class CountByThirteen {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count = 0;

        System.out.println("Type in what number you want to count to:");
        int userNumber = Integer.parseInt(scan.nextLine());

        for(int i = 0; i < userNumber+1; i++) {
            if(count <= userNumber) {
                System.out.println(count);
                count = (count + 13);
            }
        }

    }
}