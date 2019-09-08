package com.company.VideoGameCollectionDaoJunghoonYoon.dao;

import com.company.VideoGameCollectionDaoJunghoonYoon.model.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConsoleDaoImpl implements ConsoleDao {
    //Prepared Statements
    private static final String INSERT_CONSOLE_SQL =
        "INSERT INTO console(name, year) values (?, ?)";

    private static final String SELECT_CONSOLE_SQL =
            "SELECT * FROM console WHERE console_id = ?";

    private static final String SELECT_ALL_CONSOLES_SQL =
            "SELECT * FROM console";

    private static final String UPDATE_CONSOLE_SQL =
            "UPDATE console SET name = ?, year = ? WHERE console_id = ?";

    private static final String DELETE_CONSOLE_SQL =
            "DELETE FROM console WHERE console_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ConsoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Console addConsole(Console console) {
        jdbcTemplate.update(
                INSERT_CONSOLE_SQL,
                console.getName(),
                console.getYear()
        );
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        console.setConsole_id(id);

        return console;
    }

    @Override
    public Console getConsole(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CONSOLE_SQL, this::mapRowToConsole, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Console> getAllConsoles() {
       return jdbcTemplate.query(SELECT_ALL_CONSOLES_SQL, this::mapRowToConsole);
    }

    @Override
    public void updateConsole(Console console) {
        jdbcTemplate.update(
            UPDATE_CONSOLE_SQL,
                console.getName(),
                console.getYear(),
                console.getConsole_id()
        );
    }

    @Override
    public void deleteConsole(int id) {
        jdbcTemplate.update(DELETE_CONSOLE_SQL, id);
    }



    private com.company.VideoGameCollectionDaoJunghoonYoon.model.Console mapRowToConsole(ResultSet rs, int rowNum) throws SQLException {
        com.company.VideoGameCollectionDaoJunghoonYoon.model.Console console = new com.company.VideoGameCollectionDaoJunghoonYoon.model.Console();
        console.setConsole_id(rs.getInt("console_id"));
        console.setName(rs.getString("name"));
        console.setYear(rs.getString("year"));
        return console;
    }
}
