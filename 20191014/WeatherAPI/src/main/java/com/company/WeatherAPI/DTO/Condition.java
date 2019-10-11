package com.company.WeatherAPI.DTO;

public class Condition extends Temperature {

    private Number fahrenheit;
    private Number celsius;
    private Number windSpeed;
    private String windDirection;
    private String skies;
    private String precipitation;

    @Override
    public Number getFahrenheit() {
        return fahrenheit;
    }

    @Override
    public void setFahrenheit(Number fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    @Override
    public Number getCelsius() {
        return celsius;
    }

    @Override
    public void setCelsius(Number celsius) {
        this.celsius = celsius;
    }

    public Number getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Number windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getSkies() {
        return skies;
    }

    public void setSkies(String skies) {
        this.skies = skies;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }
}
