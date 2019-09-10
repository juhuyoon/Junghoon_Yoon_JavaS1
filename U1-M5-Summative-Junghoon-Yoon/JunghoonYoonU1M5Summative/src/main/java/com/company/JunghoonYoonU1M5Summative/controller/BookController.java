package com.company.JunghoonYoonU1M5Summative.controller;

import com.company.JunghoonYoonU1M5Summative.dao.AuthorDAO;
import com.company.JunghoonYoonU1M5Summative.dao.BookDAO;
import com.company.JunghoonYoonU1M5Summative.dao.PublisherDAO;
import com.company.JunghoonYoonU1M5Summative.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private PublisherDAO publisherDAO;

    @GetMapping(value = "/book")
    @ResponseStatus(value =  HttpStatus.OK)
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @GetMapping(value="/book/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Book> getBookByAuthor(@PathVariable(name = "id") int authorId) {
        return bookDAO.getBookByAuthor(authorId);

    }

    @PostMapping(value = "/book")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Book addBook(@RequestBody @Valid Book book) {
        bookDAO.addBook(book);
        return book;
    }

    @PutMapping(value = "/book")
    @ResponseStatus(value = HttpStatus.OK)
    public String updateBook(@RequestBody Book book){
        bookDAO.updateBook(book);
        return ("Book updated!");
    }

    @DeleteMapping(value = "/book/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteBook(@PathVariable(name="id") int id) {
        bookDAO.deleteBook(id);
        return("Publisher deleted!");
    }


}
