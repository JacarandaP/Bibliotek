package com.bibliotek.Repository;

import com.bibliotek.Models.Book;
import com.bibliotek.Models.Category;
import com.bibliotek.Repositories.BookRepository;
import com.bibliotek.Repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jacaranda Perez
 * Date: 2021-05-18
 * Project: Bibliotek
 */
@DataMongoTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;


    @Test
    void findBookByTitleTest(){
        Book book1 = new Book();
        book1.setId(1L);
        book1.setAuthor("Jens Lapidus");
        book1.setTitle("Snabba Cash");
        book1.setPublisher("Pocket");
        book1.setYear("2008");

        bookRepository.save(book1);

        List<Book> actualBook = bookRepository.findBookByTitle("Snabba Cash");

        assertEquals(Arrays.asList(book1), actualBook);

    }
    @Test
    void findBookByhaveIReadIt(){
        Book book1 = new Book();
        book1.setId(1L);
        book1.setAuthor("Jens Lapidus");
        book1.setTitle("Snabba Cash");
        book1.setPublisher("Pocket");
        book1.setYear("2008");
        book1.setHaveIReadIt(true);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setAuthor("Inger Christensen");
        book2.setTitle("Det");
        book2.setPublisher("Modernista");
        book2.setYear("2009");
        book2.setHaveIReadIt(true);

        Book book3 = new Book();
        book3.setId(3L);
        book3.setAuthor("Albert Camus");
        book3.setTitle("El extranjero");
        book3.setPublisher("Alianza");
        book3.setYear("2011");
        book3.setHaveIReadIt(false);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        List<Book> actualBooks = bookRepository.findByHaveIReadIt(true);

        assertEquals(Arrays.asList(book1, book2), actualBooks);
        assertEquals(book1.getTitle(), actualBooks.get(0).getTitle());
        assertEquals(book2.getTitle(), actualBooks.get(1).getTitle());

    }

    @Test
    void findBooksToRead(){
        Book book1 = new Book();
        book1.setId(1L);
        book1.setAuthor("Jens Lapidus");
        book1.setTitle("Snabba Cash");
        book1.setPublisher("Pocket");
        book1.setYear("2008");
        book1.setHaveIReadIt(true);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setAuthor("Inger Christensen");
        book2.setTitle("Det");
        book2.setPublisher("Modernista");
        book2.setYear("2009");
        book2.setHaveIReadIt(false);

        Book book3 = new Book();
        book3.setId(3L);
        book3.setAuthor("Albert Camus");
        book3.setTitle("El extranjero");
        book3.setPublisher("Alianza");
        book3.setYear("2011");
        book3.setHaveIReadIt(false);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        List<Book> actualBooks = bookRepository.findByHaveIReadIt(false);

        assertEquals(Arrays.asList(book2, book3), actualBooks);
        assertEquals(book2.getTitle(), actualBooks.get(0).getTitle());
        assertEquals(book3.getTitle(), actualBooks.get(1).getTitle());

    }

}
