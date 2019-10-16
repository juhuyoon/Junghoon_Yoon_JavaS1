package com.company.JunghoonYoonGameStore.DAO;

import com.company.JunghoonYoonGameStore.DTO.Game;
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
public class GameDaoTest {

    @Autowired
    private GameDao gameDao;

    private Game game1, game2;

    @Before
    public void setUp() throws Exception {
        gameDao.getAllGames().forEach(g -> {
            gameDao.deleteGame(g.getGame_id());
        });

    game1 = new Game();
    game1.setTitle("Harvest Moon");
    game1.setEsrb_rating("G");
    game1.setDescription("Farming Game");
    game1.setPrice(new BigDecimal("15.99"));
    game1.setStudio("EA");
    game1.setQuantity(10);

    game2 = new Game();
    game2.setTitle("Starcraft");
    game2.setEsrb_rating("PG-13");
    game2.setDescription("RTS game");
    game2.setPrice(new BigDecimal("25.99"));
    game2.setStudio("Blizzard");
    game2.setQuantity(100);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGame() {
        game1 = gameDao.addGame(game1);
        assertEquals(1, gameDao.getAllGames().size());
    }

    @Test
    public void getGame() {
        game1 = gameDao.addGame(game1);
        Game gameTest = gameDao.getGame(game1.getGame_id());

        assertEquals(game1, gameTest);
    }

    @Test
    public void getAllGames() {
        gameDao.addGame(game1);
        gameDao.addGame(game2);

        List<Game> gList = gameDao.getAllGames();
        assertEquals(2, gList.size());
    }

    @Test
    public void updateGame() {
        gameDao.addGame(game1);
        game1.setDescription("NEW DESC");
        Game newGame = gameDao.getGame(game1.getGame_id());

        assertNotEquals(game1, newGame);
    }

    @Test
    public void deleteGame() {
        gameDao.addGame(game1);
        gameDao.deleteGame(game1.getGame_id());

        Game gameTest = gameDao.getGame(game1.getGame_id());

        assertNull(gameTest);
    }

    @Test
    public void getGamesByStudio() {
        gameDao.addGame(game1);
        gameDao.getGamesByStudio(game1.getStudio());

        Game gameTest = gameDao.getGame(game1.getGame_id());

        assertEquals(gameTest, game1);
    }

    @Test
    public void getGamesByRating() {
        gameDao.addGame(game1);
        gameDao.getGamesByRating(game1.getEsrb_rating());

        Game gameTest = gameDao.getGame(game1.getGame_id());
        assertEquals(gameTest, game1);
    }

    @Test
    public void getGamesByTitle() {
        gameDao.addGame(game1);
        gameDao.getGamesByTitle(game1.getTitle());

        Game gameTest = gameDao.getGame(game1.getGame_id());

        assertEquals(gameTest, game1);
    }
}