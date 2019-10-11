package com.company.WeatherAPI.DTO;

public class Temperature {
    private Number fahrenheit;
    private Number celsius;

    public Number getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(Number fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public Number getCelsius() {
        return celsius;
    }

    public void setCelsius(Number celsius) {
        this.celsius = celsius;
    }
}
