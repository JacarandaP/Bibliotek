package com.bibliotek.Services;

import com.bibliotek.Models.Book;
import com.bibliotek.Models.Category;
import com.bibliotek.Repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


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
        Book mockBook = new Book();
        mockBook.setId(1L);
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
        mockBook1.setId(1L);
        mockBook1.setAuthor("Inger Christensen");
        mockBook1.setTitle("Det");
        mockBook1.setPublisher("Modernista");
        mockBook1.setYear("2009");

        Book mockBook2 = new Book();
        mockBook2.setId(2L);
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
        mockBook.setId(1L);
        String author = "Inger Christensen";
        mockBook.setAuthor(author);

        when(mockRepository.findBookByAuthor(author)).thenReturn(Arrays.asList(mockBook));

        List<Book> actual = bookService.getBooksByAuthor(author);

        assertEquals(author, actual.get(0).getAuthor());
        verify(mockRepository).findBookByAuthor(anyString());


    }

    @Test
    void getBooksByAuthorAndTitle(){
        Book mockBook = new Book();
        mockBook.setId(1L);
        String author = "Inger Christensen";
        String title = "Det";
        mockBook.setAuthor(author);
        mockBook.setTitle(title);

        when(mockRepository.findByAuthorAndTitle(author,title)).thenReturn(mockBook);

        Book actual = bookService.getBooksByAuthorAndTitle(author,title);

        assertEquals(author, actual.getAuthor());
        assertEquals(title, actual.getTitle());
        verify(mockRepository).findByAuthorAndTitle(anyString(), anyString());

    }

  
   @Test
    void addBook_fail() {
       Book mockBook = new Book();
       mockBook.setId(1L);
       mockBook.setAuthor("Inger Christensen");
       mockBook.setTitle("Det");
       mockBook.setPublisher("Modernista");
       mockBook.setYear("2009");

       when(mockRepository.existsByAuthorAndTitle(anyString(), anyString())).thenReturn(true);

       assertThrows(ResponseStatusException.class, () -> bookService.addBook(mockBook));

       verify(mockRepository, times(0)).save(any());
       verify(mockRepository).existsByAuthorAndTitle(anyString(), anyString());
   }

    @Test
    void deleteBookTest_fail(){

        Book mockBook = new Book();
        mockBook.setId(5L);
        mockBook.setAuthor("Inger Christensen");
        mockBook.setTitle("Det");
        mockBook.setPublisher("Modernista");
        mockBook.setYear("2009");

        mockRepository.save(mockBook);

        when(mockRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(ResponseStatusException.class, ()-> bookService.deleteById(mockBook.getId()));

        verify(mockRepository, times(0)).deleteById(anyLong());
        verify(mockRepository).existsById(anyLong());
    }

    @Test
    void deleteBookTest_success(){
        Book mockBook = new Book();
        mockBook.setId(5L);
        mockBook.setAuthor("Inger Christensen");
        mockBook.setTitle("Det");
        mockBook.setPublisher("Modernista");
        mockBook.setYear("2009");

        mockRepository.save(mockBook);

        when(mockRepository.existsById(anyLong())).thenReturn(true);

        String message = bookService.deleteById(mockBook.getId());

        assertEquals("Book with id "+ mockBook.getId() + " is removed.", message);

        verify(mockRepository).deleteById(anyLong());
        verify(mockRepository).existsById(anyLong());
    }

  
    @Test
    void getBooksByCategory() {
        Category mockCat = new Category();
        mockCat.setName("poesi");
        mockCat.setId(1L);
        Book mockBook = new Book();
        String author = "Inger Christensen";
        mockBook.setId(1L);
        mockBook.setAuthor(author);
        mockBook.setTitle("Det");
        mockBook.setPublisher("Modernista");
        mockBook.setYear("2009");
        mockBook.setCategory(mockCat);

        when(mockRepository.findBookByCategory_Name(mockCat.getName())).thenReturn((Arrays.asList(mockBook)));

        List<Book> actual = bookService.getBooksByCategory(mockCat.getName());

        assertEquals(mockCat.getId(), actual.get(0).getCategory().getId());
        assertEquals(mockCat.getName(), actual.get(0).getCategory().getName());
        assertEquals(1, actual.size());
        verify(mockRepository).findBookByCategory_Name(anyString());
    }

    @Test
    void getBooksByYear() {
        Book mockBook = new Book();
        String year = "2009";
        mockBook.setId(1L);
        mockBook.setAuthor("Inger Christensen");
        mockBook.setTitle("Det");
        mockBook.setPublisher("Modernista");
        mockBook.setYear(year);

        when(mockRepository.findBookByYear(year)).thenReturn(Arrays.asList(mockBook));

        List<Book> actual = bookService.getBooksByYear(year);

        assertEquals(year, actual.get(0).getYear());
        verify(mockRepository).findBookByYear(anyString());
    }

    @Test
    void readItTest(){
        Book mockBook = new Book();
        String year = "2009";
        mockBook.setId(1L);
        mockBook.setAuthor("Inger Christensen");
        mockBook.setTitle("Det");
        mockBook.setPublisher("Modernista");
        mockBook.setYear(year);
        mockBook.setHaveIReadIt(false);
        String messg = "Book with id " + mockBook.getId() + " is now marked as read.";

        when(mockRepository.findById(anyLong())).thenReturn(java.util.Optional.of(mockBook));
        mockBook.setHaveIReadIt(true);
        mockRepository.save(mockBook);

        String actual = bookService.readIt(1L);

        assertEquals("Book with id 1 is now marked as read.", actual);

    }

}