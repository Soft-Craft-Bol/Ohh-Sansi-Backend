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

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCategoryById(@PathVariable int id) {
        Category category = categoryService.findById(id);
        if(category != null) {
            return ResponseEntity.ok(Map.of( "data", category));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", "Category not found"));
        }
    }
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCategory(@PathVariable int id, @RequestBody Category category) {
        category.setIdCategoria(id);
        Map<String, Object> response = categoryService.updateCategory(category);
        return ResponseEntity.ok(response);
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable int id) {
        Map<String, Object> response = categoryService.deleteCategory(id);
        return ResponseEntity.ok(response);
     }


}
