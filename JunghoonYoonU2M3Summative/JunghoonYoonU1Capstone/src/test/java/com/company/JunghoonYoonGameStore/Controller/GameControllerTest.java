package com.company.JunghoonYoonGameStore.Controller;

import com.company.JunghoonYoonGameStore.DAO.GameDao;
import com.company.JunghoonYoonGameStore.DTO.Game;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameDao gameDao;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getGame() throws Exception {
        Game game = new Game();
        game.setTitle("Starcraft");
        game.setEsrb_rating("T");
        game.setDescription("RTS GAME");
        game.setPrice(new BigDecimal("24.99"));
        game.setStudio("Blizzard");
        game.setQuantity(10);
        game.setGame_id(1);

        Game returnVal = game;

        String outputJson = mapper.writeValueAsString(game);

        when(gameDao.getGame(1)).thenReturn(returnVal);
        this.mockMvc.perform(get("/Game/get/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void getGameThatDoesNotExistReturns404() throws Exception {
        when(gameDao.getGame(100)).thenThrow(new IllegalArgumentException("Message must not be null or empty!"));

        this.mockMvc.perform(get("/Console/get/100"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllGames() throws Exception {
        Game game = new Game();
        game.setGame_id(1);
        game.setTitle("Starcraft");
        game.setEsrb_rating("T");
        game.setDescription("RTS GAME");
        game.setPrice(new BigDecimal("24.99"));
        game.setStudio("Blizzard");
        game.setQuantity(10);

        Game game2 = new Game();
        game2.setTitle("Starcraft");
        game2.setEsrb_rating("T");
        game2.setDescription("RTS GAME");
        game2.setPrice(new BigDecimal("24.99"));
        game2.setStudio("Blizzard");
        game2.setQuantity(10);

        List<Game> gList = new ArrayList<>();
        gList.add(game);
        gList.add(game2);

        when(gameDao.getAllGames()).thenReturn(gList);

        List<Game> listChecker = new ArrayList<>();
        listChecker.addAll(gList);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/Game"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void addGame() throws Exception {
        Game game = new Game();
        game.setTitle("Starcraft");
        game.setEsrb_rating("T");
        game.setDescription("RTS GAME");
        game.setPrice(new BigDecimal("24.99"));
        game.setStudio("Blizzard");
        game.setQuantity(10);

        String inputJson = mapper.writeValueAsString(game);

        Game game2 = new Game();
        game2.setTitle("Starcraft");
        game2.setEsrb_rating("T");
        game2.setDescription("RTS GAME");
        game2.setPrice(new BigDecimal("24.99"));
        game2.setStudio("Blizzard");
        game2.setQuantity(10);

        String outputJson = mapper.writeValueAsString(game2);

        when(gameDao.addGame(game)).thenReturn(game2);

        this.mockMvc.perform(post("/Game")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void updateGame() throws Exception {
        Game game = new Game();
        game.setGame_id(1);
        game.setTitle("Starcraft");
        game.setEsrb_rating("T");
        game.setDescription("RTS GAME");
        game.setPrice(new BigDecimal("24.99"));
        game.setStudio("Blizzard");
        game.setQuantity(10);
        game.setGame_id(1);

        String inputJson = mapper.writeValueAsString(game);
        String outputJson = mapper.writeValueAsString(game);

        this.mockMvc.perform(put("/Game/update")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isAccepted());
    }

    @Test
    public void deleteGame() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/Game/delete/1"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string("Game deleted."));
    }

    @Test
    public void getGamesByStudio() throws Exception {
        Game game = new Game();
        game.setGame_id(1);
        game.setTitle("Starcraft");
        game.setEsrb_rating("T");
        game.setDescription("RTS GAME");
        game.setPrice(new BigDecimal("24.99"));
        game.setStudio("Blizzard");
        game.setQuantity(10);
        game.setGame_id(1);

        List<Game> returnVal = gameDao.getGamesByStudio(game.getStudio());

        String outputJson = mapper.writeValueAsString(returnVal);

        when(gameDao.getGamesByStudio("Blizzard")).thenReturn(returnVal);
        this.mockMvc.perform(get("/Game/studio/Blizzard"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void getGamesByRating() throws Exception {
        Game game = new Game();
        game.setGame_id(1);
        game.setTitle("Starcraft");
        game.setEsrb_rating("T");
        game.setDescription("RTS GAME");
        game.setPrice(new BigDecimal("24.99"));
        game.setStudio("Blizzard");
        game.setQuantity(10);
        game.setGame_id(1);

        List<Game> returnVal = gameDao.getGamesByRating(game.getEsrb_rating());

        String outputJson = mapper.writeValueAsString(returnVal);

        when(gameDao.getGamesByRating("T")).thenReturn(returnVal);
        this.mockMvc.perform(get("/Game/rating/T"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void getGamesByTitle() throws Exception{
        Game game = new Game();
        game.setTitle("Starcraft");
        game.setEsrb_rating("T");
        game.setDescription("RTS GAME");
        game.setPrice(new BigDecimal("24.99"));
        game.setStudio("Blizzard");
        game.setQuantity(10);
        game.setGame_id(1);
        game.setGame_id(1);

        List<Game> returnVal = gameDao.getGamesByTitle(game.getTitle());

        String outputJson = mapper.writeValueAsString(returnVal);

        when(gameDao.getGamesByTitle("Starcraft")).thenReturn(returnVal);
        this.mockMvc.perform(get("/Game/title/Starcraft"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
}