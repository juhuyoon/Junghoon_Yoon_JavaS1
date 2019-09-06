package com.company.CarLotDaoJunghoonYoon.dao;

import com.company.CarLotDaoJunghoonYoon.DAO.CarLotDao;
import com.company.CarLotDaoJunghoonYoon.Model.Car;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CarLotDaoTest {

    @Autowired
    private CarLotDao dao;

    @Before
    public void setUp() throws Exception {
        //flushes out previous data
        List<Car> cList = dao.getAllCars();
        cList.stream().forEach(c -> dao.deleteCars(c.getId()));
    }

    @Test
    public void addGetDeleteCar() {
        //Dummy Data
        Car car = new Car();
        car.setMake("Honda");
        car.setModel("CRV");
        car.setYear("2019");
        car.setColor("Gray");

        //Testing Add
        car = dao.addCar(car);
        Car car2 = dao.getCar(car.getId());
        assertEquals(car, car2);

        //Testing Delete
        dao.deleteCars(car.getId());
        car2 = dao.getCar(car.getId());
        assertNull(car2);


    }


    @Test
    public void getAllCars() {
        //Dummy Data
        Car car = new Car();
        car.setMake("Honda");
        car.setModel("CRV");
        car.setYear("2019");
        car.setColor("Gray");

        dao.addCar(car);

        car = new Car();
        car.setMake("Audi");
        car.setModel("R8");
        car.setYear("2012");
        car.setColor("Silver");

        dao.addCar(car);

        List<Car> carList = dao.getAllCars();
        assertEquals(carList.size(), 2);
    }

    @Test
    public void updateCars() {
        //Dummy Data
        Car car = new Car();
        car.setMake("Honda");
        car.setModel("CRV");
        car.setYear("2019");
        car.setColor("Gray");

        car = dao.addCar(car);

        //Replacement Data
        car.setMake("NEW SET");
        car.setModel("NEW MODEL");
        car.setYear("NEW YEAR");
        car.setColor("NEW COLOR");

        dao.updateCars(car);
        Car car2 = dao.getCar(car.getId());
        assertEquals(car2, car);

    }


    @Test
    public void getCarByMake() {
        //Dummy Data
        Car car = new Car();
        car.setMake("Honda");
        car.setModel("CRV");
        car.setYear("2019");
        car.setColor("Gray");

        dao.addCar(car);

        car = new Car();
        car.setMake("Audi");
        car.setModel("R8");
        car.setYear("2011");
        car.setColor("Pink");

        dao.addCar(car);

        List<Car> cList = dao.getCarByMake("Honda");
        assertEquals(cList.size(), 1);
    }

    @Test
    public void getCarByColor() {
        //Dummy Data
        Car car = new Car();
        car.setMake("Honda");
        car.setModel("CRV");
        car.setYear("2019");
        car.setColor("Gray");

        dao.addCar(car);

        car = new Car();
        car.setMake("Audi");
        car.setModel("R8");
        car.setYear("2011");
        car.setColor("Pink");

        dao.addCar(car);

        List<Car> cList = dao.getCarByColor("Pink");
        assertEquals(cList.size(), 1);
    }
}