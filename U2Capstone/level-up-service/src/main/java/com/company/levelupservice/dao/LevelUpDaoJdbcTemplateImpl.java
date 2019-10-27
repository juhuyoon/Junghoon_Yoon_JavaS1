package com.company.levelupservice.dao;

import com.company.levelupservice.model.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LevelUpDaoJdbcTemplateImpl implements LevelUpDao{

    //prepared statements
    private static final String INSERT_LEVELUP_SQL = "INSERT INTO level_up (customer_id, points, member_date) VALUE (?, ?, ?)";
    private static final String SELECT_LEVELUP_SQL = "SELECT * FROM level_up WHERE level_up_id = ?";
    private static final String SELECT_ALL_LEVELUPS_SQL = "SELECT * FROM level_up";
    private static final String SELECT_BY_CUSTOMER_SQL = "SELECT * FROM level_up WHERE customer_id = ?";
    private static final String UPDATE_LEVELUP_SQL = "UPDATE level_up SET customer_id = ?, points = ?, member_date = ? WHERE level_up_id = ?";
    private static final String DELETE_LEVELUP_SQL = "DELETE FROM level_up WHERE level_up_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LevelUpDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public LevelUp createLevelUp(LevelUp lvl) {
        jdbcTemplate.update(INSERT_LEVELUP_SQL, lvl.getCustomer_id(), lvl.getPoints(), lvl.getMember_date());
        int id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);
        lvl.setLevel_up_id(id);
        return lvl;
    }

    @Override
    public LevelUp readLevelUp(int level_up_id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_LEVELUP_SQL, this::mapRowToObject, level_up_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<LevelUp> readAll() {
        return jdbcTemplate.query(SELECT_ALL_LEVELUPS_SQL, this::mapRowToObject);
    }

    @Override
    public LevelUp readByCustomer(int customer_id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_CUSTOMER_SQL, this::mapRowToObject, customer_id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void updateLevelUp(LevelUp lvl) {
        jdbcTemplate.update(UPDATE_LEVELUP_SQL, lvl.getCustomer_id(), lvl.getPoints(), lvl.getMember_date(), lvl.getLevel_up_id());
    }

    @Override
    public void deleteLevelUp(int level_up_id) {
        jdbcTemplate.update(DELETE_LEVELUP_SQL, level_up_id);
    }

    //row mapper
    private LevelUp mapRowToObject(ResultSet rs, int rowNum) throws SQLException{
        LevelUp levelUp = new LevelUp(
                rs.getInt("level_up_id"),
                rs.getInt("customer_id"),
                rs.getInt("points"),
                rs.getDate("member_date").toLocalDate()
        );
        return levelUp;
    }
}
