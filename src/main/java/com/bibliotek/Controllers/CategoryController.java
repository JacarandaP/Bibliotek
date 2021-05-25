package com.bibliotek.Controllers;


import com.bibliotek.Models.Book;
import com.bibliotek.Models.Category;
import com.bibliotek.Repositories.CategoryRepository;
import com.bibliotek.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Lisa Ramel
 * Date: 2021-05-18
 * Time: 11:13
 * Project: Bibliotek
 * Copywrite: MIT
 */
@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @RequestMapping("")
    public List<Category> showAllCategories() {
        return categoryService.getAllCategories();
    }

    @ResponseBody
    @PostMapping(value="/add", consumes="application/json",produces="application/json")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/name")
    public Category showCategoryByName(@RequestParam String name){
        return categoryService.getCategoryByName(name);
    }

}
