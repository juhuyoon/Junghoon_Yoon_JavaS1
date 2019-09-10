package com.company.JunghoonYoonU1M5Summative.dao;

import com.company.JunghoonYoonU1M5Summative.model.Publisher;

import java.util.List;

// DAO For implementations
public interface PublisherDAO {

    /* General CRUD methods*/
    Publisher getPublisher(int id);

    List<Publisher> getAllPublishers();

    Publisher addPublisher(Publisher publisher);

    void updatePublisher(Publisher publisher);

    void deletePublisher(int id);


}
