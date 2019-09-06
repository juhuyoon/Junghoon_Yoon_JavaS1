package com.company.CarLotDaoJunghoonYoon.DAO;

import com.company.CarLotDaoJunghoonYoon.Model.Car;

import java.util.List;

public interface CarLotDao {

    /**
     * Adds Car based on the parameters of the car
     * @param car
     * @return
     */
    Car addCar(Car car);


    /**
     * Retrieves the Car by searching for the id.
     * @param id
     * @return
     */
    Car getCar(int id);

    /**
     * Returns a list of all the cars in the database.
     * @return
     */
    List<Car> getAllCars();

    /**
     * Updates a specific Car in the database based on ID.
     * @param car
     */
    void updateCars(Car car);

    /**
     * Removes a specific Car in the database based on the ID.
     * @param id
     */
    void deleteCars(int id);

    /**
     * Retrieves a list of Cars based on the car make.
     * @param make
     * @return
     */
    List<Car> getCarByMake(String make);


    /**
     * Retrieves a list of Cars based on the car color.
     * @param color
     * @return
     */
    List<Car> getCarByColor(String color);
}

