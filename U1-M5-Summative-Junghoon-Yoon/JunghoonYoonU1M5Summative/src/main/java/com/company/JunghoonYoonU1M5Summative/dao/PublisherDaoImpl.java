package com.company.JunghoonYoonU1M5Summative.dao;

import com.company.JunghoonYoonU1M5Summative.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PublisherDaoImpl implements PublisherDAO{

    //Prepared Statements
    private static final String ADD_PUBLISHER_SQL =
            "INSERT INTO publisher (name, street, city, state, postal_code, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?) ";

    private static final String GET_PUBLISHER_SQL =
            "SELECT * FROM publisher WHERE publisher_id = ?";

    private static final String GET_ALL_PUBLISHERS_SQL =
            "SELECT * FROM publisher";

    private static final String UPDATE_PUBLISHER_SQL =
            "UPDATE publisher SET name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, email = ? WHERE publisher_id = ?";

    private static final String DELETE_PUBLISHER_SQL =
            "DELETE FROM publisher WHERE publisher_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PublisherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Gets a publisher by the ID from the DB
     * @param id
     * @return
     */
    @Override
    public Publisher getPublisher(int id) {
        try {
            return jdbcTemplate.queryForObject(GET_PUBLISHER_SQL, this::mapRowToPublisher, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Gets all publishers from the DB
     * @return
     */
    @Override
    public List<Publisher> getAllPublishers() {
        return jdbcTemplate.query(GET_ALL_PUBLISHERS_SQL, this::mapRowToPublisher);
    }

    /**
     * Adds a publisher as an Object to the DB
     * @param publisher
     * @return
     */
    @Override
    public Publisher addPublisher(Publisher publisher) {
        jdbcTemplate.update(ADD_PUBLISHER_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostal_code(),
                publisher.getPhone(),
                publisher.getEmail()
        );

        int id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);
        publisher.setId(id);

        return publisher;
    }

    /**
     * Updates a publisher in the DB by the ID
     * @param publisher
     */
    @Override
    public void updatePublisher(Publisher publisher) {
        jdbcTemplate.update(UPDATE_PUBLISHER_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostal_code(),
                publisher.getPhone(),
                publisher.getEmail(),
                publisher.getId()
        );
    }

    /**
     * Deletes a publisher by their ID from the DB
     * @param id
     */
    @Override
    public void deletePublisher(int id) {
        jdbcTemplate.update(DELETE_PUBLISHER_SQL, id);
    }

    private Publisher mapRowToPublisher(ResultSet rs, int rowNum) throws SQLException {
        Publisher publisher = new Publisher();
        publisher.setId(rs.getInt("publisher_id"));
        publisher.setName(rs.getString("name"));
        publisher.setStreet(rs.getString("street"));
        publisher.setCity(rs.getString("city"));
        publisher.setState(rs.getString("state"));
        publisher.setPostal_code(rs.getString("postal_code"));
        publisher.setPhone(rs.getString("phone"));
        publisher.setEmail(rs.getString("email"));

        return publisher;
    }
}
