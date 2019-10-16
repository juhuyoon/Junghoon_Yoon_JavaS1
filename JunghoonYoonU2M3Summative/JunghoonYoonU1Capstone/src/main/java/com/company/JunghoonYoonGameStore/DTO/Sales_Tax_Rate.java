package com.company.JunghoonYoonGameStore.DTO;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class Sales_Tax_Rate {
    @NotEmpty(message = "Enter the state")
    private String state;
    @NotNull(message = "Enter the tax rate based on state. ")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal rate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sales_Tax_Rate that = (Sales_Tax_Rate) o;
        return state.equals(that.state) &&
                rate.equals(that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, rate);
    }
}
