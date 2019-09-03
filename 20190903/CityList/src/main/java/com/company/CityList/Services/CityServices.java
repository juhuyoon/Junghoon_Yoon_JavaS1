package com.company.CityList.Services;

import com.company.CityList.Model.City;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CityServices {

    Map<String, City> cityMap = new HashMap<>();


    public Map<String, City> postCity(City city) {
        cityMap.put(city.getName(), city);

        return cityMap;
    }

    public Collection<City> returnAllCity() {
        return cityMap.values();
    }

    public void deleteCityByName(String cityName) {
        cityMap.remove(cityName);
    }

    public City getCityByName(String cityName) {
        return cityMap.get(cityName);
    }


}
