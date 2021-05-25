package com.bibliotek.Repository;

import com.bibliotek.Models.Category;
import com.bibliotek.Repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jacaranda Perez
 * Date: 2021-05-18
 * Project: Bibliotek
 */
@DataMongoTest
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void findByNameTest(){
        Category category1 = new Category();
        Category category2 = new Category();
        category1.setId(1L);
        category2.setId(2L);
        category1.setName("Thriller");
        category2.setName("Deckare");
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        Category actualCategory = categoryRepository.findByName("Deckare");

        assertEquals(category2, actualCategory); 


    }

}
