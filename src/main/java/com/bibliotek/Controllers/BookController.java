package com.bibliotek.Controllers;

import com.bibliotek.Models.Book;
import com.bibliotek.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Lisa Ramel
 * Date: 2021-05-18
 * Time: 10:30
 * Project: Bibliotek
 * Copywrite: MIT
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("")
    public Iterable<Book> showAllBooks(){
        return bookRepository.findAll();
    }

    @ResponseBody
    @PostMapping(value="/add", consumes="application/json",produces="application/json")
    public Book addBook(@RequestBody Book book){
        boolean bookFound = bookRepository.existsByAuthorAndTitle(book.getAuthor(), book.getTitle());
        if(bookFound) {
            return null;
        } else
            return bookRepository.save(book);
    }


}

