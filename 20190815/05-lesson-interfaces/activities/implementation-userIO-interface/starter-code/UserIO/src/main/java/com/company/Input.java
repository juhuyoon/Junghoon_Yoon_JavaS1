package com.company;

import com.company.interfaces.UserIO;

import java.util.Scanner;

public class Input implements UserIO {
    Scanner scan = new Scanner(System.in);
    @Override
    public int readInt(String prompt) {
        int userInput = Integer.parseInt(scan.nextLine());
        return (int) userInput;
    }

    @Override
    public long readLong(String prompt) {
        long userInput = Long.parseLong(scan.nextLine());
        return (long) userInput;
    }

    @Override
    public double readDouble(String prompt) {
        double userInput = Double.parseDouble(scan.nextLine());
        return (double) userInput;
    }

    @Override
    public float readFloat(String prompt) {
        float userInput = Float.parseFloat(scan.nextLine());
        return (float) userInput;
    }

    @Override
    public String readString(String prompt) {
        String userInput = scan.nextLine();
        return (String) userInput;
    }
}
