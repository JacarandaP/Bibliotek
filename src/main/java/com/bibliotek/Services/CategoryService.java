package com.bibliotek.Services;

import com.bibliotek.Models.Book;
import com.bibliotek.Models.Category;
import com.bibliotek.Repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Created by Lisa Ramel
 * Date: 2021-05-20
 * Time: 09:24
 * Project: Bibliotek
 * Copywrite: MIT
 */
@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category){
        validateCategory(category);
        boolean categoryFound = categoryRepository.existsByName(category.getName());
        if(categoryFound) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category already exists.");
        } else
            return categoryRepository.save(category);
    }

    //Add to test addCategory_invalid
    private void validateCategory(Category category){
        if(category.getName()== null || category.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name must be written.");
        }
    }

}
