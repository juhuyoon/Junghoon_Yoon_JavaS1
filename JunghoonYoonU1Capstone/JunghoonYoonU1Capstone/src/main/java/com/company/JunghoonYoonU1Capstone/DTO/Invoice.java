package com.company.JunghoonYoonU1Capstone.DTO;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class Invoice {
    private Integer invoice_id;
    @NotNull(message = "Input a name")
    private String name;
    @NotNull(message = "Input a street")
    private String street;
    @NotNull(message = "Input the city")
    private String city;
    @NotNull(message = "Input the state")
    private String state;
    @NotNull(message = "Input a zipcode")
    private String zipcode;
    @NotNull(message = "Input the item type")
    private String item_type;
    @NotEmpty(message = "Input an item id")
    private Integer item_id;
    @NotEmpty(message = "Input the unit price")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal unit_price;
    @NotEmpty(message = "Input the quantity of the product")
    @Min(value = 1)
    private Integer quantity;
    @NotEmpty(message = "Add on the subtotal")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal subtotal;
    @NotEmpty(message = "Link the tax")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal tax;
    @NotEmpty(message = "Enter the processing fee")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal processing_fee;
    @NotEmpty(message = "Get the total amount")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal total;

    public Integer getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Integer invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessing_fee() {
        return processing_fee;
    }

    public void setProcessing_fee(BigDecimal processing_fee) {
        this.processing_fee = processing_fee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoice_id, invoice.invoice_id) &&
                name.equals(invoice.name) &&
                street.equals(invoice.street) &&
                city.equals(invoice.city) &&
                state.equals(invoice.state) &&
                zipcode.equals(invoice.zipcode) &&
                item_type.equals(invoice.item_type) &&
                item_id.equals(invoice.item_id) &&
                unit_price.equals(invoice.unit_price) &&
                quantity.equals(invoice.quantity) &&
                subtotal.equals(invoice.subtotal) &&
                tax.equals(invoice.tax) &&
                processing_fee.equals(invoice.processing_fee) &&
                total.equals(invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_id, name, street, city, state, zipcode, item_type, item_id, unit_price, quantity, subtotal, tax, processing_fee, total);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoice_id=" + invoice_id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", item_type='" + item_type + '\'' +
                ", item_id=" + item_id +
                ", unit_price=" + unit_price +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processing_fee=" + processing_fee +
                ", total=" + total +
                '}';
    }
}
