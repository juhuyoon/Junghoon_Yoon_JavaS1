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
public class TypeDaoImplTest {

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
    public void addGetDeleteType() {
        Type type = new Type();
        type.setName("Action");
        type.setDescription("Super adventurous");
        type = typeDao.addType(type);

        Type type1 = typeDao.getType(type.getType_id());

        assertEquals(type, type1);

        typeDao.deleteType(type.getType_id());
        type1 = typeDao.getType(type.getType_id());

        assertNull(type1);

    }

    @Test
    public void getAllTypes() {
        Type type = new Type();
        type.setName("Action");
        type.setDescription("Super adventurous");
        type = typeDao.addType(type);

        type = new Type();
        type.setName("Romance");
        type.setDescription("Super Comfy");
        type = typeDao.addType(type);

        type = new Type();
        type.setName("Unique");
        type.setDescription("Very Different");
        type = typeDao.addType(type);

        List<Type> tList = typeDao.getAllTypes();

        assertEquals(tList, 3);
    }

    @Test
    public void updateType() {
        Type type = new Type();
        type.setName("Action");
        type.setDescription("Super adventurous");
        typeDao.addType(type);

        type = new Type();
        type.setName("NEW NAME");
        type.setDescription("NEW PEOPLE");
        typeDao.updateType(type);

        Type type1 = typeDao.getType(type.getType_id());

        assertEquals(type1, type);
    }


}