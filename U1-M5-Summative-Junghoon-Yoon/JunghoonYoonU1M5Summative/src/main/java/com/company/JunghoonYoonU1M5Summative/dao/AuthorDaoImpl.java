package com.company.JunghoonYoonU1M5Summative.dao;


import com.company.JunghoonYoonU1M5Summative.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDAO {

    //Prepared Statements
    private static final String ADD_AUTHOR_SQL =
            "INSERT INTO author (first_name, last_name, street, city, state, postal_code, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_AUTHOR_SQL =
            "SELECT * FROM author WHERE author_id = ?";

    private static final String GET_ALL_AUTHORS_SQL =
            "SELECT * FROM author";

    private static final String UPDATE_AUTHOR_SQL =
            "UPDATE author SET first_name = ?, last_name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, email = ? WHERE author_id = ?";

    private static final String DELETE_AUTHOR_SQL =
            "DELETE FROM author WHERE author_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Implemented Methods

    /**
     * Retrieves author from the DB
     * @param id
     * @return
     */
    @Override
    public Author getAuthor(int id) {
       try {
           return jdbcTemplate.queryForObject(GET_AUTHOR_SQL, this::mapRowToAuthor, id);
       } catch (EmptyResultDataAccessException e) {
           return null;
       }
    }

    /**
     * Retrieves all authors from the DB
     * @return
     */
    @Override
    public List<Author> getAllAuthors() {
        return jdbcTemplate.query(GET_ALL_AUTHORS_SQL, this::mapRowToAuthor);
    }

    /**
     * Adds an author Object to the DB
     * @param author
     * @return
     */

    @Override
    public Author addAuthor(Author author) {
       jdbcTemplate.update(ADD_AUTHOR_SQL,
               author.getFirst_name(),
               author.getLast_name(),
               author.getStreet(),
               author.getCity(),
               author.getState(),
               author.getPostal_code(),
               author.getPhone(),
               author.getEmail());
       int id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);
       author.setId(id);

       return author;
    }

    /**
     * Updates an author by the primary key ID
     * @param author
     */
    @Override
    public void updateAuthor(Author author) {
        jdbcTemplate.update(UPDATE_AUTHOR_SQL,
                author.getFirst_name(),
                author.getLast_name(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getPostal_code(),
                author.getPhone(),
                author.getEmail(),
                author.getId());
    }

    /**
     * Deletes an author by the Primary id
     * @param id
     */
    @Override
    public void deleteAuthor(int id) {
        jdbcTemplate.update(DELETE_AUTHOR_SQL, id);

    }

    //Helper Methods
    private Author mapRowToAuthor(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author();
        author.setId(rs.getInt("author_id"));
        author.setFirst_name(rs.getString("first_name"));
        author.setLast_name(rs.getString("last_name"));
        author.setStreet(rs.getString("street"));
        author.setCity(rs.getString("city"));
        author.setState(rs.getString("state"));
        author.setPostal_code(rs.getString("postal_code"));
        author.setPhone(rs.getString("phone"));
        author.setEmail(rs.getString("email"));

        return author;
    }
}
