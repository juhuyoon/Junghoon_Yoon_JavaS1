package com.company.JunghoonYoonU1M5Summative.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    //Declaration of Variables
    private Integer id;
    private String isbn;
    private LocalDate publish_date;
    private Integer author_id;
    private String title;
    private Integer publisher_id;
    private Double price;

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(LocalDate publish_date) {
        this.publish_date = publish_date;
    }

    public Integer getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(Integer publisher_id) {
        this.publisher_id = publisher_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id) &&
                isbn.equals(book.isbn) &&
                publish_date.equals(book.publish_date) &&
                author_id.equals(book.author_id) &&
                title.equals(book.title) &&
                publisher_id.equals(book.publisher_id) &&
                price.equals(book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, publish_date, author_id, title, publisher_id, price);
    }
}
