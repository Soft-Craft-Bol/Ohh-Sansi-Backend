package com.softcraft.ohhsansibackend.domain.repository.implementation;

import com.softcraft.ohhsansibackend.domain.models.Category;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.ICategoryRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDomainRepository implements ICategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    public CategoryDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Category category) {
        String sql = "SELECT insertCategory(?)";
        jdbcTemplate.update(sql, category.getCodCategory());
    }

    @Override
    public Optional<Category> findById(Long idCategory) {
        String sql = "SELECT ";
        return jdbcTemplate.query(sql, new Object[]{idCategory},
                new BeanPropertyRowMapper <Category>(Category.class)).stream().findFirst();
    }


    @Override
    public List<Category> findAll() {
        String sql = "SELECT ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }

    @Override
    public void update(Category category) {
        String sql = "SELECT updateCategory(?, ?)";
        jdbcTemplate.update(sql, category.getIdCategory(), category.getCodCategory());
    }

    @Override
    public void delete(Long idCategory) {
        String sql = "SELECT deleteCategory(?)";
        jdbcTemplate.update(sql, idCategory);
    }
}
