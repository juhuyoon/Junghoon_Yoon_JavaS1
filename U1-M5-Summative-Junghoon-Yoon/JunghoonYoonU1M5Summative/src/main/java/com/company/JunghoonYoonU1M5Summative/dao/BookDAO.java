package com.company.JunghoonYoonU1M5Summative.dao;

import com.company.JunghoonYoonU1M5Summative.model.Book;

import java.util.List;

// DAO For implementations
public interface BookDAO {

    /* General CRUD methods*/
    Book getBook(int id);

    List<Book> getAllBooks();

    Book addBook(Book book);

    void updateBook(Book book);

    void deleteBook(int id);

    List<Book> getBookByAuthor(int author_id);

}
