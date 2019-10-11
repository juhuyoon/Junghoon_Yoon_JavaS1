package com.company.MonthConverter.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Month {
    @NotEmpty(message = "You must enter a whole number between 1 and 12.")
    @Size(min = 1, max = 12, message = "Number must be between 1 and 12.")
    private int month;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
