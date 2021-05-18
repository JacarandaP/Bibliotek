package com.bibliotek.Repositories;


import com.bibliotek.Models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findBookByYear(String year);
    List<Book> findBookByAuthor(String author);
    List<Book> findBookByTitle(String title);
    List<Book> findByAuthorAndTitle(String title, String author);
    List<Book> findByCategory(String category);

}
