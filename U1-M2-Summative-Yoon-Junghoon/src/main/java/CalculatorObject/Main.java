package CalculatorObject;

public class Main {
    public static void main(String[] args) {
        //Instantiation of object
        CalculatorObject calc = new CalculatorObject();

        //Usage of methods from that obj.
        calc.addition(1, 1);
        calc.subtraction(23, 52);
        calc.multiplication(34, 2);
        calc.division(12, 3);
        calc.division(12, 7);
        calc.addition(3.4, 2.3);
        calc.multiplication(6.7, 4.4);
        calc.subtraction(5.5, 0.5);
        calc.division(10.8, 2.2);
    }
}
