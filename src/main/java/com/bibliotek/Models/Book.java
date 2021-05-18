package com.bibliotek.Models;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 * Created by Jacaranda Perez
 * Date: 2021-05-18
 * Project: Bibliotek
 */

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private long id; //Uniq id
    private String title;
    private String author;
    private String publisher;
    private String year;

    @OneToOne
    @JoinColumn(name="categoryId")
    private Category category;

    public Book(long id, String title, String author, String publisher, Category category, String year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.year = year;
    }

    public Book(){}
}
