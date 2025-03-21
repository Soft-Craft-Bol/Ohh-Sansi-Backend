package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.application.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.application.ports.CategoryAdapter;
import com.softcraft.ohhsansibackend.domain.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class CategoryService {
    private final CategoryAdapter categoryAdapter;

    @Autowired
    public CategoryService(CategoryAdapter categoryAdapter) {
        this.categoryAdapter = categoryAdapter;
    }

    public Map<String, Object> saveCategory(Category category) {
        try {
            categoryAdapter.saveCategory(category);
        }catch (DuplicateKeyException e){
            throw new DuplicateKeyException(e.getMessage());
        }
        return Map.of("success", true, "message", "Categoria creada exitosamente");
    }

    public Category findById(int idCategoria) {
        Category category = categoryAdapter.findById(idCategoria);
        if(category == null) {
            throw new ResourceNotFoundException("Categoria con ID" + idCategoria + " no encontrada");
        }
        return category;
    }

    public List<Category> findAll() {
        return categoryAdapter.findAll();
    }

    public Map<String, Object> updateCategory(Category category) {
        if (categoryAdapter.findById(category.getIdCategoria()) == null) {
            throw new ResourceNotFoundException("Categoria con ID " + category.getIdCategoria() + " no encontrada");
        }
        categoryAdapter.updateCategory(category);
        return Map.of("success", true, "message", "Categoria actualizada exitosamente");
    }

    public Map<String, Object> deleteCategory(int idCategoria) {
        if (categoryAdapter.findById(idCategoria) == null) {
            throw new ResourceNotFoundException("Categoria con ID " + idCategoria + " no encontrada");
        }
        categoryAdapter.deleteCategory(idCategoria);
        return Map.of("success", true, "message", "Categoria eliminada exitosamente");
    }
}
