package com.company.JunghoonYoonU1Capstone.DAO;

import com.company.JunghoonYoonU1Capstone.DTO.Game;

import java.util.List;

public interface GameDao {
    Game addGame(Game game);

    Game getGame(Integer game_id);

    List<Game> getAllGames();

    void updateGame(Game game);

    void deleteGame(Integer game_id);

    List<Game> getGamesByStudio(String studio);

    List<Game> getGamesByRating(String esrb_Rating);

    List<Game> getGamesByTitle(String title);

}
