package com.astindg.models;

import javax.validation.constraints.*;

public class Person {
    private static final String REGEXP_NAME = "[A-Z]\\w+, [A-Z]\\w+";

    private int person_id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Pattern(regexp = REGEXP_NAME, message = "Mame should be in format \"Firstname, Surname\"")
    private String name;

    @Min(value = 1900, message = "Year of birth should be greater than 1900")
    @Max(value = 2200, message = "Year of birth should be less than 2200")
    private int yearOfBirth;

    public Person() {

    }

    public Person(int id, String name, int age) {
        this.person_id = id;
        this.name = name;
        this.yearOfBirth = age;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
