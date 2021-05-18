package com.bibliotek.Repository;

import com.bibliotek.Models.Book;
import com.bibliotek.Models.Category;
import com.bibliotek.Repositories.BookRepository;
import com.bibliotek.Repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jacaranda Perez
 * Date: 2021-05-18
 * Project: Bibliotek
 */
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;


    @Test
    void findBookByTitle(){
        Book book1 = new Book();
        book1.setAuthor("Jens Lapidus");
        book1.setTitle("Snabba Cash");
        book1.setPublisher("Pocket");
        book1.setYear("2008");

        bookRepository.save(book1);

        Book actualBook = bookRepository.findBookByTitle("Snabba Cash");

        assertEquals(book1, book1);

    }
}
