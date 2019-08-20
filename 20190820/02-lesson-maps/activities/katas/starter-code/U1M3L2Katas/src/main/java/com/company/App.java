package com.company;

import java.util.*;

public class App {
    public void printKeys(Map<String, String> input){
        Set<Map.Entry<String, String>> carKeys = input.entrySet();
        for(Map.Entry<String, String> entry : carKeys) {
            System.out.println(entry.getKey());
        }
    }

    public void printValues(Map<String, String> input) {
        Set<Map.Entry<String, String>> carValues = input.entrySet();
        for(Map.Entry<String, String> entry: carValues) {
            System.out.println(entry.getValue());
        }
    }

    public void printKeysAndValues(Map<String, String> input) {
        Set<Map.Entry<String, String>> carKeyValue = input.entrySet();
        for(Map.Entry<String, String> entry: carKeyValue) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public Map<String, Integer> mapFun(Map<String, Integer> input) {
        input.put("Ford Explorer", 2012);
        input.put("Smart Fortwo", 2013);
        input.remove("Jeep Wrangler");
        return input;
    }

    public Map<String, List<Car>> listCars(List<Car> cars) {
        List<Car> toyotaList = new ArrayList<>();
        List<Car> fordList = new ArrayList<>();
        List<Car> hondaList = new ArrayList<>();

        for(Car carMake : cars) {
            if (carMake.getMake() == "Toyota") {
                toyotaList.add(carMake);
            } else if(carMake.getMake() == "Ford") {
                fordList.add(carMake);
            } else if(carMake.getMake() == "Honda") {
                hondaList.add(carMake);
            }
        }

        Map<String, List<Car>> returnCars = new HashMap<>();
        returnCars.put("Toyota", toyotaList);
        returnCars.put("Ford", fordList);
        returnCars.put("Honda", hondaList);

        return returnCars;
    }
}
