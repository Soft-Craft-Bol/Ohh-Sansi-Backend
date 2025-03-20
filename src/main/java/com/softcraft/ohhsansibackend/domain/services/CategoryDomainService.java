package com.softcraft.ohhsansibackend.domain.services;


import com.softcraft.ohhsansibackend.domain.models.Category;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryDomainService {
    private final ICategoryRepository ICategoryRepository;

    @Autowired
    public CategoryDomainService(ICategoryRepository ICategoryRepository) {
        this.ICategoryRepository = ICategoryRepository;
    }

    public void createCategory(Category category) {
        ICategoryRepository.save(category);
    }

    public Optional<Category> getCategory(Long id) {
        return ICategoryRepository.findById(id);
    }

    public List<Category> listCategory() {
        return ICategoryRepository.findAll();
    }

    public void updateCategory(Category category) {
        ICategoryRepository.update(category);
    }

    public void deleteCategory(Long id) {
        ICategoryRepository.delete(id);
    }
}
