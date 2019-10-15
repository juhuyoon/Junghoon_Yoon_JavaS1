package com.company.JunghoonYoonU1Capstone.DTO;

public class sumMultiples {
    // Function to find sum of AP series
    static long sumAP(long n, long d)
    {
        // Number of terms
        n /= d;

        return (n) * (1 + n) * d / 2;
    }

    // Function to find the sum of all
// multiples of 2 and 5 below N
    static long sumMultiples(long n)
    {
        // Since, we need the sum of
        // multiples less than N
        n--;

        return sumAP(n, 2) + sumAP(n, 5) - sumAP(n, 10);
    }
    public int sumMultiples(int num){
        int sum = 0;
        for(int i = 0; i < num; i++) {
            if(i < 0) {
                throw new IllegalArgumentException();
            }
            if(i % 3 == 0) {
                sum += i;
            }
        }
        return num;
    }
}
