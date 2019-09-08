package com.company.CoffeeInventoryDaoJunghoonYoon.dao;

import com.company.CoffeeInventoryDaoJunghoonYoon.model.Roaster;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoasterDaoImpl implements RoasterDao  {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_ROASTER_SQL =
            "INSERT INTO roaster (name, street, city, state, postal_code, phone, email, note) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ROASTER_SQL =
            "SELECT * FROM roaster WHERE roaster_id = ?";

    private static final String SELECT_ALL_ROASTERS_SQL =
            "SELECT * FROM roaster";

    private static final String UPDATE_ROASTER_SQL =
            "UPDATE roaster SET name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, email = ?, note = ?";

    private static final String DELETE_ROASTER_SQL =
            "DELETE FROM roaster WHERE roaster_id = ?";

    public RoasterDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Roaster addRoaster(Roaster roaster) {
        jdbcTemplate.update(
                INSERT_ROASTER_SQL,
                roaster.getName(),
                roaster.getStreet(),
                roaster.getCity(),
                roaster.getStreet(),
                roaster.getPostal_code(),
                roaster.getPhone(),
                roaster.getEmail(),
                roaster.getNote());
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        roaster.setRoaster_id(id);

        return roaster;
    }

    @Override
    public Roaster getRoaster(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_ROASTER_SQL, this::mapRowToRoaster, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Roaster> getAllRoasters() {
        return jdbcTemplate.query(
                SELECT_ALL_ROASTERS_SQL,
                this::mapRowToRoaster
        );
    }

    @Override
    public void updateRoaster(Roaster roaster) {
    jdbcTemplate.update(UPDATE_ROASTER_SQL,
            roaster.getName(),
            roaster.getStreet(),
            roaster.getCity(),
            roaster.getState(),
            roaster.getPostal_code(),
            roaster.getPhone(),
            roaster.getEmail(),
            roaster.getNote());
    }

    @Override
    public void deleteRoaster(int id) {
        jdbcTemplate.update(DELETE_ROASTER_SQL, id);
    }

    private Roaster mapRowToRoaster(ResultSet rs, int rowNum) throws SQLException {
        Roaster roaster = new Roaster();
        roaster.setRoaster_id(rs.getInt("roaster_id"));
        roaster.setName(rs.getString("name"));
        roaster.setStreet(rs.getString("street"));
        roaster.setCity(rs.getString("city"));
        roaster.setState(rs.getString("state"));
        roaster.setPostal_code(rs.getString("postal_code"));
        roaster.setPhone(rs.getString("phone"));
        roaster.setEmail(rs.getString("email"));
        roaster.setNote(rs.getString("note"));

        return roaster;
    }
}
