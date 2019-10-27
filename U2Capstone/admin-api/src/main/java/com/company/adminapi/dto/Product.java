package com.company.adminapi.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private int product_id;
    private String product_name;
    private String product_description;
    private BigDecimal list_price;
    private BigDecimal unit_cost;

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
        return getProduct_id() == product.getProduct_id() &&
                getProduct_name().equals(product.getProduct_name()) &&
                getProduct_description().equals(product.getProduct_description()) &&
                getList_price().equals(product.getList_price()) &&
                getUnit_cost().equals(product.getUnit_cost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct_id(), getProduct_name(), getProduct_description(), getList_price(), getUnit_cost());
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



