package com.company;


public class App {

    public static int total(int[] input) {
        int sum = 0;

        for (int i = 0; i < input.length; i++) {
            sum += input[i];
        }
        return sum;
    }

    public static int totalOdd(int[] input) {
        int sumOdd = 0;
        for (int i = 0; i < input.length; i++) {
            if (i % 2 != 0) {
                sumOdd += input[i];
            }
        }
        return sumOdd;
    }

    public static int totalEven(int[] input) {
        int sumEven = 0;
        for (int i = 0; i < input.length; i++) {
            if (i % 2 == 0) {
                sumEven += input[i];
            }
        }
        return sumEven;
    }

    public static int secondLargestNumber(int[] input) {
        int largest = 0;
        int secondLargest = 0;
        if (input[0] > input[1]) {
            largest = input[0];
            secondLargest = input[1];
        } else if (input[0] < input[1]) {
            largest = input[1];
            secondLargest = input[0];
        }
        for (int i = 0; i < input.length; i++) {
            if (input[i] > largest) {
                secondLargest = largest;
                largest = input[i];
            } else if ((input[i] < largest) && (input[i] > secondLargest)) {
                secondLargest = input[i];
            }
        }
        return secondLargest;
    }

    public static String[] swapFirstAndLast(String[] input) {
        String[] firstAndLastSwap = new String[input.length];
        String lastInNewArray = input[0];
        String firstInNewArray = input[input.length - 1];

        for (int i = 0; i < input.length; i++) {
            firstAndLastSwap[i] = input[i];
        }
        firstAndLastSwap[input.length - 1] = lastInNewArray;
        firstAndLastSwap[0] = firstInNewArray;
        return firstAndLastSwap;

    }

    public static int[] reverse(int[] input) {
        int[] reverseArray = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            reverseArray[input.length - 1 - i] = input[i];
        }
        return reverseArray;
    }

    public static String concatenateString(String[] input) {
        String result = "";
        for (int i = 0; i < input.length; i++) {
            result += input[i];
        }
        return result;
    }

    //Change the holding counters.
    public static int[] everyThird(int[] input) {
        if (input.length >= 3) {
            int count = 1;
            int newIndex = 0;
            int[] thirdResult = new int[input.length / 3];
            for (int i = 0; i < input.length; i++) {
                if (count % 3 == 0) {
                    thirdResult[newIndex] = input[i];
                    newIndex++;
                }
                count++;
            }
            return thirdResult;
        } else {
            return null;
        }
    }

    public static int[] lessThanFive(int[] input) {
        int count = 0;
        int newIndex = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] < 5) {
                count++;
            }
        }
        int[] lessThanFiveArray = new int[count];
        for (int i = 0; i < input.length; i++) {
            if (input[i] < 5) {
                lessThanFiveArray[newIndex] = input[i];
                newIndex++;
            }
            count++;
        }
        if (lessThanFiveArray.length == 0) {
            return null;
        } else {
            return lessThanFiveArray;
        }

//    public static int[] splitAtFive(int[] input) {
//
//        return input;
//    }
//
//    public static void main(String[] args) {
//        int[] newArr = {1,2,3,4,5,6,7,8};
//        everyThird(newArr);
//    }
    }
}

