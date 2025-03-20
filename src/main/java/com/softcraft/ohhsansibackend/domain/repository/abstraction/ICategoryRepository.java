package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.domain.models.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {
    void save(Category category);
    Optional<Category> findById(Long idCategory);
    List<Category> findAll();
    void update(Category category);
    void delete(Long id);
}
