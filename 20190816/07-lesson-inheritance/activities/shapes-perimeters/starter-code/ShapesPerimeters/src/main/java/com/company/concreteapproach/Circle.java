package com.company.concreteapproach;

public class Circle extends Shape{
    private double radius;

    public Circle(String name, String color, int x_coordinate, int y_coordinate, double radius) {
        super(name, color, x_coordinate, y_coordinate);
        this.radius = radius;
    }

    @Override
    public void setX_coordinate(int x_coordinate) {
        super.setX_coordinate(x_coordinate);
    }

    @Override
    public void setY_coordinate(int y_coordinate) {
        super.setY_coordinate(y_coordinate);
    }

    @Override
    public void area() {
        System.out.println(Math.PI * Math.pow(radius, 2));
    }

    @Override
    public void perimeter() {
        System.out.println(Math.PI * 2 * radius);
    }
}
