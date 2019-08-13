package com.company.Exercise3;

public class SomeMath {
    public static void total5(int a, int b, int c, int d, int e) {
        int total = a + b + c + d + e;
        System.out.println("The total is: " + total);
    }

    public static void average5(int a, int b, int c, int d, int e) {
        double average = ((a + b + c + d + e)/5);
        System.out.printf("The average is: %.0f \n", average);
    }

    public static void largest5(double a, double b, double c, double d, double e){
        //Math max chain
        double max = Math.max(Math.max(Math.max(Math.max(a,b),c),d),e);
        System.out.println("The largest number is: " + max);
    }

    public static void main(String[] args) {
        total5(1, 2, 3, 4, 5);
        average5(1, 3, 5, 7 ,9);
        largest5(42.0, 35.1, 2.3,40.2,15.6);
    }
}
