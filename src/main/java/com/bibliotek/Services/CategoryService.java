package com.bibliotek.Services;

import com.bibliotek.Models.Book;
import com.bibliotek.Models.Category;
import com.bibliotek.Repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
        boolean categoryFound = categoryRepository.existsByName(category.getName());
        if(categoryFound) {
            return null;
        } else
            return categoryRepository.save(category);
    }

}
