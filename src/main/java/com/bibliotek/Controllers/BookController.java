package com.bibliotek.Controllers;

import com.bibliotek.Models.Book;
import com.bibliotek.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Lisa Ramel
 * Date: 2021-05-18
 * Time: 10:30
 * Project: Bibliotek
 * Copywrite: MIT
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("")
    public Iterable<Book> showAllBooks(){
        return bookRepository.findAll();
    }

}

