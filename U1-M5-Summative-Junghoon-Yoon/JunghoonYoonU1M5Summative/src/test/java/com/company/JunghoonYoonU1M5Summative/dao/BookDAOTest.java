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

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit Tests for Book DAO and Impl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDAOTest {

    // Bean Injections
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
    public void addGetDeleteBook() {
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


        //Sample Book
        Book book = new Book();
        book.setIsbn("xd1234");
        book.setPublish_date(LocalDate.of(1993, 8, 5));
        book.setAuthor_id(author.getId());
        book.setTitle("Redwall");
        book.setPublisher_id(publisher.getId());
        book.setPrice(15.50);

        book = bookDao.addBook(book);

        Book book1 = bookDao.getBook(book.getId());
        System.out.println(book1);

        assertEquals(book1, book);

        bookDao.deleteBook(book.getId());

        book1 = bookDao.getBook(book.getId());

        assertNull(book1);

    }

    @Test
    public void getAllBooks() {
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


        //Sample Book
        Book book = new Book();
        book.setIsbn("xd1234");
        book.setPublish_date(LocalDate.of(1993, 8, 5));
        book.setAuthor_id(author.getId());
        book.setTitle("Redwall");
        book.setPublisher_id(publisher.getId());
        book.setPrice(15.50);

        book = bookDao.addBook(book);

        book = new Book();
        book.setIsbn("some0001");
        book.setPublish_date(LocalDate.of(2005, 1, 25));
        book.setAuthor_id(author.getId());
        book.setTitle("Camelot");
        book.setPublisher_id(publisher.getId());
        book.setPrice(20.00);

        bookDao.addBook(book);

        List<Book> bList = bookDao.getAllBooks();

        assertEquals(bList.size(), 2);
    }

    @Test
    public void updateBook() {
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


        //Sample Book
        Book book = new Book();
        book.setIsbn("xd1234");
        book.setPublish_date(LocalDate.of(1993, 8, 5));
        book.setAuthor_id(author.getId());
        book.setTitle("Redwall");
        book.setPublisher_id(publisher.getId());
        book.setPrice(15.50);

        book = bookDao.addBook(book);

        book.setIsbn("UPDATE0001");
        book.setPublish_date(LocalDate.of(2010, 5, 25));
        book.setAuthor_id(author.getId());
        book.setTitle("UPDATEBOOK");
        book.setPublisher_id(publisher.getId());
        book.setPrice(20.00);

        bookDao.updateBook(book);

        Book book2 = bookDao.getBook(book.getId());

        assertEquals(book2, book);
    }

    @Test
    public void getBookByAuthor() {
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

        Author author2 = new Author();
        author2.setFirst_name("Robert");
        author2.setLast_name("Heinlein");
        author2.setStreet("123 Other Street");
        author2.setCity("Atlanta");
        author2.setState("GA");
        author2.setPostal_code("30033");
        author2.setPhone("000-111-2222");
        author2.setEmail("otherEmail@gmail.com");

        author2 = authorDao.addAuthor(author2);

        Author author3 = new Author();
        author3.setFirst_name("George");
        author3.setLast_name("Martin");
        author3.setStreet("999 Other Street");
        author3.setCity("San Francisco");
        author3.setState("CA");
        author3.setPostal_code("40019");
        author3.setPhone("000-111-2222");
        author3.setEmail("got@naver.com");

        author3 = authorDao.addAuthor(author3);

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


        //Sample Book
        Book book = new Book();
        book.setIsbn("xd1234");
        book.setPublish_date(LocalDate.of(1993, 8, 5));
        book.setAuthor_id(author.getId());
        book.setTitle("Redwall");
        book.setPublisher_id(publisher.getId());
        book.setPrice(15.50);

        book = bookDao.addBook(book);

        book = new Book();
        book.setIsbn("some0001");
        book.setPublish_date(LocalDate.of(2010, 5, 15));
        book.setAuthor_id(author2.getId());
        book.setTitle("Camelot");
        book.setPublisher_id(publisher.getId());
        book.setPrice(20.00);

        bookDao.addBook(book);

        book = new Book();
        book.setIsbn("isbn12345");
        book.setPublish_date(LocalDate.of(1990, 12, 31));
        book.setAuthor_id(author3.getId());
        book.setTitle("Game of Thrones");
        book.setPublisher_id(publisher.getId());
        book.setPrice(99.99);

        bookDao.addBook(book);

        List<Book> bList = bookDao.getBookByAuthor(author.getId());
        assertEquals(1, bList.size());

        List<Book> bList2 = bookDao.getBookByAuthor(author2.getId());
        assertEquals(1, bList2.size());

        List<Book> bList3 = bookDao.getBookByAuthor(author3.getId());
        assertEquals(1, bList3.size());
    }

}