package com.company.Exercise4;

import java.util.Arrays;

public class ArrayFunReverseIt {
    public static void main(String[] args) {
        int[] firstArray = new int[]{1, 2, 3, 4, 5};
        int[] secondArray = new int[5];
        for(int i = 0; i < firstArray.length; i++) {
            int temp = 0;
            temp = firstArray[i];
            secondArray[secondArray.length - i - 1] = temp;
        }
        System.out.print("First array is: " + Arrays.toString(firstArray) + "\n");
        System.out.println("Second array is: " + Arrays.toString(secondArray));
    }
}
