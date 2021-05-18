package com.bibliotek.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 * Created by Miwa Guhrés
 * Date: 2021-05-18
 * Time: 10:50
 * Project: Bibliotek
 * Copyright: MIT
 */
@Data
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private  String name;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(){}
}
