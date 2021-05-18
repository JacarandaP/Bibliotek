package com.bibliotek.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
