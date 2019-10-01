package com.trilogyed.rsp.dao;

import com.trilogyed.rsp.model.Rsvp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RsvpDaoJdbcTemplateImplTest {

    @Autowired
    RsvpDao dao;

    @Before
    public void setUp() throws Exception {
        List<Rsvp> rsvps = dao.getAllRsvps();

        rsvps.stream().forEach(rsvp -> dao.deleteRsvp(rsvp.getId()));
    }


    @Test
    public void addGetDeleteRsvp() {
        Rsvp rsvp = new Rsvp("John Doe", 2);
        rsvp = dao.addRsvp(rsvp);
        Rsvp fromDao = dao.getRsvp(rsvp.getId());
        assertEquals(fromDao, rsvp);
        dao.deleteRsvp(rsvp.getId());
        fromDao = dao.getRsvp(rsvp.getId());
        assertNull(fromDao);

    }

    @Test
    public void getAllRsvps() {
        Rsvp rsvp = new Rsvp("Sally Smith", 4);
        dao.addRsvp(rsvp);

        rsvp = new Rsvp("John Doe", 3);
        dao.addRsvp(rsvp);

        List<Rsvp> lRsvp = dao.getAllRsvps();
        assertEquals(2, lRsvp.size());
    }

    @Test
    public void updateRsvp() {
        Rsvp rsvp = new Rsvp("John Jones", 5);
        rsvp = dao.addRsvp(rsvp);
        rsvp.setGuestName("NEW NAME");
        dao.updateRsvp(rsvp);
        Rsvp fromDao = dao.getRsvp(rsvp.getId());
        assertEquals(rsvp, fromDao);
    }

}