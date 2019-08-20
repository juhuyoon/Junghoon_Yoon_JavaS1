package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {

    public static int total (ArrayList<Integer> numbers) {
        List<Integer> totalArray = new ArrayList<>();

        for(int num : numbers) {
            totalArray.add(num);
        }
        int sum = 0;
        for(int i = 0; i < totalArray.size(); i++) {
            sum += totalArray.get(i);
        }
        return sum;
    }

    public static int totalEven (ArrayList<Integer> numbers) {

        int sum = 0;
        for(int i = 0; i < numbers.size(); i += 2) {
            sum += numbers.get(i);
        }

        return sum;
    }

    public static List<String> swapFirstAndLast(ArrayList<String> strings) {
        List<String> newArray = new ArrayList<>();

        for(int i = 0; i < strings.size(); i++) {
            newArray.add(i, strings.get(i));
        }
            int value = (strings.size() -1);

        Collections.swap(newArray, 0, value);
        return newArray;

    }

    public static List<Integer> reverse(ArrayList<Integer> numbers) {
        List<Integer> newArray = new ArrayList<>();

        for(int i = 0; i < numbers.size(); i++) {

            newArray.add(i, numbers.get(i));
        }
        Collections.reverse(newArray);

        return newArray;
    }

    public static List<Integer> lessThanFive(ArrayList<Integer> numbers) {

        List<Integer> newArray = new ArrayList<>();

        for(Integer num: numbers) {
            if(num < 5) {
                newArray.add(num);
            }
        }

        return newArray;

//            for (int i = 0; i < numbers.size(); i++) {
//                if (numbers.get(i) < 5) {
//                    System.out.println(numbers.get(i));
//                    newArray.add(i, numbers.get(i));\
//                }
//                if(newArray.size() == 0) {
//                    return null;
//                }
//            }
//            return newArray;
    }


    //challenge
    public static int[][] splitAtFive(int[] numbers) {
        int numLessThanFive = 0;
        int numMoreThanFive = 0;

        for(int num : numbers) {
            if ( num < 5 ) {
                numLessThanFive++;
            } else {
                numMoreThanFive++;
            }
        }

        int[] lessThan = new int[numLessThanFive];
        int[] moreThan = new int[numMoreThanFive];


        for(int num : numbers) {

            // subtracting numLessThanFive from length then decrementing numLessThanFive
            // allows us to go from 0 to length - 1 in order without additional variables
            // same with numMoreThanFive
            if ( num < 5 ) {
                lessThan[lessThan.length - numLessThanFive] = num;
                numLessThanFive--;
            } else {
                moreThan[moreThan.length - numMoreThanFive] = num;
                numMoreThanFive--;
            }
        }

        return new int[][] { lessThan, moreThan };
    }

    // Challenge
    public static String[][] evensAndOdds(String[] strings) {

        //leveraging integer division
        String[] odds = new String[ strings.length/2 ];

        // Set evens to null for reassignment below
        String[] evens = null;

        // again leveraging integer division
        // if it's already of even length, we're good
        // but if it's of odd length, there's one more even index than odd
        if (strings.length % 2 == 0) {
            evens = new String[ strings.length/2];
        } else {
            evens = new String[ strings.length/2 + 1];
        }

        for(int i = 0; i < strings.length; i++) {
            int currIndex = i/2;
            if( i % 2 == 0 ) {
                evens[currIndex] = strings[i];
            } else {
                odds[currIndex] = strings[i];
            }
        }

        return new String[][] { evens, odds };
    }

//    public static void main(String[] args) {
//        ArrayList<Integer> value = new ArrayList<Integer>();
//        value.add(-5);
//        value.add(4);
//        value.add(5);
//        value.add(9);
//
//        System.out.println(lessThanFive(value));
//    }
}
