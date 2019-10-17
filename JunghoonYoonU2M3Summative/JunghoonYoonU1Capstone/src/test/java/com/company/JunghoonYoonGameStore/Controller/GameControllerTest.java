package com.company.JunghoonYoonGameStore.Controller;

import com.company.JunghoonYoonGameStore.DAO.GameDao;
import com.company.JunghoonYoonGameStore.DTO.Game;
import com.company.JunghoonYoonGameStore.security.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SecurityConfig securityConfig;

    @MockBean
    private GameDao gameDao;

    @MockBean
    private DataSource dataSource;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                 .apply(springSecurity())
                                 .build();
    }

    @Test
    public void shouldGetGame() throws Exception {
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
    public void shouldReturn404ForGameThatDoesNotExist() throws Exception {
        when(gameDao.getGame(100)).thenThrow(new IllegalArgumentException("Message must not be null or empty!"));

        this.mockMvc.perform(get("/Console/get/100"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetAllGames() throws Exception {
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
    @WithMockUser(username = "adminUser", roles={"STAFF", "MANAGER", "ADMIN"})
    public void shouldAddGame() throws Exception {
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
                .with(csrf().asHeader())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));

    }

    @Test
    @WithMockUser(username = "adminUser", roles={"STAFF", "MANAGER", "ADMIN"})
    public void shouldUpdateGame() throws Exception {
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
                    .with(csrf().asHeader())
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isAccepted());
    }

    @Test
    @WithMockUser(username = "adminUser", roles={"STAFF", "MANAGER", "ADMIN"})
    public void shouldDeleteGame() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/Game/delete/1")
                    .with(csrf().asHeader()))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string("Game deleted."));
    }

    @Test
    public void shouldGetGamesByStudio() throws Exception {
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
    public void shouldGetGamesByRating() throws Exception {
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
    public void shouldGetGamesByTitle() throws Exception{
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