package com.company.JunghoonYoonGameStore.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConsoleDaoTempImpl implements ConsoleDao{

    //Prepared Statements
    private static final String INSERT_CONSOLE_SQL =
            "INSERT INTO console(model, manufacturer, memory_amount, processor, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String GET_CONSOLE_SQL =
            "SELECT * FROM console WHERE console_id = ?";

    private static final String GET_ALL_CONSOLES_SQL =
            "SELECT * FROM console";

    private static final String UPDATE_CONSOLE_SQL =
            "UPDATE console SET model = ?, manufacturer = ?, memory_amount = ?, processor = ?, price = ?, quantity = ? WHERE console_id = ?";

    private static final String DELETE_CONSOLE_SQL =
            "DELETE FROM console WHERE console_id = ?";

    private static final String GET_CONSOLE_BY_MANUFACTURER =
            "SELECT * FROM console WHERE manufacturer = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ConsoleDaoTempImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * Adds the Console Object to the Database
     * @param console
     * @return
     */
    @Override
    @Transactional
    public com.company.JunghoonYoonGameStore.DTO.Console addConsole(com.company.JunghoonYoonGameStore.DTO.Console console) {
        jdbcTemplate.update(INSERT_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemory_amount(),
                console.getProcessor(),
                console.getPrice(),
                console.getQuantity()
        );

        Integer consoleId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        console.setConsole_id(consoleId);
        return console;
    }

    /**
     * Retrieves the Console Object from the Database based on the console_id.
     * @param console_id
     * @return
     */
    @Override
    public com.company.JunghoonYoonGameStore.DTO.Console getConsole(Integer console_id) {
        try {
            return jdbcTemplate.queryForObject(GET_CONSOLE_SQL, this::mapConsoleToRow, console_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves a list of all the Console objects in the database.
     * @return
     */
    @Override
    public List<com.company.JunghoonYoonGameStore.DTO.Console> getAllConsoles() {
        return jdbcTemplate.query(GET_ALL_CONSOLES_SQL, this::mapConsoleToRow);
    }

    /**
     * Updates a Console Object.
     * @param console
     */
    @Override
    @Transactional
    public void updateConsole(com.company.JunghoonYoonGameStore.DTO.Console console) {
        jdbcTemplate.update(UPDATE_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemory_amount(),
                console.getProcessor(),
                console.getPrice(),
                console.getQuantity(),
                console.getConsole_id()
        );
    }

    /**
     * Deletes a console in the database
     * @param console_id
     */
    @Override
    @Transactional
    public void deleteConsole(Integer console_id) {
        jdbcTemplate.update(DELETE_CONSOLE_SQL, console_id);
    }

    /**
     * Returns a list of consoles based on the Manufacturer String.
     * @param manufacturer
     * @return
     */
    @Override
    public List<com.company.JunghoonYoonGameStore.DTO.Console> getConsolesByManufacturer(String manufacturer) {
        return jdbcTemplate.query(GET_CONSOLE_BY_MANUFACTURER, this::mapConsoleToRow, manufacturer);
    }


    private com.company.JunghoonYoonGameStore.DTO.Console mapConsoleToRow(ResultSet rs, int rowNumber) throws SQLException {
        com.company.JunghoonYoonGameStore.DTO.Console console = new com.company.JunghoonYoonGameStore.DTO.Console();
        console.setConsole_id(rs.getInt("console_id"));
        console.setModel(rs.getString("model"));
        console.setManufacturer(rs.getString("manufacturer"));
        console.setMemory_amount(rs.getString("memory_amount"));
        console.setProcessor(rs.getString("processor"));
        console.setPrice(rs.getBigDecimal("price"));
        console.setQuantity(rs.getInt("quantity"));

        return console;
    }
}
