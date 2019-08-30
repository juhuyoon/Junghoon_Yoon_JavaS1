package com.company.CityList.Controller;

import com.company.CityList.Model.City;
import com.company.CityList.Services.CityServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController("/city")
public class CityListController {
    CityServices services = new CityServices();

    @PostMapping(value = {})
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String, City> postCity(@RequestBody @Valid City city) throws IllegalArgumentException {
        return services.returnMap(city);
        //return city;
    }


    @GetMapping(value = {})
    @ResponseStatus(value = HttpStatus.OK)
    public Object getAllCities() {
        return services.returnAllCity();
    }

    @GetMapping(value = {"/{name"})
    @ResponseStatus(value = HttpStatus.OK)
    public Object getCityByName(@PathVariable String name) {
        return services.getCityByName(name);
    }

    @DeleteMapping(value ={"/{name}"})
    @ResponseStatus(value = HttpStatus.OK)
    public void removeCity(@PathVariable String name) {
        services.deleteCityByName(name);
    }
}
