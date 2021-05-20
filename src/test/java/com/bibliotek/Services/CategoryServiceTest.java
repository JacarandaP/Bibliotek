package com.bibliotek.Services;

import com.bibliotek.Models.Category;
import com.bibliotek.Repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Lisa Ramel
 * Date: 2021-05-20
 * Time: 10:09
 * Project: Bibliotek
 * Copywrite: MIT
 */
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    public void init() {
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    void addNewCategory() {
        Category testCategory = new Category();
        String expectedCategory = "Skönlitteratur";

        testCategory.setName(expectedCategory);

        when(categoryRepository.save(any())).thenReturn(testCategory);

        Category actual = categoryService.addCategory(testCategory);

        assertEquals(expectedCategory, actual.getName());
        verify(categoryRepository).save(any());
    }


}