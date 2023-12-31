package com.astindg.library2.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name = "name")
    @Size(min = 1, max = 250, message = "Name should be between 1 and 250 characters")
    private String name;

    @Column(name = "author")
    @Size(min = 1, max = 250, message = "Author name should be between 1 and 250 characters")
    private String author;

    @Column(name = "year_release")
    @Min(value = 0, message = "Year of release should be greater than 0")
    @Max(value = 2500, message = "Year of release should be less than 2500")
    private int yearRelease;

    @ManyToOne
    @JoinColumn(name = "person", referencedColumnName = "person_id")
    private Person person;

    @Column(name = "time_of_assigning")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfAssigning;

    public Book() {
    }

    public Book(String name, String author, int year_release) {
        this.name = name;
        this.author = author;
        this.yearRelease = year_release;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearRelease() {
        return yearRelease;
    }

    public void setYearRelease(int yearRelease) {
        this.yearRelease = yearRelease;
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

    public Date getDateOfAssigning() {
        return dateOfAssigning;
    }

    public void setDateOfAssigning(Date dateOfAssigning) {
        this.dateOfAssigning = dateOfAssigning;
    }

    public void setPerson(Person person) {
        this.dateOfAssigning = new Date();
        this.person = person;
    }

    public boolean hasExpired(int days) {
        long diff = new Date().getTime() - this.dateOfAssigning.getTime();
        long daysDiff = TimeUnit.MILLISECONDS.toDays(diff);
        return daysDiff > days;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + bookId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearRelease=" + yearRelease +
                '}';
    }
}

