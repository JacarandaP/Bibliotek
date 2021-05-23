package com.bibliotek.Controllers;

import com.bibliotek.Models.Book;
import com.bibliotek.Repositories.BookRepository;
import com.bibliotek.Services.BookService;

import lombok.RequiredArgsConstructor;

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
    public List<Book> showAllBooks(){
        return service.getBooks();
    }

    @ResponseBody
    @PostMapping(value="/add", consumes="application/json",produces="application/json")
    public Book addBook(@RequestBody Book book){
        return service.addBook(book);
    }

    @GetMapping("/author")
    public List<Book> showBooksByAuthor(@RequestParam String author){
        return service.getBooksByAuthor(author);
    }

    @GetMapping("/title")
    public List<Book> showBooksByTitle(@RequestParam String title){
        return service.getBooksByTitle(title);
    }

    @GetMapping("/authorAndTitle")
    public Book showBooksByAuthorAndTitle(@RequestParam String author, @RequestParam String title){
        return service.getBooksByAuthorAndTitle(author, title);
    }

    @GetMapping("/category")
    public List<Book> showBooksByCategory(@RequestParam String category){
        return service.getBooksByCategory(category);
    }

    @GetMapping("/year")
    public List<Book> showBooksByYear(@RequestParam String year){
        return service.getBooksByYear(year);
    }

    @RequestMapping ("/delete/{id}")
    public String deleteBookById(@PathVariable long id){
        return service.deleteById(id);
    }

    @RequestMapping("/readBooks")
    public List<Book> getReadBooks() { return service.getBooksIHaveRead();}

    @RequestMapping("/bookstoread")
    public List<Book> getBooksToRead() { return service.getBookToRead();}


}

