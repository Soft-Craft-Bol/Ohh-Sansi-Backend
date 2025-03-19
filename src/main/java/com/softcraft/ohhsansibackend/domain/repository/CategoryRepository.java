package com.softcraft.ohhsansibackend.domain.repository;

import com.softcraft.ohhsansibackend.domain.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    void save(Category category);
    Optional<Category> findById(Long idCategory);
    List<Category> findAll();
    void update(Category category);
    void delete(Long id);
}
