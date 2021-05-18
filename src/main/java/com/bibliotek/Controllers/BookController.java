package com.bibliotek.Controllers;

import com.bibliotek.Models.Book;
import com.bibliotek.Repositories.BookRepository;
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


    @RequestMapping("/author")
    public List<Book> showBooksByAuthour(String author){
        return bookRepository.findBookByAuthor(author);
    }

    @RequestMapping("/category")
    public List<Book> showBooksByCategory(String category){
        return bookRepository.findByCategory(category);
    }


    @RequestMapping ("/delete/{id}")
    public String deleteBookById(@PathVariable long id){
        int indexToRemove = -1;
        List<Book> books = StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getId() == id){
                indexToRemove = i;
            }
        }
        if(indexToRemove != -1){
            Book b = books.get(indexToRemove);
            bookRepository.delete(b);
            return "Book with id " + id + " is removed.";
        }
        return "Book with id " + id + " could not be removed.";
    }
}

