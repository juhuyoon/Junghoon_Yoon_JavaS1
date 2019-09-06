package com.company.CarLotDaoJunghoonYoon.DAO;

import com.company.CarLotDaoJunghoonYoon.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarLotDaoImpl implements CarLotDao {
    //Prepared Statement Strings
    private static final String INSERT_CAR_SQL =
            "INSERT INTO car(make, model, year, color) values (?, ?, ?, ?)";

    private static final String SELECT_CAR_SQL =
            "SELECT * FROM car WHERE id = ?";

    private static final String SELECT_ALL_CARS_SQL =
            "SELECT * FROM car";

    private static final String DELETE_CAR_SQL =
            "DELETE FROM car WHERE id = ?";

    private static final String UPDATE_CAR_SQL =
            "UPDATE car SET make = ?, model = ?, year = ?, color = ? WHERE id = ?";

    private static final String SELECT_CARS_BY_MAKE_SQL =
            "SELECT * FROM car WHERE make = ?";

    private static final String SELECT_CARS_BY_COLOR_SQL =
            "SELECT * FROM car WHERE color = ?";

    //Creating a property of the class JdbcTemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarLotDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Adds Car based on the parameters of the car
     *
     * @param car
     * @return
     */
    @Override
    public Car addCar(Car car) {
        jdbcTemplate.update(INSERT_CAR_SQL,
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getColor()
                );
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        car.setId(id);

        return car;
    }

    /**
     * Retrieves the Car by searching for the id.
     *
     * @param id
     * @return
     */
    @Override
    public Car getCar(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_CAR_SQL, this::mapRowToCar, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Returns a list of all the cars in the database.
     *
     * @return
     */
    @Override
    public List<Car> getAllCars() {
        return jdbcTemplate.query(SELECT_ALL_CARS_SQL, this::mapRowToCar);
    }

    /**
     * Updates a specific Car in the database based on ID.
     *
     * @param car
     */
    @Override
    public void updateCars(Car car) {
        jdbcTemplate.update(UPDATE_CAR_SQL,
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getColor(),
                car.getId()
        );
    }

    /**
     * Removes a specific Car in the database based on the ID.
     *
     * @param id
     */
    @Override
    public void deleteCars(int id) {
        jdbcTemplate.update(DELETE_CAR_SQL, id);
    }

    /**
     * Retrieves a list of Cars based on the car make.
     *
     * @param make
     * @return
     */
    @Override
    public List<Car> getCarByMake(String make) {
        return jdbcTemplate.query(SELECT_CARS_BY_MAKE_SQL, this::mapRowToCar, make);
    }

    /**
     * Retrieves a list of Cars based on the car color.
     *
     * @param color
     * @return
     */
    @Override
    public List<Car> getCarByColor(String color) {
        return jdbcTemplate.query(SELECT_CARS_BY_COLOR_SQL, this::mapRowToCar, color);
    }


    /**
     * Maps Car table row to Car Object.
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    private Car mapRowToCar(ResultSet rs, int rowNum) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setMake(rs.getString("make"));
        car.setModel(rs.getString("model"));
        car.setYear(rs.getString("year"));
        car.setColor(rs.getString("color"));

        return car;
    }

}
