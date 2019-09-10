package com.company.JunghoonYoonU1M5Summative.controller;

import com.company.JunghoonYoonU1M5Summative.dao.AuthorDAO;
import com.company.JunghoonYoonU1M5Summative.dao.BookDAO;
import com.company.JunghoonYoonU1M5Summative.dao.PublisherDAO;
import com.company.JunghoonYoonU1M5Summative.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private PublisherDAO publisherDAO;

    @GetMapping(value="/author")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Author> getAllAuthors() {
        return authorDAO.getAllAuthors();
    }

    @PostMapping(value = "/author")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Author addAuthor(@RequestBody @Valid Author author) {
        authorDAO.addAuthor(author);
        return author;
    }

    @PutMapping(value = "/author")
    @ResponseStatus(value = HttpStatus.OK)
    public String updateAuthor(@RequestBody Author author){
        authorDAO.updateAuthor(author);
        return ("Author updated!");
    }

    @DeleteMapping(value = "/author/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteAuthor(@PathVariable(name = "id") int id){
        authorDAO.deleteAuthor(id);
        return("Author Deleted!");
    }
}
