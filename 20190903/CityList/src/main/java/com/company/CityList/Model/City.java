package com.company.CityList.Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class City {
    @NotEmpty(message = "You must provide a city name")
    private String name;
    @NotEmpty(message = "You must provide a State name")
    private String state;
    @NotNull(message = "You must provide a number of population")
    private Integer population;
    @NotNull(message = "Pass in a true/false of whether that city is a state.")
    private boolean capital;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }
}
