package com.company.adminapi.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItem {

    private int invoide_item_id;
    private int invoice_id;
    private int inventory_id;
    private int quantity;
    private BigDecimal unit_price;

    public int getInvoide_item_id() {
        return invoide_item_id;
    }

    public void setInvoide_item_id(int invoide_item_id) {
        this.invoide_item_id = invoide_item_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return getInvoide_item_id() == that.getInvoide_item_id() &&
                getInvoice_id() == that.getInvoice_id() &&
                getInventory_id() == that.getInventory_id() &&
                getQuantity() == that.getQuantity() &&
                getUnit_price().equals(that.getUnit_price());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoide_item_id(), getInvoice_id(), getInventory_id(), getQuantity(), getUnit_price());
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "invoide_item_id=" + invoide_item_id +
                ", invoice_id=" + invoice_id +
                ", inventory_id=" + inventory_id +
                ", quantity=" + quantity +
                ", unit_price=" + unit_price +
                '}';
    }
}
