package CalculatorObject;

public class CalculatorObject {
    public void addition(int a, int b) {
        int sum = a + b;
        System.out.printf(a + " + " + b + " = " + sum + "\n");
    }

    public void subtraction(int a, int b) {
        int difference = a - b;
        System.out.printf(a + " - " + b + " = " + difference  + "\n");
    }

    public void multiplication(int a, int b) {
        int product = a * b;
        System.out.printf(a + " * " + b + " = " + product  + "\n");
    }

    public void division(int a, int b) {
        int reminder = a / b;
        System.out.printf(a + " / " + b + " = " + reminder  + "\n");

    }

    public void addition(double a, double b) {
        double sum = a + b;
        System.out.printf(a + " + " + b + " = %.2f \n", sum);
    }

    public void subtraction(double a, double b){
        double difference = a - b;
        System.out.printf(a + " - " + b + " = %.2f \n", difference );
    }

    public void multiplication(double a, double b) {
        double product = a * b;
        System.out.printf(a + " * " + b + " = %.2f \n", product);
    }

    public void division(double a, double b) {
        double reminder = a / b;
        System.out.printf(a + " / " + b + " = %.2f \n", reminder);
    }

}
