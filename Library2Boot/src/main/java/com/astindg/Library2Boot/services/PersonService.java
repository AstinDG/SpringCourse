package com.astindg.Library2Boot.services;

import com.astindg.Library2Boot.models.Book;
import com.astindg.Library2Boot.models.Person;
import com.astindg.Library2Boot.repositories.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person findById(Integer id){
        return personRepository.findById(id).orElse(null);
    }

    public List<Book> getBooksByPersonId(Integer id){
        Optional<Person> person = personRepository.findById(id);
        if(person.isPresent()){
            Hibernate.initialize(person.get().getBooks());
            return person.get().getBooks();
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }

    @Transactional
    public void update(Integer id, Person person){
        person.setPersonId(id);
        personRepository.save(person);
    }

    @Transactional
    public void delete(Integer id){
        personRepository.deleteById(id);
    }

    @Transactional
    public void delete(Person person){
        personRepository.delete(person);
    }
}
