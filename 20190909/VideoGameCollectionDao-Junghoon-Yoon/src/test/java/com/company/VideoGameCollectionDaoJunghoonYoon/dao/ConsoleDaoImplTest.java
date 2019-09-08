package com.company.VideoGameCollectionDaoJunghoonYoon.dao;

import com.company.VideoGameCollectionDaoJunghoonYoon.model.Console;
import com.company.VideoGameCollectionDaoJunghoonYoon.model.Game;
import com.company.VideoGameCollectionDaoJunghoonYoon.model.Publisher;
import com.company.VideoGameCollectionDaoJunghoonYoon.model.Type;
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
public class ConsoleDaoImplTest {

    @Autowired
    GameDao gameDao;

    @Autowired
    ConsoleDao consoleDao;

    @Autowired
    PublisherDao publisherDao;

    @Autowired
    TypeDao typeDao;

    @Before
    public void setUp() throws Exception {
        List<Game> gList = gameDao.getAllGames();
        for(Game g : gList) {
            gameDao.deleteGame(g.getGame_id());
        }

        List<Console> cList = consoleDao.getAllConsoles();
        for(Console c : cList) {
            consoleDao.deleteConsole(c.getConsole_id());
        }

        List<Publisher> pList = publisherDao.getAllPublisher();
        for(Publisher p : pList) {
            publisherDao.deletePublisher(p.getPublisher_id());
        }

        List<Type> tList = typeDao.getAllTypes();
        for(Type t : tList) {
            typeDao.deleteType(t.getType_id());
        }
    }



    @Test
    public void addGetDeleteConsole() {
        Console console = new Console();

        console.setName("XBOX");

        console.setYear("2005");

        consoleDao.addConsole(console);

        Console console1 = consoleDao.getConsole(console.getConsole_id());

        assertEquals(console1, console);

        consoleDao.deleteConsole(console.getConsole_id());

        console1 = consoleDao.getConsole(console.getConsole_id());

        assertNull(console1);

    }


    @Test
    public void getAllConsoles() {
        Console console = new Console();

        console.setName("XBOX");

        console.setYear("2005");

        consoleDao.addConsole(console);

        console = new Console();
        console.setName("PLAYSTATION");
        console.setYear("2001");
        consoleDao.addConsole(console);

        List<Console> cList = consoleDao.getAllConsoles();

        assertEquals(cList.size(), 2);
    }

    @Test
    public void updateConsole() {
        Console console = new Console();
        console.setName("XBOX");
        console.setYear("2005");
        consoleDao.addConsole(console);


        console.setName("NEW XBOX");
        console.setYear("2019");
        consoleDao.updateConsole(console);

        Console console1 = consoleDao.getConsole(console.getConsole_id());

        assertEquals(console1, console);
    }

}