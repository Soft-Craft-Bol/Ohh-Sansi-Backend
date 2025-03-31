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
    private final CategoryAdapter categoryAdapter;

    @Autowired
    public CategoryService(CategoryDomainRepository categoryDomainRepository, CategoryAdapter categoryAdapter) {
        this.categoryDomainRepository = categoryDomainRepository;
        this.categoryAdapter = categoryAdapter;
    }

    public Map<String, Object> saveCategory(Category category) {
        try {
            categoryAdapter.saveCategory(category);
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException(e.getMessage());
        }
        return Map.of("success", true, "message", "Categoria creada exitosamente");
    }

<<<<<<< HEAD

=======
    public Map<String, Object> deleteCategory(int id) {
        if (!categoryDomainRepository.delete(id)) {
            throw new ResourceNotFoundException("Categoria no encontrada");
        }
        return Map.of("success", true, "message", "Categoria eliminada exitosamente");
    }
>>>>>>> 3acbd87 (delete category)
}