package com.company.JunghoonYoonU1M5Summative.dao;

import com.company.JunghoonYoonU1M5Summative.model.Book;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDAO {

    //Prepared Statements
    private static final String ADD_BOOK_SQL =
            "INSERT INTO book (isbn, publish_date, author_id, title, publisher_id, price) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String GET_BOOK_SQL =
            "SELECT * FROM book WHERE book_id = ?";

    private static final String GET_ALL_BOOKS_SQL =
            "SELECT * FROM book";

    private static final String UPDATE_BOOK_SQL =
            "UPDATE book SET isbn = ?, publish_date = ?, author_id = ?, title = ?, publisher_id = ?, price = ? WHERE book_id = ?";

    private static final String DELETE_BOOK_SQL =
            "DELETE FROM book WHERE book_id = ?";

    private static final String FIND_BOOK_BY_AUTHOR =
            "SELECT * FROM book WHERE author_id = ?";

    private JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves a book by the ID
     * @param id
     * @return
     */
    @Override
    public Book getBook(int id) {
        try {
            return jdbcTemplate.queryForObject(GET_BOOK_SQL, this::mapRowToBook, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves all books from the DB
     * @return
     */
    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(GET_ALL_BOOKS_SQL, this::mapRowToBook);
    }

    /**
     * Adds a book to the DB.
     * @param book
     * @return
     */
    @Override
    public Book addBook(Book book) {
        jdbcTemplate.update(ADD_BOOK_SQL,
                book.getIsbn(),
                book.getPublish_date(),
                book.getAuthor_id(),
                book.getTitle(),
                book.getPublisher_id(),
                book.getPrice());
        int id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);
        book.setId(id);

        return book;
    }

    /**
     * Updates one book by the ID to the database
     * @param book
     */
    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update(UPDATE_BOOK_SQL,
                book.getIsbn(),
                book.getPublish_date(),
                book.getAuthor_id(),
                book.getTitle(),
                book.getPublisher_id(),
                book.getPrice(),
                book.getId());
    }

    /**
     * Deletes a book by the ID from the DB
     * @param id
     */
    @Override
    public void deleteBook(int id) {
        jdbcTemplate.update(DELETE_BOOK_SQL, id);
    }

    /**
     * Searches for a book by the author ID
     * @param author_id
     * @return
     */
    @Override
    public List<Book> getBookByAuthor(int author_id) {
        return jdbcTemplate.query(FIND_BOOK_BY_AUTHOR,
                this::mapRowToBook, author_id);
    }

    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublish_date(rs.getDate("publish_date").toLocalDate());
        book.setAuthor_id(rs.getInt("author_id"));
        book.setTitle(rs.getString("title"));
        book.setPublisher_id(rs.getInt("publisher_id"));
        book.setPrice(rs.getBigDecimal("price"));

        return book;
    }
}
