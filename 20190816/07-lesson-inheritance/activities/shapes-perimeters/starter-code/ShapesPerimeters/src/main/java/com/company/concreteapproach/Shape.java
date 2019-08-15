package com.company.concreteapproach;

public class Shape {
    public Shape(String name, String color, int x_coordinate, int y_coordinate) {
        this.name = name;
        this.color = color;
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }

    private String name;
    private String color;
    private int x_coordinate;
    private int y_coordinate;

    public void area() {

    };

    public void perimeter() {

    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getX_coordinate() {
        return x_coordinate;
    }

    public void setX_coordinate(int x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public int getY_coordinate() {
        return y_coordinate;
    }

    public void setY_coordinate(int y_coordinate) {
        this.y_coordinate = y_coordinate;
    }
}
