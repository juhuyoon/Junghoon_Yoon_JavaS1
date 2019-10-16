package com.company.JunghoonYoonGameStore.DTO;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class TShirt {
    private Integer t_shirt_id;
    @NotEmpty(message = "Enter the size")
    private String size;
    @NotEmpty(message = "Enter the color")
    private String color;
    @NotEmpty(message = "Enter the description")
    private String description;
    @NotNull(message = "Enter the price")
    @Digits(integer = 5, fraction =2)
    private BigDecimal price;
    @NotNull(message = "Enter the quantity")
    @Min(value = 1)
    private Integer quantity;

    public Integer getT_shirt_id() {
        return t_shirt_id;
    }

    public void setT_shirt_id(Integer t_shirt_id) {
        this.t_shirt_id = t_shirt_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TShirt tShirt = (TShirt) o;
        return Objects.equals(t_shirt_id, tShirt.t_shirt_id) &&
                size.equals(tShirt.size) &&
                color.equals(tShirt.color) &&
                description.equals(tShirt.description) &&
                price.equals(tShirt.price) &&
                quantity.equals(tShirt.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t_shirt_id, size, color, description, price, quantity);
    }
}
