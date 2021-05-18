package com.bibliotek.Repositories;

import com.bibliotek.Models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Jacaranda Perez
 * Date: 2021-05-18
 * Project: Bibliotek
 */

public interface CategoryRepository extends CrudRepository {

    Book findById (Long id);
    List<Book> findByName (String name);

}
