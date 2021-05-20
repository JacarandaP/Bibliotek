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
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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
        Category mockCat = new Category();
        mockCat.setName("poesi");
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
    void addBook_fail() {
        Book mockBook = new Book();
        mockBook.setAuthor("Inger Christensen");
        mockBook.setTitle("Det");
        mockBook.setPublisher("Modernista");
        mockBook.setYear("2009");

        when(mockRepository.existsByAuthorAndTitle(anyString(), anyString())).thenReturn(true);

        assertThrows(ResponseStatusException.class, ()-> bookService.addBook(mockBook));

        verify(mockRepository, times(0)).save(any());
        verify(mockRepository).existsByAuthorAndTitle(anyString(), anyString());


    }
}