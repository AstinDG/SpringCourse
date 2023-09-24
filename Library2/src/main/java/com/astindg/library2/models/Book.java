package com.astindg.library2.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    @Column(name = "name")
    @Size(min = 1, max = 250, message = "Name should be between 1 and 250 characters")
    private String name;

    @Column(name = "author")
    @Size(min = 1, max = 250, message = "Author name should be between 1 and 250 characters")
    private String author;

    @Column(name = "year_release")
    @Min(value = 0, message = "Year of release should be greater than 0")
    @Max(value = 2500, message = "Year of release should be less than 2500")
    private int year_release;

    @ManyToOne
    @JoinColumn(name = "person", referencedColumnName = "person_id")
    private Person person;

    public Book() {
    }

    public Book(String name, String author, int year_release) {
        this.name = name;
        this.author = author;
        this.year_release = year_release;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear_release() {
        return year_release;
    }

    public void setYear_release(int year_release) {
        this.year_release = year_release;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearRelease=" + year_release +
                '}';
    }
}

