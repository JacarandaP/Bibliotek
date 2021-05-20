package com.bibliotek.Services;

import com.bibliotek.Models.Category;
import com.bibliotek.Repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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


    // Add addCategory_failures
    @Test
    void addCategory_invalid(){
        Category invalidCategory = new Category();

        assertThrows(ResponseStatusException.class, ()-> categoryService.addCategory(invalidCategory));
        verify(categoryRepository, times(0)).save(any());
        verify(categoryRepository, times(0)).existsByName(anyString());
    }


    @Test
    void getAllCategories() {
        Category testCategory1 = new Category();
        String expectedCategory1 = "Biografi";

        Category testCategory2 = new Category();
        String expectedCategory2 = "Deckare";

        testCategory1.setName(expectedCategory1);
        testCategory2.setName(expectedCategory2);

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(testCategory1, testCategory2));

        List<Category> actual = categoryService.getAllCategories();

        assertEquals(testCategory1, actual.get(0));
        assertEquals(testCategory2, actual.get(1));
        assertEquals(2, actual.size());
    }


  
    @Test
    void addCategory_Existing(){
        String expectedCategory ="Skönlitteratur";

        Category addC = new Category();
        addC.setName(expectedCategory);

        when(categoryRepository.existsByName(anyString())).thenReturn(true);

        assertThrows(ResponseStatusException. class, () ->categoryService.addCategory(addC));

        verify(categoryRepository, times(0)).save(any());
        verify(categoryRepository).existsByName(anyString());
    }
}