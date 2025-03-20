package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.application.ports.CategoryAdapter;
import com.softcraft.ohhsansibackend.domain.models.Category;
import com.softcraft.ohhsansibackend.domain.services.CategoryDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryAdapter categoryAdapter;

    public CategoryService(CategoryAdapter categoryAdapter) {
        this.categoryAdapter = categoryAdapter;
    }

    public void saveCategory(Category category) {
        categoryAdapter.saveCategory(category);
    }

    public Optional<Category> findById(Long idCategory) {
        return categoryAdapter.findById(idCategory);
    }

    public List<Category> findAll() {
        return categoryAdapter.findAll();
    }

    public void updateCategory(Category category) {
        categoryAdapter.updateCategory(category);
    }

    public void deleteCategory(Long idCategory) {
        categoryAdapter.deleteCategory(idCategory);
    }
}
