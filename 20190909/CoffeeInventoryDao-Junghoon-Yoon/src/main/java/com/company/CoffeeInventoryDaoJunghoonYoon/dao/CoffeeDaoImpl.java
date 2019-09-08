package com.company.CoffeeInventoryDaoJunghoonYoon.dao;

import com.company.CoffeeInventoryDaoJunghoonYoon.model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CoffeeDaoImpl implements CoffeeDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_COFFEE_SQL =
            "INSERT INTO coffee (roaster_id, name, count, unit_price, description, type) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_COFFEE_SQL =
            "SELECT * FROM coffee WHERE coffee_id = ?";

    private static final String SELECT_ALL_COFFEES_SQL =
            "SELECT * FROM coffee";

    private static final String UPDATE_COFFEE_SQL =
            "UPDATE coffee SET roaster_id = ?, name = ?, count = ?, unit_price = ?, description = ?, type = ? where coffee_id = ?";

    private static final String DELETE_COFFEE =
            "DELETE FROM coffee WHERE coffee_id = ?";

    private static final String SELECT_COFFEE_BY_TYPE_SQL =
            "SELECT * FROM coffee WHERE type = ?";

    private static final String SELECT_COFFEE_BY_ROASTER_ID_SQL =
            "SELECT * FROM coffee WHERE roaster_id = ?";

    @Autowired
    public CoffeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Coffee addCoffee(Coffee coffee) {
        jdbcTemplate.update(INSERT_COFFEE_SQL,
                coffee.getRoaster_id(),
                coffee.getName(),
                coffee.getCount(),
                coffee.getUnit_price(),
                coffee.getDescription(),
                coffee.getType());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        coffee.setCoffee_id(id);

        return coffee;
    }

    @Override
    public Coffee getCoffee(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_COFFEE_SQL, this::mapRowToCoffee, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Coffee> getAllCoffees() {
        return jdbcTemplate.query(SELECT_ALL_COFFEES_SQL, this::mapRowToCoffee);
    }

    @Override
    public void updateCoffee(Coffee coffee) {
        jdbcTemplate.update(UPDATE_COFFEE_SQL,
                coffee.getRoaster_id(),
                coffee.getName(),
                coffee.getCount(),
                coffee.getUnit_price(),
                coffee.getDescription(),
                coffee.getType());
    }

    @Override
    public void deleteCoffee(int id) {
        jdbcTemplate.update(DELETE_COFFEE, id);
    }

    @Override
    public List<Coffee> getCoffeesByType(String type) {
        return jdbcTemplate.query(
                SELECT_COFFEE_BY_TYPE_SQL,
                this::mapRowToCoffee,
                type);
    }

    @Override
    public List<Coffee> getCoffeeByRoaster(int roasterId) {
        return jdbcTemplate.query(
                SELECT_COFFEE_BY_ROASTER_ID_SQL,
                this::mapRowToCoffee,
                roasterId);
    }

    private Coffee mapRowToCoffee(ResultSet rs, int rowNum) throws SQLException {
        Coffee coffee = new Coffee();
        coffee.setCoffee_id(rs.getInt("coffee_id"));
        coffee.setRoaster_id(rs.getInt("roaster_id"));
        coffee.setName(rs.getString("name"));
        coffee.setCount(rs.getInt("count"));
        coffee.setUnit_price(rs.getDouble("unit_price"));
        coffee.setDescription(rs.getString("description"));
        coffee.setType(rs.getString("type"));

        return coffee;
    }
}
