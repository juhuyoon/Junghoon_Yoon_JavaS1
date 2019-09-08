package com.company.VideoGameCollectionDaoJunghoonYoon.dao;

import com.company.VideoGameCollectionDaoJunghoonYoon.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TypeDaoImpl implements TypeDao{
    //Prepared Statements
    private static final String INSERT_TYPE_SQL =
            "INSERT INTO type(name, description) values (?, ?)";

    private static final String SELECT_TYPE_SQL =
            "SELECT * FROM type WHERE type_id = ?";

    private static final String SELECT_ALL_TYPES_SQL =
            "SELECT * FROM type";

    private static final String UPDATE_TYPE_SQL =
            "UPDATE type SET name = ?, year = ? WHERE type_id = ?";

    private static final String DELETE_TYPE_SQL =
            "DELETE FROM type WHERE type_id = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TypeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Type addType(Type type) {
        jdbcTemplate.update(
                INSERT_TYPE_SQL,
                type.getName(),
                type.getDescription()
        );
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        type.setType_id(id);

        return type;
    }

    @Override
    public Type getType(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_TYPE_SQL, this::mapRowToType, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Type> getAllTypes() {
        return jdbcTemplate.query(SELECT_ALL_TYPES_SQL, this::mapRowToType);
    }

    @Override
    public void updateType(Type type) {
        jdbcTemplate.update(
                UPDATE_TYPE_SQL,
                type.getName(),
                type.getDescription(),
                type.getType_id()
        );
    }

    @Override
    public void deleteType(int id) {
        jdbcTemplate.update(DELETE_TYPE_SQL, id);
    }

    private Type mapRowToType(ResultSet rs, int rowNum) throws SQLException {
        Type type = new Type();
        type.setType_id(rs.getInt("type_id"));
        type.setName(rs.getString("name"));
        type.setDescription(rs.getString("description"));

        return type;
    }
}
