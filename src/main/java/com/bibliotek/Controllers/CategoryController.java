package com.bibliotek.Controllers;


import com.bibliotek.Models.Book;
import com.bibliotek.Models.Category;
import com.bibliotek.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Lisa Ramel
 * Date: 2021-05-18
 * Time: 11:13
 * Project: Bibliotek
 * Copywrite: MIT
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;


    @RequestMapping("")
    public Iterable<Category> showAllCategories() {
        return categoryRepository.findAll();
    }
    @ResponseBody
    @PostMapping(value="/add", consumes="application/json",produces="application/json")
    public Category addCategory(@RequestBody Category category){
        boolean categoryFound = categoryRepository.existsByName(category.getName());
        if(categoryFound) {
            return null;
        } else
            return categoryRepository.save(category);

    }

}
