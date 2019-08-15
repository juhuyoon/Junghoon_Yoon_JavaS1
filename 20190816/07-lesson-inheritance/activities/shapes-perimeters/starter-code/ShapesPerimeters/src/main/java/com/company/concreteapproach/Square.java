package com.company.concreteapproach;

public class Square extends Shape {
    private double side;

    public Square(String name, String color, int x_coordinate, int y_coordinate, double side) {
        super(name, color, x_coordinate, y_coordinate);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public void area() {
        System.out.println(this.side * this.side);
    }

    @Override
    public void perimeter() {
        System.out.println(this.side * 4.0);
    }
}
