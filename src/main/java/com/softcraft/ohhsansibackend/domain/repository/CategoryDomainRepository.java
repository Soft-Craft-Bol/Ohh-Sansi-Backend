package com.softcraft.ohhsansibackend.domain.repository;

import com.softcraft.ohhsansibackend.domain.models.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    public CategoryDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Category category) {
        String sql = "INSERT INTO CATEGORY (NOMBRE_CATEGORY) VALUES ( ?)";
        jdbcTemplate.update(sql, category.getCodCategory());
    }

    public void update(Category category) {
        String sql = "UPDATE CATEGORY SET NOMBRE_CATEGORY = ? WHERE ID_CATEGORY = ?";
        jdbcTemplate.update(sql, category.getCodCategory(), category.getIdCategory());
    }
}
