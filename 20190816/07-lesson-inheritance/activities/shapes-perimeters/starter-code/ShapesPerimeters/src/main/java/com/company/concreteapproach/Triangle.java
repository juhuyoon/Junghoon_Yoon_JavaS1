package com.company.concreteapproach;

public class Triangle extends Shape {
    private int side1;
    private int side2;
    private int side3;

    public Triangle(String name, String color, int x_coordinate, int y_coordinate, int side1, int side2, int side3) {
        super(name, color, x_coordinate, y_coordinate);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public void perimeter() {
        System.out.println(side1 + side2 + side3);
    }

    @Override
    public void area() {
        double p = side1 + side2 + side3;
        System.out.println(Math.sqrt(p * (p - side1) * (p - side2) * (p-side3)));
    }

    @Override
    public int getX_coordinate() {
        return super.getX_coordinate();
    }

    @Override
    public int getY_coordinate() {
        return super.getY_coordinate();
    }
}
