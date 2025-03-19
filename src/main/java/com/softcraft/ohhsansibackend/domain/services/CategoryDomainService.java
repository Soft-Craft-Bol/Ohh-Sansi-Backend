package com.softcraft.ohhsansibackend.domain.services;


import com.softcraft.ohhsansibackend.domain.models.Category;
import com.softcraft.ohhsansibackend.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryDomainService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryDomainService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public Optional<Category> getCategory(Long id) {
        return categoryRepository.findById(id);
    }

    public List<Category> listCategory() {
        return categoryRepository.findAll();
    }

    public void updateCategory(Category category) {
        categoryRepository.update(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.delete(id);
    }
}
