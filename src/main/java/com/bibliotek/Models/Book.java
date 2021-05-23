package com.bibliotek.Models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


/**
 * Created by Jacaranda Perez
 * Date: 2021-05-18
 * Project: Bibliotek
 */
@Data
public class Book {

    @Id

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String year;
    private boolean haveIReadIt;

    private Category category;

    public Book(long id, String title, String author, String publisher, Category category, String year, boolean haveIReadIt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.year = year;
        this.haveIReadIt = haveIReadIt;

    }

    public Book(){}
}
