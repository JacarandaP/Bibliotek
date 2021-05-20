package com.bibliotek.Models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 * Created by Jacaranda Perez
 * Date: 2021-05-18
 * Project: Bibliotek
 */
@Data
@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private Long id; //Uniq id
    private String title;
    private String author;
    private String publisher;
    private String year;
    private boolean haveIReadIt;

    @ManyToOne
    @JoinColumn(name="categoryId", referencedColumnName = "id")
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
