package com.company.CoffeeInventoryDaoJunghoonYoon.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="coffee")
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer coffeeId;
    private Integer roasterId;
    private String name;
    private int count;
    private double unit_price;
    private String description;
    private String type;


    public Integer getId() {
        return coffeeId;
    }

    public void setId(Integer id) {
        this.coffeeId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRoasterId() {
        return roasterId;
    }

    public void setRoasterId(Integer roasterId) {
        this.roasterId = roasterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return count == coffee.count &&
                Double.compare(coffee.unit_price, unit_price) == 0 &&
                coffeeId.equals(coffee.coffeeId) &&
                name.equals(coffee.name) &&
                description.equals(coffee.description) &&
                type.equals(coffee.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coffeeId, name, count, unit_price, description, type);
    }
}
