package com.astindg.library2.services;

import com.astindg.library2.models.Book;
import com.astindg.library2.models.Person;
import com.astindg.library2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(Integer id){
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void update(Integer id, Book updatedBook){
        updatedBook.setBook_id(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(Integer id){
        bookRepository.deleteById(id);
    }

    @Transactional
    public void setBook(Person person, Integer bookId){
        bookRepository.getOne(bookId).setPerson(person);
    }

    @Transactional
    public void releaseBook(Integer bookId){
        bookRepository.getOne(bookId).setPerson(null);
    }
}
