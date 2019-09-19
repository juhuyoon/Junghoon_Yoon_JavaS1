package com.company.JunghoonYoonU1Capstone.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class Processing_Fee {
    @NotEmpty(message = "Enter the product type")
    private String product_type;
    @NotNull(message = "Enter the fee")
    private BigDecimal fee;

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processing_Fee that = (Processing_Fee) o;
        return product_type.equals(that.product_type) &&
                Objects.equals(fee, that.fee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_type, fee);
    }
}
