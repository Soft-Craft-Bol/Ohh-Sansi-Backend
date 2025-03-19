package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.domain.models.Category;
import com.softcraft.ohhsansibackend.domain.services.CategoryDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryDomainService categoryDomainService;

    public CategoryService(CategoryDomainService categoryDomainService) {
        this.categoryDomainService = categoryDomainService;
    }

    public void saveCategory(Category category) {
        categoryDomainService.createCategory(category);
    }

    public Optional<Category> findById(Long idCategory) {
        return categoryDomainService.getCategory(idCategory);
    }

    public List<Category> findAll() {
        return categoryDomainService.listCategory();
    }

    public void updateCategory(Category category) {
        categoryDomainService.updateCategory(category);
    }

    public void deleteCategory(Long idCategory) {
        categoryDomainService.deleteCategory(idCategory);
    }
}
