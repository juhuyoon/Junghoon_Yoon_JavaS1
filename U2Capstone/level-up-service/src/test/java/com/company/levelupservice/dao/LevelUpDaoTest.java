package com.company.levelupservice.dao;

import com.company.levelupservice.model.LevelUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LevelUpDaoTest {

    @Autowired
    LevelUpDao dao;

    @Before
    public void setUp() throws Exception {
        dao.readAll().stream().forEach(lvl -> dao.deleteLevelUp(lvl.getLevel_up_id()));
    }

    @Test
    public void createLevelUp() {
        LevelUp lvl1 = new LevelUp(
                1,
                20,
                LocalDate.of(2000,01,01)
        );

        LevelUp checkLvl = dao.createLevelUp(lvl1);
        dao.readLevelUp(checkLvl.getLevel_up_id());
        System.out.println(checkLvl);
        assertNotNull(checkLvl.getLevel_up_id());
        assertTrue(checkLvl.getCustomer_id() > 0);
        assertEquals(lvl1.getCustomer_id(), checkLvl.getCustomer_id());
    }

    @Test
    public void readLevelUp() {
        LevelUp lvl1 = new LevelUp(
                1,
                20,
                LocalDate.of(2000,01,01)
        );
        lvl1 = dao.createLevelUp(lvl1);

        LevelUp checkLvl = dao.readLevelUp(lvl1.getLevel_up_id());

        assertEquals(lvl1, checkLvl);
    }

    @Test
    public void readAll() {
        LevelUp lvl1 = new LevelUp(
                1,
                20,
                LocalDate.of(2000,01,01)
        );
        lvl1 = dao.createLevelUp(lvl1);
        LevelUp lvl2 = new LevelUp(
                2,
                42,
                LocalDate.of(2000,02,02)
        );
        lvl2 = dao.createLevelUp(lvl2);
        List<LevelUp> expectedList = new ArrayList<>();
        expectedList.add(lvl1);
        expectedList.add(lvl2);

        List<LevelUp> checkList = dao.readAll();

        assertEquals(expectedList, checkList);
    }

    @Test
    public void readByCustomer(){
        LevelUp lvl1 = new LevelUp(
                1,
                20,
                LocalDate.of(2000,01,01)
        );
        lvl1 = dao.createLevelUp(lvl1);

        LevelUp checkLvl = dao.readByCustomer(1);

        assertEquals(lvl1, checkLvl);
    }

    @Test
    public void updateLevelUp() {
        LevelUp lvl1 = new LevelUp(
                1,
                20,
                LocalDate.of(2000,01,01)
        );
        lvl1 = dao.createLevelUp(lvl1);

        Integer pointUpdate = 100;
        lvl1.setPoints(pointUpdate);

        dao.updateLevelUp(lvl1);
        LevelUp checkLvl = dao.readLevelUp(lvl1.getLevel_up_id());

        assertEquals(pointUpdate, checkLvl.getPoints());
    }

    @Test
    public void deleteLevelUp() {
        LevelUp lvl1 = new LevelUp(
                1,
                20,
                LocalDate.of(2000,01,01)
        );
        lvl1 = dao.createLevelUp(lvl1);

        dao.deleteLevelUp(lvl1.getLevel_up_id());

        assertNull(dao.readLevelUp(lvl1.getLevel_up_id()));
    }
}