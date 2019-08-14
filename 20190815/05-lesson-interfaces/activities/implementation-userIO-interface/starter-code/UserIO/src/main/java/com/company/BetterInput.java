package com.company;

import com.company.interfaces.UserIO;

import java.util.Scanner;


public class BetterInput implements UserIO {
    Scanner scan = new Scanner(System.in);

    @Override
    public int readInt(String prompt) {
        int userInput = 0;
        try {
            userInput = Integer.parseInt(scan.nextLine());
            return (int) userInput;
        } catch(Exception e) {
            System.out.println(e);
            return this.readInt(prompt);
        }
    }

    @Override
    public long readLong(String prompt) {
        long userInput = 0;
        try {
            userInput = Long.parseLong(scan.nextLine());
            return (long) userInput;
        } catch(Exception e) {
            System.out.println(e);
            return this.readLong(prompt);
        }
    }

    @Override
    public double readDouble(String prompt) {
        double userInput = 0;
        try{
            userInput = Double.parseDouble(scan.nextLine());
            return (double) userInput;
        } catch (Exception e) {
            System.out.println(e);
            return this.readDouble(prompt);
        }
    }

    @Override
    public float readFloat(String prompt) {
        float userInput = 0;
        try {
            userInput = Float.parseFloat(scan.nextLine());
            return (float) userInput;
        } catch (Exception e) {
            System.out.println(e);
            return this.readFloat(prompt);
        }
    }

    @Override
    public String readString(String prompt) {
        String userInput = null;
        try {
            userInput = scan.nextLine();
            return userInput;
        } catch (Exception e) {
            System.out.println(e);
            return this.readString(prompt);
        }
    }
}
