package com.astindg.library.dao;

import com.astindg.library.models.Book;
import com.astindg.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private static final String SAVE_PERSON_SQL = "INSERT INTO Person(name, year_of_birth) VALUES(?, ?)";
    private static final String GET_ALL_PEOPLE_SQL = "SELECT * FROM Person";
    private static final String GET_PERSON_BY_ID = "SELECT * FROM Person WHERE person_id=?";
    private static final String UPDATE_PERSON_SQL = "UPDATE Person SET name=?, year_of_birth=? WHERE person_id=?;";
    private static final String RELEASE_ALL_BOOK_FROM_PERSON = "UPDATE Book SET person=null WHERE person=?";
    private static final String DELETE_PERSON_SQL = "DELETE FROM Person WHERE person_id=?";
    private static final String GET_ALL_BOOKS_BY_ID = "SELECT * FROM Book where person=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query(GET_ALL_PEOPLE_SQL, new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query(GET_PERSON_BY_ID, new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update(SAVE_PERSON_SQL,
                person.getName(),
                person.getYearOfBirth()
        );
    }

    public void update(int id, Person person) {
        jdbcTemplate.update(UPDATE_PERSON_SQL,
                person.getName(),
                person.getYearOfBirth(),
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update(RELEASE_ALL_BOOK_FROM_PERSON, id);
        jdbcTemplate.update(DELETE_PERSON_SQL, id);
    }

    public List<Book> getBooks(int id) {
        return jdbcTemplate.query(GET_ALL_BOOKS_BY_ID, new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
