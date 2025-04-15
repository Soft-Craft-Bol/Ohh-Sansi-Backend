package com.softcraft.ohhsansibackend.categoria.application.usecases;

import com.softcraft.ohhsansibackend.categoria.domain.repository.implementation.CategoryDomainRepository;
import com.softcraft.ohhsansibackend.exception.DuplicateResourceException;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.categoria.application.ports.CategoryAdapter;
import com.softcraft.ohhsansibackend.categoria.domain.models.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Tag(name = "Category Service", description = "Service for managing categories")
@Service
public class CategoryService {

    private final CategoryDomainRepository categoryDomainRepository;
    private final CategoryAdapter categoryAdapter;
    private final Validator validator;

    @Autowired
    public CategoryService(CategoryDomainRepository categoryDomainRepository, CategoryAdapter categoryAdapter, Validator validator) {
        this.categoryDomainRepository = categoryDomainRepository;
        this.categoryAdapter = categoryAdapter;
        this.validator = validator;
    }
    @Operation(summary = "Save a new category", description = "Validates and saves a new category")
    public Map<String, Object> saveCategory(Category category) {
        validateCategory(category);
        try {
            categoryDomainRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException("Categoría ya registrada");
        }
        return Map.of("success", true, "message", "Categoria creada exitosamente");
    }
    private void validateCategory(Category category) {
        Set<ConstraintViolation<Category>> violations = validator.validate(category);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }


    public Map<String, Object> deleteCategory(int id) {
        if (!categoryDomainRepository.delete(id)) {
            throw new ResourceNotFoundException("Categoria no encontrada");
        }
        return Map.of("success", true, "message", "Categoria eliminada exitosamente");
    }

    public Map<String, Object> findAll(Category category) {
        List<Category> categories = categoryAdapter.findAll();
        return Map.of("success", true, "message", "Categorias encontradas", "data", categories);
    }
}