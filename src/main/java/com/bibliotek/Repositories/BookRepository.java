package com.bibliotek.Repositories;


import com.bibliotek.Models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findBookByYear(String year);
    List<Book> findBookByAuthor(String author);
    Book findBookByTitle(String title);
    Book findByAuthorAndTitle(String title, String author);
    List<Book> findByCategory(String category);
    boolean existsByAuthorAndTitle(String author, String title);
    List<Book> findByHaveIReadIt (boolean haveIReadIt);
    List<Book> findByHaveIReadIt (boolean haveIReadIt);

}
