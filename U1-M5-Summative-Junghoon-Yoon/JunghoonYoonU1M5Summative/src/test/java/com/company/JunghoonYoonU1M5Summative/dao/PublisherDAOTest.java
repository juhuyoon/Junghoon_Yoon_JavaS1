package com.company.JunghoonYoonU1M5Summative.dao;

import com.company.JunghoonYoonU1M5Summative.model.Author;
import com.company.JunghoonYoonU1M5Summative.model.Book;
import com.company.JunghoonYoonU1M5Summative.model.Publisher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit Tests for Publisher DAO and Impl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherDAOTest {

    //Bean Injections
    @Autowired
    private BookDAO bookDao;
    @Autowired
    private AuthorDAO authorDao;
    @Autowired
    private PublisherDAO publisherDAO;

    @Before
    public void setUp() throws Exception {
        //Cleaning up Test DB
        List<Book> bList = bookDao.getAllBooks();
        bList.stream().forEach(b -> bookDao.deleteBook(b.getId()));

        List<Author> aList = authorDao.getAllAuthors();
        aList.stream().forEach(a -> authorDao.deleteAuthor(a.getId()));

        List<Publisher> pList = publisherDAO.getAllPublishers();
        pList.stream().forEach(p -> publisherDAO.deletePublisher(p.getId()));
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void addGetDeletePublisher() {

        //Sample Publisher
        Publisher publisher = new Publisher();
        publisher.setName("Barnes");
        publisher.setStreet("123 Barnes St");
        publisher.setCity("Centennial");
        publisher.setState("CO");
        publisher.setPostal_code("40044");
        publisher.setPhone("999-999-9999");
        publisher.setEmail("email@barnes.com");

        System.out.println(publisher);


        publisher = publisherDAO.addPublisher(publisher);

        Publisher publisher2 = publisherDAO.getPublisher(publisher.getId());

        //Testing adding and getting method
        assertEquals(publisher2, publisher);

        publisherDAO.deletePublisher(publisher.getId());

        publisher2 = publisherDAO.getPublisher(publisher.getId());

        //Testing delete method
        assertNull(publisher2);
    }

    @Test
    public void getAllPublishers() {
        //Sample Publisher
        Publisher publisher = new Publisher();
        publisher.setName("Barnes");
        publisher.setStreet("Barnes St");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostal_code("11156");
        publisher.setPhone("555-555-5555");
        publisher.setEmail("Barnes@Nobles.com");

        publisher = publisherDAO.addPublisher(publisher);

        publisher = new Publisher();
        publisher.setName("Nobles");
        publisher.setStreet("Nobles Ave.");
        publisher.setCity("Seattle");
        publisher.setState("WA");
        publisher.setPostal_code("40404");
        publisher.setPhone("777-777-0091");
        publisher.setEmail("nobles@barnes.com");

        publisherDAO.addPublisher(publisher);

        List<Publisher> pList = publisherDAO.getAllPublishers();

        //Testing get all method
        assertEquals(pList.size(), 2);

    }

    @Test
    public void updatePublisher() {
        //Sample Publisher
        Publisher publisher = new Publisher();
        publisher.setName("Barnes");
        publisher.setStreet("Barnes St");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostal_code("11156");
        publisher.setPhone("555-555-5555");
        publisher.setEmail("Barnes@Nobles.com");

        publisher = publisherDAO.addPublisher(publisher);

        publisher.setName("Nobles");
        publisher.setStreet("Nobles Ave.");
        publisher.setCity("Seattle");
        publisher.setStreet("WA");
        publisher.setPostal_code("40404");
        publisher.setPhone("777-777-0091");
        publisher.setEmail("nobles@barnes.com");

        publisherDAO.updatePublisher(publisher);
        Publisher publisher2 = publisherDAO.getPublisher(publisher.getId());

        //Testing Update Method
        assertEquals(publisher2, publisher);
    }
}