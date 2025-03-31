package com.softcraft.ohhsansibackend.categoria.infraestructure.rest;


import com.softcraft.ohhsansibackend.categoria.application.usecases.CategoryService;
import com.softcraft.ohhsansibackend.categoria.domain.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/register-category")
    public ResponseEntity<Map<String, Object>> createCategory(@RequestBody Category category) {
        Map<String, Object> response = categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }




}
