package com.company.JunghoonYoonGameStore.DAO;

import com.company.JunghoonYoonGameStore.DTO.Console;
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
public class ConsoleDaoTest {

    @Autowired
    private ConsoleDao consoleDao;

    private Console console, console2;


    @Before
    public void setUp() throws Exception {
        consoleDao.getAllConsoles().forEach(c -> {
            consoleDao.deleteConsole(c.getConsole_id());
        });

        console = new Console();
        console.setModel("PC");
        console.setManufacturer("Microsoft");
        console.setMemory_amount("500GB");
        console.setProcessor("Intel");
        console.setPrice(new BigDecimal("599.99"));
        console.setQuantity(5);

        console2 = new Console();
        console2.setModel("XBOX");
        console2.setManufacturer("Microsoft");
        console2.setMemory_amount("15GB");
        console2.setProcessor("Non");
        console2.setPrice(new BigDecimal("300.00"));
        console2.setQuantity(10);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addConsole() {
        console = consoleDao.addConsole(console);
        assertEquals(1, consoleDao.getAllConsoles().size());
    }

    @Test
    public void getConsole() {
        console = consoleDao.addConsole(console);
        Console consoleTest = consoleDao.getConsole(console.getConsole_id());

        assertEquals(consoleTest, console);
    }

    @Test
    public void getAllConsoles() {
        consoleDao.addConsole(console);
        consoleDao.addConsole(console2);

        List<Console> cList = consoleDao.getAllConsoles();
        assertEquals(2, cList.size());
    }

    @Test
    public void updateConsole() {
        consoleDao.addConsole(console);
        console.setProcessor("SOMETHING ELSE");
        Console consoleTest = consoleDao.getConsole(console.getConsole_id());

        assertNotEquals(console, consoleTest);
    }

    @Test
    public void deleteConsole() {
        consoleDao.addConsole(console);
        consoleDao.deleteConsole(console.getConsole_id());

        Console consoleTest = consoleDao.getConsole(console.getConsole_id());

        assertNull(consoleTest);
    }

    @Test
    public void getConsolesByManufacturer() {
        consoleDao.addConsole(console);
        consoleDao.getConsolesByManufacturer(console.getManufacturer());

        Console consoleTest = consoleDao.getConsole(console.getConsole_id());

        assertEquals(consoleTest, console);
    }
}