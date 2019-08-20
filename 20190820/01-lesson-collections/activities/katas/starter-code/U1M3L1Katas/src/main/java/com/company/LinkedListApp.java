package com.company;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LinkedListApp {
    public static int total (LinkedList<Integer> numbers) {
        List<Integer> totalArray = new LinkedList<>();

        for(int num : numbers) {
            totalArray.add(num);
        }
        int sum = 0;
        for(int i = 0; i < totalArray.size(); i++) {
            sum += totalArray.get(i);
        }
        return sum;
    }

    public static int totalEven (LinkedList<Integer> numbers) {

        int sum = 0;
        for(int i = 0; i < numbers.size(); i += 2) {
            sum += numbers.get(i);
        }

        return sum;
    }

    public static List<String> swapFirstAndLast(LinkedList<String> strings) {
        List<String> newArray = new LinkedList<>();

        for(int i = 0; i < strings.size(); i++) {
            newArray.add(i, strings.get(i));
        }
        int value = (strings.size() -1);

        Collections.swap(newArray, 0, value);
        return newArray;

    }

    public static List<Integer> reverse(LinkedList<Integer> numbers) {
        List<Integer> newArray = new LinkedList<>();

        for(int i = 0; i < numbers.size(); i++) {

            newArray.add(i, numbers.get(i));
        }
        Collections.reverse(newArray);

        return newArray;
    }

    public static List<Integer> lessThanFive(LinkedList<Integer> numbers) {

        List<Integer> newArray = new LinkedList<>();

        for(Integer num: numbers) {
            if(num < 5) {
                newArray.add(num);
            }
        }
        if(newArray.size() == 0) {
            return null;
        }
        return newArray;
    }
}
