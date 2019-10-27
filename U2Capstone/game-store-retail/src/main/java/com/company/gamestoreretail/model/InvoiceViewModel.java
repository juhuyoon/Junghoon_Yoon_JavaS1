package com.company.gamestoreretail.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class InvoiceViewModel {
    private Integer invoice_id;
    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Id must be positive")
    private Integer customer_id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate purchase_date;
    @NotBlank(message = "Please provide a list of invoice items")
    private List<InvoiceItem> invoiceItems;
    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Id must be positive")
    private Integer points;

    public InvoiceViewModel() {
    }

    public InvoiceViewModel(Integer invoice_id, @Pattern(regexp = "[\\s]*[0-9]*[1-9]+", message = "Id must be positive") Integer customer_id, LocalDate purchase_date, @NotBlank(message = "Please provide a list of invoice items") List<InvoiceItem> invoiceItems, @Pattern(regexp = "[\\s]*[0-9]*[1-9]+", message = "Id must be positive") Integer points) {
        this.invoice_id = invoice_id;
        this.customer_id = customer_id;
        this.purchase_date = purchase_date;
        this.invoiceItems = invoiceItems;
        this.points = points;
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

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return Objects.equals(invoice_id, that.invoice_id) &&
                Objects.equals(customer_id, that.customer_id) &&
                Objects.equals(purchase_date, that.purchase_date) &&
                Objects.equals(invoiceItems, that.invoiceItems) &&
                Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_id, customer_id, purchase_date, invoiceItems, points);
    }

    @Override
    public String toString() {
        return "InvoiceViewModel{" +
                "invoice_id=" + invoice_id +
                ", customer_id=" + customer_id +
                ", purchase_date=" + purchase_date +
                ", invoiceItems=" + invoiceItems +
                ", points=" + points +
                '}';
    }
}
