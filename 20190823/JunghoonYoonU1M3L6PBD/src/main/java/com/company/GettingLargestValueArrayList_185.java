package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class GettingLargestValueArrayList_185 {
    public static void main(String[] args) {
        ArrayList<Integer> myArray = new ArrayList<>();
        for(int i = myArray.size(); i < 10; i++) {
            int min = 1;
            int max = 50;
            int range = (max - min) + 1;
            int randomA = (int)(Math.random() * range) + min;

            myArray.add(randomA);
        }
        System.out.println("ArrayList: "+ myArray);
        Object max = Collections.max(myArray);
        System.out.println("The largest value is " + max);
    }
}
