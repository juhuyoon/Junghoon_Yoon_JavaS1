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
    private Game game;

    //Getting Game object by id
    @GetMapping("/get/{gameId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Game getGame(@PathVariable(name="gameId") Integer gameId) {
        return gameDao.getGame(gameId);
    }

    //Getting all the games
    @GetMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    //Posting a Game Object
    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Game addGame(@RequestBody @Valid Game game) {
        gameDao.addGame(game);
        return game;
    }

    //Updating a Game Object
    @PutMapping("/update")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String updateGame(@RequestBody @Valid Game game) {
        gameDao.updateGame(game);
        return "Game has been updated!";
    }

    //Deleting a Game Object by id
    @DeleteMapping("/delete/{gameId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteGame(@PathVariable(name="gameId") int gameId) {
        gameDao.deleteGame(gameId);
        return "Game deleted.";
    }

    //Getting a List of Game Object by Studio
    @GetMapping("/studio/{Studio}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Game> getGamesByStudio(@PathVariable(name="Studio") String studio) {
        return gameDao.getGamesByStudio(studio);
    }

    //Getting a List of Game Object by Rating
    @GetMapping("/rating/{Rating}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Game> getGamesByRating(@PathVariable(name="Rating") String rating) {
        return gameDao.getGamesByRating(rating);
    }

    //Getting a List of Game Object by Title
    @GetMapping("/title/{Title}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Game> getGamesByTitle(@PathVariable(name = "Title") String title) {
        return gameDao.getGamesByTitle(title);
    }
}
