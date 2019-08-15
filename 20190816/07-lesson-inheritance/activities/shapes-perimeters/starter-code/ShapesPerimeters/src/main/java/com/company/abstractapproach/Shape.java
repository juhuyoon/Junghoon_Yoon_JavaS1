package com.company.abstractapproach;

public abstract class Shape {
    private String name;
    private String color;
    private int x_coordinate;
    private int y_coordinate;

    public Shape(String name, String color, int x_coordinate, int y_coordinate) {
        this.name = name;
        this.color = color;
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }

    abstract void area();
    abstract void perimeter();
}
