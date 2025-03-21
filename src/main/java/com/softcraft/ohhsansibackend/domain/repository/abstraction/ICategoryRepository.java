package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.domain.models.Category;
import java.util.List;


public interface ICategoryRepository {
    Category save(Category category);
    Category findById(int idCategoria);
    List<Category> findAll();
    boolean update(Category category);
    boolean delete(int idCategoria);
}
