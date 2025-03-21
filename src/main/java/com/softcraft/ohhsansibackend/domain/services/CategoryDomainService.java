package com.softcraft.ohhsansibackend.domain.services;


import com.softcraft.ohhsansibackend.domain.models.Category;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryDomainService {
    private final ICategoryRepository ICategoryRepository;

    @Autowired
    public CategoryDomainService(ICategoryRepository ICategoryRepository) {
        this.ICategoryRepository = ICategoryRepository;
    }

    public Category createCategory(Category category) {
        return ICategoryRepository.save(category);
    }

    public Category getCategory(int id) {
        return ICategoryRepository.findById(id);
    }

    public List<Category> listCategory() {
        return ICategoryRepository.findAll();
    }

    public boolean updateCategory(Category category) {
        return ICategoryRepository.update(category);
    }

    public boolean deleteCategory(int id) {
        return ICategoryRepository.delete(id);
    }
}
