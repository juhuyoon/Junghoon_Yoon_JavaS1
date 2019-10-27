package com.company.gamestoreinvoiceserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

public class Invoice {

    private Integer invoice_id;
    @NotBlank(message = "Please provide a customer id")
    private Integer customer_id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate purchase_date;

    public Invoice() {
    }

    public Invoice(Integer invoice_id, @NotBlank(message = "Please provide a customer id") Integer customer_id, LocalDate purchase_date) {
        this.invoice_id = invoice_id;
        this.customer_id = customer_id;
        this.purchase_date = purchase_date;
    }

    public Integer getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Integer invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
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
        return Objects.equals(invoice_id, invoice.invoice_id) &&
                Objects.equals(customer_id, invoice.customer_id) &&
                Objects.equals(purchase_date, invoice.purchase_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_id, customer_id, purchase_date);
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