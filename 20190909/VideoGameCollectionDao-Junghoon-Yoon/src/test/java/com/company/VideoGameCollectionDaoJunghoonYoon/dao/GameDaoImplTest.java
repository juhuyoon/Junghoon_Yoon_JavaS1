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
public class GameDaoImplTest {


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
    public void addGetDeleteGame() {
        Console console = new Console();
        console.setName("XBOX");
        console.setYear("2005");
        console = consoleDao.addConsole(console);

        Publisher publisher = new Publisher();
        publisher.setName("Barnes");
        publisher.setWebsite("www.barnes.com");
        publisher = publisherDao.addPublisher(publisher);

        Type type = new Type();
        type.setName("Adventure");
        type.setDescription("Fun adventure");
        type = typeDao.addType(type);


        Game game = new Game();
        game.setConsole_id(console.getConsole_id());
        game.setType_id(type.getType_id());
        game.setPublisher_id(publisher.getPublisher_id());

        game = gameDao.addGame(game);
        Game game1 = gameDao.getGame(game.getGame_id());

        assertEquals(game1, game);

        gameDao.deleteGame(game.getGame_id());

        game1 = gameDao.getGame(game.getGame_id());

        assertNull(game1);

    }

    @Test
    public void getAllGames() {
        Console console = new Console();
        console.setName("XBOX");
        console.setYear("2005");
        console = consoleDao.addConsole(console);

        Publisher publisher = new Publisher();
        publisher.setName("Barnes");
        publisher.setWebsite("www.barnes.com");
        publisher = publisherDao.addPublisher(publisher);

        Type type = new Type();
        type.setName("Adventure");
        type.setDescription("Fun adventure");
        type = typeDao.addType(type);

        Game game = new Game();
        game.setConsole_id(console.getConsole_id());
        game.setType_id(type.getType_id());
        game.setPublisher_id(publisher.getPublisher_id());

        game = gameDao.addGame(game);

        game = new Game();
        game.setConsole_id(console.getConsole_id());
        game.setType_id(type.getType_id());
        game.setPublisher_id(publisher.getPublisher_id());
        game = gameDao.addGame(game);

        List<Game> gList = gameDao.getAllGames();

        assertEquals(gList.size(), 2);
    }

    @Test
    public void updateGame() {
        Console console = new Console();
        console.setName("XBOX");
        console.setYear("2005");
        console = consoleDao.addConsole(console);

        Publisher publisher = new Publisher();
        publisher.setName("Barnes");
        publisher.setWebsite("www.barnes.com");
        publisher = publisherDao.addPublisher(publisher);

        Type type = new Type();
        type.setName("Adventure");
        type.setDescription("Fun adventure");
        type = typeDao.addType(type);

        Game game = new Game();
        game.setConsole_id(console.getConsole_id());
        game.setType_id(type.getType_id());
        game.setPublisher_id(publisher.getPublisher_id());

        game = gameDao.addGame(game);

        game.setConsole_id(console.getConsole_id());
        game.setType_id(type.getType_id());
        game.setPublisher_id(publisher.getPublisher_id());

        gameDao.updateGame(game);

        Game game1 = gameDao.getGame(game.getGame_id());
        assertEquals(game1, game);
    }

    @Test
    public void getGamesByConsole() {
        Console console = new Console();
        console.setName("XBOX");
        console.setYear("2005");
        console = consoleDao.addConsole(console);

        Publisher publisher = new Publisher();
        publisher.setName("Barnes");
        publisher.setWebsite("www.barnes.com");
        publisher = publisherDao.addPublisher(publisher);

        Type type = new Type();
        type.setName("Adventure");
        type.setDescription("Fun adventure");
        type = typeDao.addType(type);

        Game game = new Game();
        game.setConsole_id(console.getConsole_id());
        game.setType_id(type.getType_id());
        game.setPublisher_id(publisher.getPublisher_id());

        game = gameDao.addGame(game);

        List<Game> gList = gameDao.getGamesByConsole(console.getConsole_id());
        assertEquals(gList.size(), 1);
    }

    @Test
    public void getGamesByPublishers() {
        Console console = new Console();
        console.setName("XBOX");
        console.setYear("2005");
        console = consoleDao.addConsole(console);

        Publisher publisher = new Publisher();
        publisher.setName("Barnes");
        publisher.setWebsite("www.barnes.com");
        publisher = publisherDao.addPublisher(publisher);

        Type type = new Type();
        type.setName("Adventure");
        type.setDescription("Fun adventure");
        type = typeDao.addType(type);

        Game game = new Game();
        game.setConsole_id(console.getConsole_id());
        game.setType_id(type.getType_id());
        game.setPublisher_id(publisher.getPublisher_id());

        game = gameDao.addGame(game);

        List<Game> gList = gameDao.getGamesByPublishers(publisher.getPublisher_id());
        assertEquals(gList.size(), 1);
    }

    @Test
    public void getGamesByType() {
        Console console = new Console();
        console.setName("XBOX");
        console.setYear("2005");
        console = consoleDao.addConsole(console);

        Publisher publisher = new Publisher();
        publisher.setName("Barnes");
        publisher.setWebsite("www.barnes.com");
        publisher = publisherDao.addPublisher(publisher);

        Type type = new Type();
        type.setName("Adventure");
        type.setDescription("Fun adventure");
        type = typeDao.addType(type);

        Game game = new Game();
        game.setConsole_id(console.getConsole_id());
        game.setType_id(type.getType_id());
        game.setPublisher_id(publisher.getPublisher_id());

        game = gameDao.addGame(game);

        List<Game> gList = gameDao.getGamesByType(type.getType_id());
        assertEquals(gList.size(), 1);
    }
}