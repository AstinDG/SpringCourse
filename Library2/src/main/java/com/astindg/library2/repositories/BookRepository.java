package com.astindg.library2.repositories;

import com.astindg.library2.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT b FROM Book as b WHERE " +
            "b.name like %:searchPrompt% or " +
            "b.author like %:searchPrompt%")
    List<Book> searchBook(@Param("searchPrompt") String searchPrompt);
}
