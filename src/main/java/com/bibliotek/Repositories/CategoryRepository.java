package com.bibliotek.Repositories;

import com.bibliotek.Models.Book;
import com.bibliotek.Models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Jacaranda Perez
 * Date: 2021-05-18
 * Project: Bibliotek
 */

public interface CategoryRepository extends CrudRepository<Category,Long> {

    Category findCategoryById(Long id);
    List<Category> findByName (String name);

}
