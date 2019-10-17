package com.company.JunghoonYoonGameStore.DAO;

import com.company.JunghoonYoonGameStore.DTO.TShirt;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TshirtDaoTest {

    @Autowired
    private TshirtDao tshirtDao;

    private TShirt tShirt, tShirt2;



    @Before
    public void setUp() throws Exception {
        tshirtDao.getAllTShirts().forEach(t -> {
            tshirtDao.deleteTShirt(t.getT_shirt_id());
        });

        tShirt = new TShirt();
        tShirt.setSize("Medium");
        tShirt.setColor("Orange");
        tShirt.setDescription("Pretty and Orange");
        tShirt.setPrice(new BigDecimal("5.55"));
        tShirt.setQuantity(15);

        tShirt2 = new TShirt();
        tShirt2.setSize("Large");
        tShirt2.setColor("Black");
        tShirt2.setDescription("Professional Attire");
        tShirt2.setPrice(new BigDecimal("155.55"));
        tShirt2.setQuantity(5);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldAddTShirt() {
        tShirt = tshirtDao.addTShirt(tShirt);
        assertEquals(1, tshirtDao.getAllTShirts().size());
    }

    @Test
    public void shouldGetTShirt() {
        tShirt = tshirtDao.addTShirt(tShirt);
        TShirt tShirtTest = tshirtDao.getTShirt(tShirt.getT_shirt_id());

        assertEquals(tShirtTest, tShirt);
    }

    @Test
    public void shouldGetAllTShirts() {
        tshirtDao.addTShirt(tShirt);
        tshirtDao.addTShirt(tShirt2);

        List<TShirt> tList = tshirtDao.getAllTShirts();
        assertEquals(2, tList.size());
    }

    @Test
    public void shouldUpdateTShirt() {
        tshirtDao.addTShirt(tShirt);
        tShirt.setDescription("SOMETHING NEW");
        TShirt tShirtTest = tshirtDao.getTShirt(tShirt.getT_shirt_id());

        assertNotEquals(tShirt,  tShirtTest);

    }


    @Test
    public void shouldDeleteTShirt() {
        tshirtDao.addTShirt(tShirt);
        tshirtDao.deleteTShirt(tShirt.getT_shirt_id());

        TShirt tShirtTest = tshirtDao.getTShirt(tShirt.getT_shirt_id());

        assertNull(tShirtTest);
    }

    @Test
    public void shouldGetTShirtsByColor() {
        tshirtDao.addTShirt(tShirt);
        tshirtDao.getTShirtsByColor(tShirt.getColor());

        TShirt tShirtTest = tshirtDao.getTShirt(tShirt.getT_shirt_id());


        assertEquals(tShirtTest, tShirt);
    }

    @Test
    public void shouldGetTShirtsBySize() {
        tshirtDao.addTShirt(tShirt);
        tshirtDao.getTShirtsBySize(tShirt.getSize());

        TShirt tShirtTest = tshirtDao.getTShirt(tShirt.getT_shirt_id());

        assertEquals(tShirtTest, tShirt);
    }
}