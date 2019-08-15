package com.company.abstractapproach;

public class Circle extends Shape{

    private double radius;

    public Circle(String name, String color, int x_coordinate, int y_coordinate, double radius) {
        super(name, color, x_coordinate, y_coordinate);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    void area() {
        System.out.println(Math.PI * Math.pow(this.radius, 2));
    }

    @Override
    void perimeter() {
        System.out.println(Math.PI * 2 * this.radius);
    }
}
