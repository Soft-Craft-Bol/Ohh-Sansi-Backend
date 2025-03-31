package com.softcraft.ohhsansibackend.categoria.application.usecases;

import com.softcraft.ohhsansibackend.categoria.domain.repository.implementation.CategoryDomainRepository;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.categoria.application.ports.CategoryAdapter;
import com.softcraft.ohhsansibackend.categoria.domain.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CategoryService {

    private final CategoryDomainRepository categoryDomainRepository;

    @Autowired
    public CategoryService(CategoryDomainRepository categoryDomainRepository) {
        this.categoryDomainRepository = categoryDomainRepository;
    }

    public Map<String, Object> saveCategory(Category category) {
        try {
            categoryDomainRepository.save(category);
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException(e.getMessage());
        }
        return Map.of("success", true, "message", "Categoria creada exitosamente");
    }
}