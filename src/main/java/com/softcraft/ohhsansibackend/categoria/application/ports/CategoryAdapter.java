package com.softcraft.ohhsansibackend.categoria.application.ports;

import com.softcraft.ohhsansibackend.categoria.domain.models.Category;
import com.softcraft.ohhsansibackend.categoria.domain.services.CategoryDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryAdapter {
    private final CategoryDomainService categoryDomainService;

    @Autowired
    public CategoryAdapter(CategoryDomainService categoryDomainService) {
        this.categoryDomainService = categoryDomainService;
    }

    public Category saveCategory(Category category) {
        return categoryDomainService.createCategory(category);
    }

    public List<Category> findAll() {
        return categoryDomainService.listCategory();
    }

    public Category findById(int idCategoria) {
        return categoryDomainService.getCategory(idCategoria);
    }

    public boolean updateCategory(Category category) {
        return categoryDomainService.updateCategory(category);
    }

    public boolean deleteCategory(int idCategoria) {
        return categoryDomainService.deleteCategory(idCategoria);
    }

}
