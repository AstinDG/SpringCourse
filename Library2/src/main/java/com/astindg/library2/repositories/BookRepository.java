package com.astindg.library2.repositories;

import com.astindg.library2.models.Book;
import com.astindg.library2.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
