package com.bibliotek.Controllers;

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

    @ResponseBody
    @PostMapping(value="/add", consumes="application/json",produces="application/json")
    public Category addBook(@RequestBody Category category){
        boolean categoryFound = categoryRepository.existsByName(category.getName());
        if(categoryFound) {
            return null;
        } else
            return categoryRepository.save(category);
    }

}
