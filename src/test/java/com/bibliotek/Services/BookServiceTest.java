package com.bibliotek.Services;

import com.bibliotek.Models.Book;
import com.bibliotek.Models.Category;
import com.bibliotek.Repositories.BookRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2021-05-20
 * Project: Bibliotek
 * Copyright: MIT
 */
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    BookService bookService;

    @Mock
    BookRepository mockRepository;

    @BeforeEach
    public void init(){
        bookService = new BookService(mockRepository);
    }

    @Test
    void addBook_success() {
        Book mockBook = new Book();
        mockBook.setAuthor("Inger Christensen");
        mockBook.setTitle("Det");
        mockBook.setPublisher("Modernista");
        mockBook.setYear("2009");

        when(mockRepository.save(mockBook)).thenReturn(mockBook);

        Book actual = bookService.addBook(mockBook);

        assertEquals(mockBook.getId(), actual.getId());
        assertEquals(mockBook.getAuthor(), actual.getAuthor());
        verify(mockRepository).save(any());
        verify(mockRepository).existsByAuthorAndTitle(anyString(), anyString());
    }


    @Test
    void getBooks(){
        Book mockBook1 = new Book();
        mockBook1.setAuthor("Inger Christensen");
        mockBook1.setTitle("Det");
        mockBook1.setPublisher("Modernista");
        mockBook1.setYear("2009");

        Book mockBook2 = new Book();
        mockBook2.setAuthor("Jens Lapidus");
        mockBook2.setTitle("Snabba Cash");
        mockBook2.setPublisher("Pocket");
        mockBook2.setYear("2008");

        when(mockRepository.findAll()).thenReturn(Arrays.asList(mockBook1, mockBook2));

        List<Book> actual = StreamSupport.stream(bookService.getBooks().spliterator(), false).collect(Collectors.toList());

        assertEquals(2, actual.size());
        assertEquals(mockBook1.getAuthor(), actual.get(0).getAuthor());
        assertEquals(mockBook2.getTitle(), actual.get(1).getTitle());

        verify(mockRepository).findAll();
    }

    @Test
    void getBooksByAuthor() {
        Book mockBook = new Book();
        String author = "Inger Christensen";
        mockBook.setAuthor(author);
        mockBook.setTitle("Det");
        mockBook.setPublisher("Modernista");
        mockBook.setYear("2009");

        when(mockRepository.findBookByAuthor(author)).thenReturn(Arrays.asList(mockBook1));

        List<Book> actual = bookService.getBooksByAuthor(author);

        assertEquals(author, actual.get(0).getAuthor());
        verify(mockRepository).findBookByAuthor(anyString());

    }

    @Test
    void getBooksByCategory() {
        Book mockBook1 = new Book();
        String author = "Inger Christensen";
        mockBook1.setAuthor(author);
        mockBook1.setTitle("Det");
        mockBook1.setPublisher("Modernista");
        mockBook1.setYear("2009");
    }

    @Test
    void getBooksByYear() {
    }
}