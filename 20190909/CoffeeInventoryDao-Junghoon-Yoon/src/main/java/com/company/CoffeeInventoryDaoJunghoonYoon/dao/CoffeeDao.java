package com.company.CoffeeInventoryDaoJunghoonYoon.dao;

import com.company.CoffeeInventoryDaoJunghoonYoon.model.Coffee;

import java.util.List;

public interface CoffeeDao {
    Coffee addCoffee(Coffee coffee);

    Coffee getCoffee(int id);

    List<Coffee> getAllCoffees();

    void updateCoffee(Coffee coffee);

    void deleteCoffee(int id);

    List<Coffee> getCoffeesByType(String type);

    List<Coffee> getCoffeeByRoaster(int roasterId);
}
