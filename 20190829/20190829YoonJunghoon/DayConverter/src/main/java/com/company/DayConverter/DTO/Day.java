package com.company.DayConverter.DTO;

import javax.validation.constraints.Size;

public class Day {
    @Size(min = 1, max = 7, message = "Must be a number between 1 and 7 inclusive")
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
