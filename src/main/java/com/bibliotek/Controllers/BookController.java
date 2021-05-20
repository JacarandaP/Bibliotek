package com.bibliotek.Controllers;

import com.bibliotek.Models.Book;
import com.bibliotek.Repositories.BookRepository;
import com.bibliotek.Services.BookService;

import lombok.RequiredArgsConstructor;

import org.assertj.core.internal.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Lisa Ramel
 * Date: 2021-05-18
 * Time: 10:30
 * Project: Bibliotek
 * Copywrite: MIT
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private final BookService service;

    @RequestMapping("")
    public Iterable<Book> showAllBooks(){
        return service.getBooks();
    }

    @ResponseBody
    @PostMapping(value="/add", consumes="application/json",produces="application/json")
    public Book addBook(@RequestBody Book book){
        return service.addBook(book);
    }

    @RequestMapping("/author")
    public List<Book> showBooksByAuthor(String author){
        return service.getBooksByAuthor(author);
    }

    @RequestMapping("/title")
    public List<Book> showBooksByTitle(String title){
        return service.getBooksByTitle(title);
    }

    @RequestMapping("/authorAndTitle")
    public Book showBooksByAuthorAndTitle(String author, String title){
        return service.getBooksByAuthorAndTitle(author, title);
    }

    @RequestMapping("/category")
    public List<Book> showBooksByCategory(String category){
        return service.getBooksByCategory(category);
    }

    @RequestMapping("/year")
    public List<Book> showBooksByYear(String year){
        return service.getBooksByYear(year);
    }

    @RequestMapping ("/delete/{id}")
    public String deleteBookById(@PathVariable long id){
        return service.deleteBookById(id);
    }

    @RequestMapping("/readBooks")
    public Iterable<Book> getReadBooks() { return service.getBooksIHaveRead();}

    @RequestMapping("/bookstoread")
    public Iterable<Book> getBooksToRead() { return service.getBookToRead();}


}

