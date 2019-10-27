package com.company.adminapi.dto;

import java.time.LocalDate;
import java.util.Objects;

public class Invoice {
    private int invoice_id;
    private int customer_id;
    private LocalDate purchase_date;

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public LocalDate getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(LocalDate purchase_date) {
        this.purchase_date = purchase_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getInvoice_id() == invoice.getInvoice_id() &&
                getCustomer_id() == invoice.getCustomer_id() &&
                getPurchase_date().equals(invoice.getPurchase_date());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoice_id(), getCustomer_id(), getPurchase_date());
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoice_id=" + invoice_id +
                ", customer_id=" + customer_id +
                ", purchase_date=" + purchase_date +
                '}';
    }
}




