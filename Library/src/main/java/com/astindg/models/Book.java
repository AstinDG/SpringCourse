package com.astindg.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Book {

    private int book_id;

    @Size(min = 1, max = 250, message = "Name should be between 1 and 250 characters")
    private String name;
    @Size(min = 1, max = 250, message = "Author name should be between 1 and 250 characters")
    private String author;
    @Min(value = 0, message = "Year of release should be greater than 0")
    @Max(value = 2500, message = "Year of release should be less than 2500")
    private int year_release;

    public Book() {
    }

    public Book(int book_id, String name, String author, int year_release) {
        this.book_id = book_id;
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
}

