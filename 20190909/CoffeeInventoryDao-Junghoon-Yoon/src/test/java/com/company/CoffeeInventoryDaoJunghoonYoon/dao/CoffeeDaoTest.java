package com.company.CoffeeInventoryDaoJunghoonYoon.dao;

import com.company.CoffeeInventoryDaoJunghoonYoon.model.Coffee;
import com.company.CoffeeInventoryDaoJunghoonYoon.model.Roaster;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CoffeeDaoTest {

    @Autowired
    CoffeeDao coffeeDao;
    @Autowired
    RoasterDao roasterDao;

    @Before
    public void setUp() throws Exception {
        //Clean up the test db
        List<Coffee> cList = coffeeDao.getAllCoffees();
        for(Coffee c : cList){
            coffeeDao.deleteCoffee(c.getCoffee_id());
        }

        List<Coffee> coffeeList = coffeeDao.getAllCoffees();

        List<Roaster> rList = roasterDao.getAllRoasters();
        for(Roaster r: rList){
            roasterDao.deleteRoaster(r.getRoaster_id());
        }

        List<Roaster> roasterList = roasterDao.getAllRoasters();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void addWithRefIntegrityException() {
        Roaster roaster = new Roaster();
        roaster.setName("Roaster");
        roaster.setStreet("Some Street");
        roaster.setCity("CityName");
        roaster.setStreet("GA");
        roaster.setPostal_code("numbers");
        roaster.setPhone("4041231231231");
        roaster.setEmail("someemail@email.com");
        roaster.setNote("THIS IS A NOTE");

        roaster = roasterDao.addRoaster(roaster);
        Coffee coffee = new Coffee();
        coffee.setRoaster_id(roaster.getRoaster_id());
        coffee.setName("Coffee Brand");
        coffee.setUnit_price(15.95);
        coffee.setDescription("Some description");
        coffee.setType("Something");

        coffee = coffeeDao.addCoffee(coffee);

    }

    @Test
    public void addGetDeleteCoffee() {
        Roaster roaster = new Roaster();
        roaster.setName("Roaster");
        roaster.setStreet("Some Street");
        roaster.setCity("CityName");
        roaster.setStreet("GA");
        roaster.setPostal_code("numbers");
        roaster.setPhone("4041231231231");
        roaster.setEmail("someemail@email.com");
        roaster.setNote("THIS IS A NOTE");

        roaster = roasterDao.addRoaster(roaster);
        Coffee coffee = new Coffee();
        coffee.setRoaster_id(roaster.getRoaster_id());
        coffee.setName("Coffee Brand");
        coffee.setUnit_price(15.95);
        coffee.setDescription("Some description");
        coffee.setType("Something");

        coffee = coffeeDao.addCoffee(coffee);

        Coffee coffee1 = coffeeDao.getCoffee(coffee.getCoffee_id());
        assertEquals(coffee1, coffee);

        coffeeDao.deleteCoffee(coffee.getCoffee_id());

        coffee1 = coffeeDao.getCoffee(coffee.getCoffee_id());

        assertNull(coffee1);
    }

    @Test
    public void getAllCoffees() {
        Roaster roaster = new Roaster();
        roaster.setName("Roaster");
        roaster.setStreet("Some Street");
        roaster.setCity("CityName");
        roaster.setStreet("GA");
        roaster.setPostal_code("numbers");
        roaster.setPhone("4041231231231");
        roaster.setEmail("someemail@email.com");
        roaster.setNote("THIS IS A NOTE");

        roaster = roasterDao.addRoaster(roaster);
        Coffee coffee = new Coffee();
        coffee.setRoaster_id(roaster.getRoaster_id());
        coffee.setName("Coffee Brand");
        coffee.setUnit_price(15.95);
        coffee.setDescription("Some description");
        coffee.setType("Something");

        coffee = coffeeDao.addCoffee(coffee);

        coffee = new Coffee();
        coffee.setRoaster_id(roaster.getRoaster_id());
        coffee.setName("New Coffee Brand");
        coffee.setUnit_price(10.11);
        coffee.setDescription("New Description");
        coffee.setType("Something2");
    }

    @Test
    public void updateCoffee() {
        Roaster roaster = new Roaster();
        roaster.setName("Roaster");
        roaster.setStreet("Some Street");
        roaster.setCity("CityName");
        roaster.setStreet("GA");
        roaster.setPostal_code("numbers");
        roaster.setPhone("4041231231231");
        roaster.setEmail("someemail@email.com");
        roaster.setNote("THIS IS A NOTE");

        roaster = roasterDao.addRoaster(roaster);

        Coffee coffee = new Coffee();
        coffee.setRoaster_id(roaster.getRoaster_id());
        coffee.setName("Coffee Brand");
        coffee.setUnit_price(15.95);
        coffee.setDescription("Some description");
        coffee.setType("Something");

        coffee = coffeeDao.addCoffee(coffee);

        coffee.setRoaster_id(roaster.getRoaster_id());
        coffee.setName("NEW Coffee Brand");
        coffee.setUnit_price(20.00);
        coffee.setDescription("Some description3");
        coffee.setType("Something3");

        coffeeDao.updateCoffee(coffee);

        Coffee coffee1 = coffeeDao.getCoffee(coffee.getCoffee_id());
        assertEquals(coffee1, coffee);

    }
    @Test
    public void getCoffeesByType() {
        Roaster roaster = new Roaster();
        roaster.setName("Roaster");
        roaster.setStreet("Some Street");
        roaster.setCity("CityName");
        roaster.setStreet("GA");
        roaster.setPostal_code("numbers");
        roaster.setPhone("4041231231231");
        roaster.setEmail("someemail@email.com");
        roaster.setNote("THIS IS A NOTE");

        roaster = roasterDao.addRoaster(roaster);

        Coffee coffee = new Coffee();
        coffee.setRoaster_id(roaster.getRoaster_id());
        coffee.setName("Coffee Brand");
        coffee.setUnit_price(15.95);
        coffee.setDescription("Some description");
        coffee.setType("Something");

        coffee = coffeeDao.addCoffee(coffee);

        List<Coffee> cList = coffeeDao.getCoffeesByType(coffee.getType());
        assertEquals(cList.size(), 1);

    }

    @Test
    public void getCoffeeByRoaster() {
        Roaster roaster = new Roaster();
        roaster.setName("Roaster");
        roaster.setStreet("Some Street");
        roaster.setCity("CityName");
        roaster.setStreet("GA");
        roaster.setPostal_code("numbers");
        roaster.setPhone("4041231231231");
        roaster.setEmail("someemail@email.com");
        roaster.setNote("THIS IS A NOTE");

        roaster = roasterDao.addRoaster(roaster);
        Coffee coffee = new Coffee();
        coffee.setRoaster_id(roaster.getRoaster_id());
        coffee.setName("Coffee Brand");
        coffee.setUnit_price(15.95);
        coffee.setDescription("Some description");
        coffee.setType("Something");

        coffee = coffeeDao.addCoffee(coffee);

        List<Coffee> cList = coffeeDao.getCoffeeByRoaster(roaster.getRoaster_id());
        assertEquals(cList.size(), 1);
    }
}