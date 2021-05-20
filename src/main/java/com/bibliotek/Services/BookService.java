package com.bibliotek.Services;

import com.bibliotek.Models.Book;
import com.bibliotek.Repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public List<Book> getBooks(){
        return repository.findAll();
    }


    public List<Book> getBooksIHaveRead(){
        List<Book> readBooks = repository.findByHaveIReadIt(true);
        return readBooks;
    }

    public List<Book> getBookToRead(){
        List<Book> booksToRead = repository.findByHaveIReadIt(false);
        return booksToRead;
    }

    public List<Book> getBooksByAuthor(String author){
        return repository.findBookByAuthor(author);
    }

    public List<Book> getBooksByTitle(String title){
        return repository.findBookByTitle(title);
    }

    public Book getBooksByAuthorAndTitle(String author, String title){
        return repository.findByAuthorAndTitle(author, title);
    }

    public List<Book> getBooksByCategory(String category){
        return repository.findByCategory(category);
    }

    public List<Book> getBooksByYear(String year){
        return repository.findBookByYear(year);
    }

    public Book addBook(Book book){
        boolean bookFound = repository.existsByAuthorAndTitle(book.getAuthor(), book.getTitle());
        if(bookFound) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book already in library.");
        } else
            return repository.save(book);
    }

   /* public String deleteBookById(long id){
        int indexToRemove = -1;
        List<Book> books = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getId() == id){
                indexToRemove = i;
            }
        }
        if(indexToRemove != -1){
            Book b = books.get(indexToRemove);
            repository.delete(b);
            return "Book with id " + id + " is removed.";
        }
        return "Book with id " + id + " could not be removed.";
    }*/

    public String deleteById(Long id) {
        boolean doesBookexist = repository.existsById(id);
        if (!doesBookexist) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book with " + id + " not found. Impossible to remove");
        } else{
        repository.deleteById(id);
        return "Book with id " + id + " is removed.";
        }
    }
}
