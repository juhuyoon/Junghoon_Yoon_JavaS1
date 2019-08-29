package com.company.RESTCalculator.DTO;

public class RESTCalculator {
    private int operand1;
    private int operand2;
    private int result;

    public int add(int operand1, int operand2) {
        int sum = operand1 + operand2;
        return sum;
    }
    public int subtract(int operand1, int operand2) {
        int difference = operand1 - operand2;
        return difference;
    }
    public int mult(int operand1, int operand2) {
        int product = operand1 * operand2;
        return product;
    }
    public int divide(int operand1, int operand2) {
        int quotient = operand1 / operand2;
        return quotient;
    }


    public int getOperand1() {
        return operand1;
    }

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
