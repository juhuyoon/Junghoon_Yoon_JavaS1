package com.trilogyed.rsp.controller;

import com.trilogyed.rsp.dao.RsvpDao;
import com.trilogyed.rsp.model.Rsvp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rsvps")
public class RsvpController {
    @Autowired
    RsvpDao dao;

    public RsvpController(RsvpDao dao) {
        this.dao = dao;
    }

    @CachePut(key = "#result.getId()")
    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Rsvp createRsvp(@RequestBody Rsvp rsvp) {
        System.out.println("CREATING RSVP");
        return dao.addRsvp(rsvp);
    }

    @Cacheable
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rsvp getRsvp(@PathVariable int id){
        System.out.println("GETTING RSVP ID = " + id);
        return dao.getRsvp(id);
    }

    @GetMapping(value="")
    @ResponseStatus(HttpStatus.OK)
    public List<Rsvp> getAllRsvps() {
        System.out.println("GETTING ALL RSVPS");
        return dao.getAllRsvps();
    }

    @CacheEvict(key = "#rsvp.getId()")
    @PutMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public void updateRsvp(@RequestBody Rsvp rsvp) {
        System.out.println("UPDATING RSVP ID = " + rsvp.getId());
        dao.updateRsvp(rsvp);
    }

    @CacheEvict
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRsvp(@PathVariable int id) {
        System.out.println("DELETING RSVP ID = " + id);
        dao.deleteRsvp(id);
    }
}
