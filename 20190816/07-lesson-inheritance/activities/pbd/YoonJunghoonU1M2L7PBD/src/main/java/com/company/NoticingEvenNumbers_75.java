package com.company;

public class NoticingEvenNumbers_75 {
    public static void main(String[] args) {
        int[] numArray = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};

        for(int i = 0; i < numArray.length; i++) {
            if(numArray[i] % 2 == 0) {
                System.out.println(numArray[i] + " <");
            } else {
                System.out.println(numArray[i]);
            }
        }
    }
}
