package com.bibliotek.Repositories;


import com.bibliotek.Models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {

    List<Book> findBookByYear(String year);
    List<Book> findBookByAuthor(String author);
    List<Book> findBookByTitle(String title);
    Book findByAuthorAndTitle(String title, String author);
    List<Book> findBookByCategory_Name (String category);
    boolean existsByAuthorAndTitle(String author, String title);
    List<Book> findByHaveIReadIt (boolean haveIReadIt);
    void deleteById(Long id);
    List<Book> findAll();


}
