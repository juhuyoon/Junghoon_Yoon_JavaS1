package com.company.gamestoreretail.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private int product_id;
    @NotBlank(message = "Please provide a product name")
    private String product_name;
    @NotBlank(message = "Please provide a product description")
    private String product_description;
    @Digits(integer=10,fraction=2)
    private BigDecimal list_price;
    @Digits(integer=10,fraction=2)
    private BigDecimal unit_cost;

    public Product() {
    }

    public Product(int product_id, @NotBlank(message = "Please provide a product name") String product_name, @NotBlank(message = "Please provide a product description") String product_description, @Digits(integer = 10, fraction = 2) BigDecimal list_price, @Digits(integer = 10, fraction = 2) BigDecimal unit_cost) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.list_price = list_price;
        this.unit_cost = unit_cost;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public BigDecimal getList_price() {
        return list_price;
    }

    public void setList_price(BigDecimal list_price) {
        this.list_price = list_price;
    }

    public BigDecimal getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(BigDecimal unit_cost) {
        this.unit_cost = unit_cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return product_id == product.product_id &&
                Objects.equals(product_name, product.product_name) &&
                Objects.equals(product_description, product.product_description) &&
                Objects.equals(list_price, product.list_price) &&
                Objects.equals(unit_cost, product.unit_cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, product_name, product_description, list_price, unit_cost);
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_description='" + product_description + '\'' +
                ", list_price=" + list_price +
                ", unit_cost=" + unit_cost +
                '}';
    }
}
