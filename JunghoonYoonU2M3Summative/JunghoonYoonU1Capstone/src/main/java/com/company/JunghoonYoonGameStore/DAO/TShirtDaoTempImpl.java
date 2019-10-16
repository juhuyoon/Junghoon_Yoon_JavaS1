package com.company.JunghoonYoonGameStore.DAO;

import com.company.JunghoonYoonGameStore.DTO.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TShirtDaoTempImpl implements TshirtDao {

    //Prepared Statements
    private static final String ADD_TSHIRT_SQL =
            "INSERT INTO t_shirt (size, color, description, price, quantity) VALUES (?, ?, ?, ?, ?)";

    private static final String GET_TSHIRT_SQL =
            "SELECT * FROM t_shirt WHERE t_shirt_id = ?";

    private static final String GET_ALL_TSHIRTS_SQL =
            "SELECT * FROM t_shirt";

    private static final String UPDATE_TSHIRTS_SQL =
            "UPDATE t_shirt SET size = ?, color = ?, description = ?, price = ?, quantity = ? WHERE t_shirt_id = ?";

    private static final String DELETE_TSHIRTS_SQL =
            "DELETE FROM t_shirt WHERE t_shirt_id = ?";

    private static final String GET_TSHIRTS_BY_COLOR =
            "SELECT * FROM t_shirt WHERE color = ?";

    private static final String GET_TSHIRTS_BY_SIZE =
            "SELECT * FROM t_shirt WHERE size = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TShirtDaoTempImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * Adding tShirt Object to the Database
     * @param t_shirt
     * @return
     */
    @Override
    public TShirt addTShirt(TShirt t_shirt) {
        jdbcTemplate.update(ADD_TSHIRT_SQL,
                t_shirt.getSize(),
                t_shirt.getColor(),
                t_shirt.getDescription(),
                t_shirt.getPrice(),
                t_shirt.getQuantity());
                int id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);
                t_shirt.setT_shirt_id(id);

       return t_shirt;
    }

    /**
     * get t-shirt Object from the database by the t_Shirt_Id
     * @param t_Shirt_Id
     * @return
     */
    @Override
    public TShirt getTShirt(Integer t_Shirt_Id) {
        try {
            return jdbcTemplate.queryForObject(GET_TSHIRT_SQL, this::mapRowByTShirt, t_Shirt_Id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Get All the T Shirts back from the Database
     * @return
     */
    @Override
    public List<TShirt> getAllTShirts() {
        return jdbcTemplate.query(GET_ALL_TSHIRTS_SQL, this::mapRowByTShirt);
    }

    /**
     * Updates a T Shirt from the database.
     * @param tShirt
     */
    @Override
    public void updateTShirt(TShirt tShirt) {
        jdbcTemplate.update(UPDATE_TSHIRTS_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity(),
                tShirt.getT_shirt_id()
        );
    }

    /**
     * Deletes a T Shirt from the database based on the ID
     * @param t_Shirt_Id
     */
    @Override
    public void deleteTShirt(Integer t_Shirt_Id) {
        jdbcTemplate.update(DELETE_TSHIRTS_SQL, t_Shirt_Id);
    }

    /**
     * Retrieves a List of T Shirt from the database based on the color
     * @param color
     * @return
     */
    @Override
    public List<TShirt> getTShirtsByColor(String color) {
        return jdbcTemplate.query(GET_TSHIRTS_BY_COLOR, this::mapRowByTShirt, color);
    }

    /**
     * Retrieves a List of T Shirt from the database based on the size
     * @param size
     * @return
     */
    @Override
    public List<TShirt> getTShirtsBySize(String size) {
        return jdbcTemplate.query(GET_TSHIRTS_BY_SIZE, this::mapRowByTShirt, size);
    }

    private TShirt mapRowByTShirt(ResultSet rs, int rowNum) throws SQLException {
        TShirt tShirt = new TShirt();
        tShirt.setT_shirt_id(rs.getInt("t_shirt_id"));
        tShirt.setSize(rs.getString("size"));
        tShirt.setColor(rs.getString("color"));
        tShirt.setDescription(rs.getString("description"));
        tShirt.setPrice(rs.getBigDecimal("price"));
        tShirt.setQuantity(rs.getInt("quantity"));

        return tShirt;
    }
}
