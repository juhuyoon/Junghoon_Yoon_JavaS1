package com.company.JunghoonYoonU1Capstone.Controller;

import com.company.JunghoonYoonU1Capstone.DAO.GameDao;
import com.company.JunghoonYoonU1Capstone.DTO.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value ="/Game")
public class GameController {
    @Autowired
    private GameDao gameDao;

    @GetMapping("/{gameId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Game getGame(@PathVariable(name="gameId") Integer gameId) {
        return gameDao.getGame(gameId);
    }

    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Game addGame(@RequestBody @Valid Game game) {
        gameDao.addGame(game);
        return game;
    }

    @PutMapping("/")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String updateGame(@RequestBody @Valid Game game) {
        gameDao.updateGame(game);
        return "Game has been updated!";
    }

    @DeleteMapping("/{gameId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteGame(@PathVariable(name="gameId") int gameId) {
        gameDao.deleteGame(gameId);
        return "Game deleted.";
    }

    @GetMapping("/{Studio}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Game> getGamesByStudio(@PathVariable(name="Studio") String studio) {
        return gameDao.getGamesByStudio(studio);
    }

    @GetMapping("/{Rating}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Game> getGamesByRating(@PathVariable(name="Rating") String rating) {
        return gameDao.getGamesByRating(rating);
    }

    @GetMapping("/{Title}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Game> getGamesByTitle(@PathVariable(name = "Title") String title) {
        return gameDao.getGamesByTitle(title);
    }
}
