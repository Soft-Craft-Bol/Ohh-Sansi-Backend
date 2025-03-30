package com.softcraft.ohhsansibackend.categoria.domain.services;


import com.softcraft.ohhsansibackend.categoria.domain.models.Category;
import com.softcraft.ohhsansibackend.categoria.domain.repository.abstraction.ICategoryRepository;
import com.softcraft.ohhsansibackend.categoria.domain.repository.implementation.CategoryDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryDomainService {
    private final CategoryDomainRepository categoryDomainRepository;

    @Autowired
    public CategoryDomainService(CategoryDomainRepository categoryDomainRepository) {
        this.categoryDomainRepository = categoryDomainRepository;
    }

    public Category createCategory(Category category) {
        return categoryDomainRepository.save(category);
    }

    public Category getCategory(int id) {
        return categoryDomainRepository.findById(id);
    }

    public List<Category> listCategory() {
        return categoryDomainRepository.findAll();
    }

    public boolean updateCategory(Category category) {
        return categoryDomainRepository.update(category);
    }

    public boolean deleteCategory(int id) {
        return categoryDomainRepository.delete(id);
    }
}
