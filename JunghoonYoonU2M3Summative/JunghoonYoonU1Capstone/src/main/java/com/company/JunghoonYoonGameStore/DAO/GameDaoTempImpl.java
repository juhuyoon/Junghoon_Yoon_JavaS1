package com.company.JunghoonYoonGameStore.DAO;

import com.company.JunghoonYoonGameStore.DTO.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GameDaoTempImpl implements GameDao {
    //Prepared Statements
    private static final String ADD_GAME_SQL =
            "INSERT INTO game (title, esrb_rating, description, price, studio, quantity) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String GET_GAME_SQL =
            "SELECT * FROM game WHERE game_id = ?";

    private static final String GET_ALL_GAMES_SQL =
            "SELECT * FROM game";

    private static final String UPDATE_GAME_SQL =
            "UPDATE game SET title = ?, esrb_rating = ?, description = ?, price = ?, studio = ?, quantity = ? WHERE game_id = ?";

    private static final String DELETE_GAME_SQL =
            "DELETE FROM game WHERE game_id = ?";

    private static final String GET_GAMES_BY_STUDIO_SQL =
            "SELECT * FROM game WHERE studio = ?";

    private static final String GET_GAMES_BY_RATINGS_SQL =
            "SELECT * FROM game WHERE esrb_rating = ?";

    private static final String GET_GAMES_BY_TITLE_SQL =
            "SELECT * FROM game WHERE title = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDaoTempImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * Adds a Game object to the Database
     * @param game
     * @return
     */
    @Override
    @Transactional
    public Game addGame(Game game) {
        jdbcTemplate.update(ADD_GAME_SQL,
                game.getTitle(),
                game.getEsrb_rating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity()
        );
        Integer id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);
        game.setGame_id(id);

        return game;
    }

    /**
     * Gets a game object from the database based on the game_id
     * @param game_id
     * @return
     */
    @Override
    public Game getGame(Integer game_id) {
        try {
            return jdbcTemplate.queryForObject(GET_GAME_SQL, this::mapRowToGame, game_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves all games in the database
     * @return
     */
    @Override
    public List<Game> getAllGames() {
        return jdbcTemplate.query(GET_ALL_GAMES_SQL, this::mapRowToGame);
    }

    /**
     * Updates the game Object in the database.
     * @param game
     */
    @Override
    @Transactional
    public void updateGame(Game game) {
        jdbcTemplate.update(UPDATE_GAME_SQL,
                game.getTitle(),
                game.getEsrb_rating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity(),
                game.getGame_id());
    }

    /**
     * Deletes the game based on the ID in the database.
     * @param game_id
     */
    @Override
    @Transactional
    public void deleteGame(Integer game_id) {
        jdbcTemplate.update(DELETE_GAME_SQL, game_id);
    }

    /**
     * Gets the Game based on the studio in the database.
     * @param studio
     * @return
     */
    @Override
    public List<Game> getGamesByStudio(String studio) {
        return jdbcTemplate.query(GET_GAMES_BY_STUDIO_SQL, this::mapRowToGame, studio);
    }

    /**
     * Gets the Game based on the ratings in the database.
     * @param esrb_Rating
     * @return
     */
    @Override
    public List<Game> getGamesByRating(String esrb_Rating) {
        return jdbcTemplate.query(GET_GAMES_BY_RATINGS_SQL, this::mapRowToGame, esrb_Rating);
    }

    /**
     * Gets the Game based on the title in the database.
     * @param title
     * @return
     */
    @Override
    public List<Game> getGamesByTitle(String title) {
        return jdbcTemplate.query(GET_GAMES_BY_TITLE_SQL, this::mapRowToGame, title);
    }

    private Game mapRowToGame(ResultSet rs, int rowNum) throws SQLException {
        Game game = new Game();
        game.setGame_id(rs.getInt("game_id"));
        game.setTitle(rs.getString("title"));
        game.setEsrb_rating(rs.getString("esrb_rating"));
        game.setDescription(rs.getString("description"));
        game.setPrice(rs.getBigDecimal("price"));
        game.setStudio(rs.getString("studio"));
        game.setQuantity(rs.getInt("quantity"));

        return game;
    }
}
