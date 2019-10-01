package com.trilogyed.rsp.dao;

import com.trilogyed.rsp.model.Rsvp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RsvpDaoJdbcTemplateImpl implements RsvpDao {
   private static final String INSERT_RSVP =
           "insert into rsvp(guest_name, total_attending) values (?, ?)";

   private static final String SELECT_RSVP =
           "select * from rsvp WHERE rsvp_id = ?";

   private static final String SELECT_ALL_RSVPS =
           "SELECT * FROM rsvp";

   private static final String UPDATE_RSVP =
           "UPDATE rsvp SET guest_name = ?, total_attending = ?, where rsvp_id = ?";

   private static final String DELETE_RSVP =
           "DELETE FROM rsvp where rsvp_id = ?";

   @Autowired
    JdbcTemplate jdbcTemplate;

    public RsvpDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Rsvp addRsvp(Rsvp rsvp) {
    jdbcTemplate.update(INSERT_RSVP,
            rsvp.getGuestName(),
            rsvp.getTotalAttending());
        int id = jdbcTemplate.queryForObject("SELECT LAST INSERT_ID()", Integer.class);
        rsvp.setId(id);
        return rsvp;
    }

    @Override
    public Rsvp getRsvp(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_RSVP, this::mapRowToRsvp, id);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Rsvp> getAllRsvps() {
        return  jdbcTemplate.query(SELECT_ALL_RSVPS, this::mapRowToRsvp);
    }

    @Override
    public void updateRsvp(Rsvp rsvp) {
        jdbcTemplate.update(UPDATE_RSVP,
                rsvp.getGuestName(),
                rsvp.getTotalAttending(),
                rsvp.getId());
    }

    @Override
    public void deleteRsvp(int id) {
        jdbcTemplate.update(DELETE_RSVP, id);
    }

    private Rsvp mapRowToRsvp(ResultSet rs, int rowNum) throws SQLException {
        Rsvp rsvp = new Rsvp();
        rsvp.setId(rs.getInt("rsvp_id"));
        rsvp.setGuestName(rs.getString("guest_name"));
        rsvp.setTotalAttending(rs.getInt("total_attending"));
        return rsvp;
    }
}
