package com.company.JunghoonYoonU1M5Summative.controller;

import com.company.JunghoonYoonU1M5Summative.dao.AuthorDAO;
import com.company.JunghoonYoonU1M5Summative.dao.BookDAO;
import com.company.JunghoonYoonU1M5Summative.dao.PublisherDAO;
import com.company.JunghoonYoonU1M5Summative.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PublisherController {

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private PublisherDAO publisherDAO;

    @GetMapping(value="/publisher")
    @ResponseStatus(value =  HttpStatus.OK)
    public List<Publisher> getAllPublishers() {
        return publisherDAO.getAllPublishers();
    }

    @PostMapping(value = "/publisher")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Publisher addPublisher(@RequestBody @Valid Publisher publisher) {
        publisherDAO.addPublisher(publisher);
        return publisher;
    }

    @PutMapping(value = "/publisher")
    @ResponseStatus(value = HttpStatus.OK)
    public String updatePublisher(@RequestBody Publisher publisher){
        publisherDAO.updatePublisher(publisher);
        return ("Publisher updated!");
    }

    @DeleteMapping(value = "/publisher/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deletePublisher(@PathVariable(name="id") int id) {
        publisherDAO.deletePublisher(id);
        return("Publisher deleted!");
    }
}

