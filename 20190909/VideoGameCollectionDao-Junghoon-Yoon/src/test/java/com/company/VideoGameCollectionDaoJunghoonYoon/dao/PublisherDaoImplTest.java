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
public class PublisherDaoImplTest {

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
    public void addGetDeletePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Barnes");
        publisher.setWebsite("www.barnes.com");
        publisherDao.addPublisher(publisher);

        Publisher publisher1 = publisherDao.getPublisher(publisher.getPublisher_id());
        assertEquals(publisher, publisher1);

        publisherDao.deletePublisher(publisher.getPublisher_id());

        publisher1 = publisherDao.getPublisher(publisher.getPublisher_id());

        assertNull(publisher1);

    }

    @Test
    public void getAllPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Barnes");
        publisher.setWebsite("www.barnes.com");
        publisherDao.addPublisher(publisher);

        publisher = new Publisher();
        publisher.setName("Nobles");
        publisher.setWebsite("www.nobles.com");
        publisherDao.addPublisher(publisher);

        List<Publisher> pList = publisherDao.getAllPublisher();

        assertEquals(pList, 2);
    }

    @Test
    public void updatePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Barnes");
        publisher.setWebsite("www.barnes.com");
        publisherDao.addPublisher(publisher);

        publisher.setName("NEW NAME");
        publisher.setWebsite("NEW WEBSITE");
        publisherDao.updatePublisher(publisher);

        Publisher publisher1 = publisherDao.getPublisher(publisher.getPublisher_id());

        assertEquals(publisher1, publisher);
    }
}