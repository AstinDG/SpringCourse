package com.astindg.dao;

import com.astindg.models.Book;
import com.astindg.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private static final String SAVE_BOOK_SQL = "INSERT INTO Book(name, author, year_release) VALUES(?, ?, ?)";
    private static final String GET_ALL_BOOKS_SQL = "SELECT * FROM Book;";
    private static final String GET_BOOK_BY_ID = "SELECT * FROM Book WHERE book_id=?";
    private static final String UPDATE_BOOK_SQL = "UPDATE Book SET name=?, author=?, year_release=? WHERE book_id=?";
    private static final String DELETE_PERSON_SQL = "DELETE FROM Book WHERE book_id=?";
    private static final String SET_BOOK_FOR_PERSON = "UPDATE Book SET person=? WHERE book_id=?";
    private static final String GET_PERSON_BY_BOOK_ID = "SELECT person_id, Person.name, year_of_birth FROM Person JOIN Book ON Person.person_id=Book.person AND book_id=?";
    private static final String RELEASE_BOOK_FROM_PERSON = "UPDATE Book SET person=null WHERE book_id=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query(GET_ALL_BOOKS_SQL, new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query(GET_BOOK_BY_ID, new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update(SAVE_BOOK_SQL,
                book.getName(), book.getAuthor(), book.getYear_release());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update(UPDATE_BOOK_SQL,
                book.getName(),
                book.getAuthor(),
                book.getYear_release(),
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_PERSON_SQL, id);
    }

    public Person getPersonByBookId(int id) {
        return jdbcTemplate.query(GET_PERSON_BY_BOOK_ID, new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void setBook(Person person, int bookId) {
        jdbcTemplate.update(SET_BOOK_FOR_PERSON,
                person.getPerson_id(),
                bookId
        );
    }

    public void releaseBook(int bookId) {
        jdbcTemplate.update(RELEASE_BOOK_FROM_PERSON, bookId);
    }
}

