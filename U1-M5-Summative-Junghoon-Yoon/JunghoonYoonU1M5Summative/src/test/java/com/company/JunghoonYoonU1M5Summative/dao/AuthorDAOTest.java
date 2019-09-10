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
 * Unit Tests For Author DAO and Impl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorDAOTest {

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
    public void getAddDeleteAuthor() {

        //Sample Author
        Author author = new Author();
        author.setFirst_name("Brian");
        author.setLast_name("Jacques");
        author.setStreet("555 Street Name");
        author.setCity("Centennial");
        author.setState("CO");
        author.setPostal_code("30033");
        author.setPhone("111-222-3333");
        author.setEmail("someEmail@email.com");

        author = authorDao.addAuthor(author);

        Author author2 = authorDao.getAuthor(author.getId());

        //Testing adding and getting method
        assertEquals(author2, author);

        authorDao.deleteAuthor(author.getId());

        author2 = authorDao.getAuthor(author.getId());

        //Testing delete method
        assertNull(author2);

    }

    @Test
    public void getAllAuthors() {
        //Sample Author
        Author author = new Author();
        author.setFirst_name("Brian");
        author.setLast_name("Jacques");
        author.setStreet("555 Street Name");
        author.setCity("Centennial");
        author.setState("CO");
        author.setPostal_code("80015");
        author.setPhone("111-222-3333");
        author.setEmail("someEmail@email.com");

        author = authorDao.addAuthor(author);

        //Second sample author
        author = new Author();
        author.setFirst_name("Robert");
        author.setLast_name("Heinlein");
        author.setStreet("123 Other Street");
        author.setCity("Atlanta");
        author.setState("GA");
        author.setPostal_code("30033");
        author.setPhone("000-111-2222");
        author.setEmail("otherEmail@gmail.com");

        authorDao.addAuthor(author);

        List<Author> aList = authorDao.getAllAuthors();

        //Testing Get All method
        assertEquals(aList.size(), 2);
    }

    @Test
    public void updateAuthor() {
        //Sample Author
        Author author = new Author();
        author.setFirst_name("Brian");
        author.setLast_name("Jacques");
        author.setStreet("555 Street Name");
        author.setCity("Centennial");
        author.setState("CO");
        author.setPostal_code("80015");
        author.setPhone("111-222-3333");
        author.setEmail("someEmail@email.com");

        author = authorDao.addAuthor(author);

        //Update to original author
        author.setFirst_name("New Name");
        author.setLast_name("New Last Name");
        author.setStreet("New Street");
        author.setCity("New City");
        author.setStreet("New State");
        author.setPostal_code("New Code");
        author.setPhone("New Phone");
        author.setEmail("newEmail@NEW.com");

        authorDao.updateAuthor(author);

        Author author2 = authorDao.getAuthor(author.getId());

        //Testing Update Method
        assertEquals(author2, author);
    }

}