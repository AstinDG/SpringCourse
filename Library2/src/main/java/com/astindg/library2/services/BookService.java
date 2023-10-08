package com.astindg.library2.services;

import com.astindg.library2.models.Book;
import com.astindg.library2.models.Person;
import com.astindg.library2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findAll(int page, int itemsPerPage, String sortField, String sortDirection) {
        Sort sort = Sort.by((sortDirection.equals("ASC")) ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        return bookRepository.findAll(PageRequest.of(page, itemsPerPage, sort));
    }

    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(Integer id, Book updatedBook) {
        updatedBook.setBookId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void setBook(Person person, Integer bookId) {
        bookRepository.getOne(bookId).setPerson(person);
    }

    @Transactional
    public void releaseBook(Integer bookId) {
        bookRepository.getOne(bookId).setPerson(null);
    }

}
