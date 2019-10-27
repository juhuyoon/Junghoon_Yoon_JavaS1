package com.company.adminapi.dto;

import java.util.Objects;

public class Inventory {
    private int inventory_id;
    private int product_id;
    private int quantity;

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return getInventory_id() == inventory.getInventory_id() &&
                getProduct_id() == inventory.getProduct_id() &&
                getQuantity() == inventory.getQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInventory_id(), getProduct_id(), getQuantity());
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventory_id=" + inventory_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                '}';
    }
}
