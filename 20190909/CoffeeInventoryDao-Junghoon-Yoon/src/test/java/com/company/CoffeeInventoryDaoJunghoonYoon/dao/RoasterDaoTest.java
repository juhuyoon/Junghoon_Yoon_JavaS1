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
public class RoasterDaoTest {

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
    }

    @Test
    public void addGetDeleteRoaster() {
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

        Roaster roaster1 = roasterDao.getRoaster(roaster.getRoaster_id());

        assertEquals(roaster1, roaster);

        roasterDao.deleteRoaster(roaster.getRoaster_id());

        roaster1 = roasterDao.getRoaster(roaster.getRoaster_id());

        assertNull(roaster1);
    }

    @Test
    public void getAllRoasters() {
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

        roaster = new Roaster();
        roaster.setName("Next Roaster");
        roaster.setStreet("Some Street2");
        roaster.setCity("CityName2");
        roaster.setStreet("GA2");
        roaster.setPostal_code("numbers2");
        roaster.setPhone("40412312312312");
        roaster.setEmail("someemail2@email.com");
        roaster.setNote("THIS IS A2 NOTE");

        roaster = roasterDao.addRoaster(roaster);

        List<Roaster> rList = roasterDao.getAllRoasters();

        assertEquals(rList, 2);
    }

    @Test
    public void updateRoaster() {
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

        roaster.setName("Next Roaster");
        roaster.setStreet("Some Street2");
        roaster.setCity("CityName2");
        roaster.setStreet("GA2");
        roaster.setPostal_code("numbers2");
        roaster.setPhone("40412312312312");
        roaster.setEmail("someemail2@email.com");
        roaster.setNote("THIS IS A2 NOTE");

        roasterDao.updateRoaster(roaster);

        Roaster roaster1 = roasterDao.getRoaster(roaster.getRoaster_id());
        assertEquals(roaster1, roaster);
    }

}