package com.bibliotek.Services;

import com.bibliotek.Models.Book;
import com.bibliotek.Repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2021-05-20
 * Project: Bibliotek
 * Copyright: MIT
 */

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository repository;

    public Iterable<Book> getBooks(){
        return repository.findAll();
    }

    public Iterable<Book> getBooksIHaveRead(){
        Iterable<Book> readBooks = repository.findByHaveIReadIt(true);
        return readBooks;
    }


}
