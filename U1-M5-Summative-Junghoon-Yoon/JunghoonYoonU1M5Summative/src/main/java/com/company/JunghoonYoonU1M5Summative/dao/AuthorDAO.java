package com.company.JunghoonYoonU1M5Summative.dao;

import com.company.JunghoonYoonU1M5Summative.model.Author;

import java.util.List;

// DAO For implementations
public interface AuthorDAO {

    /* General CRUD methods*/
    Author getAuthor(int id);

    List<Author> getAllAuthors();

    Author addAuthor(Author author);

    void updateAuthor(Author author);

    void deleteAuthor(int id);

}
