package com.company.gamestoreretail.model;

import java.util.Objects;

public class Inventory {
    private int inventory_id;
    private int product_id;
    private int quantity;

    public Inventory() {
    }

    public Inventory(int inventory_id, int product_id, int quantity) {
        this.inventory_id = inventory_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

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
        return inventory_id == inventory.inventory_id &&
                product_id == inventory.product_id &&
                quantity == inventory.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventory_id, product_id, quantity);
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
