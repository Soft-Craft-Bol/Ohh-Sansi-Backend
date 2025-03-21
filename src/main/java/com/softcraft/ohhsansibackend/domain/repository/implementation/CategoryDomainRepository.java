package com.softcraft.ohhsansibackend.domain.repository.implementation;

import com.softcraft.ohhsansibackend.domain.models.Category;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.ICategoryRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class CategoryDomainRepository implements ICategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    public CategoryDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Category save(Category category) {
        String sql = "SELECT insertCategory(?,?)";
        return jdbcTemplate.queryForObject(sql, new Object[] {category.getCodigoCategoria(), category.getIdArea()}
        , new BeanPropertyRowMapper<>(Category.class));

    }

    @Override
    public Category findById(int idCategoria) {
        String sql = "SELECT * FROM selectCategoryById(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{idCategoria}, new BeanPropertyRowMapper<>(Category.class));
    }


    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM selectAllCategories()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }

    @Override
    public boolean update(Category category) {
        String sql = "SELECT updateCategory(?, ?, ?)";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, category.getIdCategoria(), category.getCodigoCategoria(), category.getIdArea());
        return result;
    }

    @Override
    public boolean delete(int idCategoria) {
        String sql = "SELECT deleteCategory(?)";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, idCategoria);
        return result;
    }
}
