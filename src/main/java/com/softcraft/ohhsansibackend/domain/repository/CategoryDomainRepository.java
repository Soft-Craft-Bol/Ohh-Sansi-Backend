package com.softcraft.ohhsansibackend.domain.repository;

import com.softcraft.ohhsansibackend.domain.models.Category;
import com.softcraft.ohhsansibackend.domain.repository.CategoryRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDomainRepository implements CategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    public CategoryDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Category category) {
        String sql = "CALL sp_insert_category(?, ?, ?)";
        jdbcTemplate.update(sql, category.getCodCategory());
    }

    @Override
    public Optional<Category> findById(Long idCategory) {
        String sql = "CALL sp_get_category_by_id(?)";

        return jdbcTemplate.query(sql, new Object[]{idCategory}, rs -> {
            if (rs.next()) {
                return Optional.of(new Category(
                        rs.getLong("ID_CATEGORY"),
                        rs.getLong("ID_AREA"),
                        rs.getLong("ID_TIPO_SUBJECT_CATEGORY"),
                        rs.getString("COD_CATEGORY")
                ));
            }
            return Optional.empty();
        });
    }


    @Override
    public List<Category> findAll() {
        String sql = "CALL sp_get_all_categories()";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Category(
                rs.getLong("ID_CATEGORY"),
                rs.getLong("ID_AREA"),
                rs.getLong("ID_TIPO_SUBJECT_CATEGORY"),
                rs.getString("COD_CATEGORY")
        ));
    }

    @Override
    public void update(Category category) {
        String sql = "CALL sp_update_category(?, ?, ?, ?)";
        jdbcTemplate.update(sql, category.getIdCategory(), category.getIdArea(), category.getIdTipoSubjectCategory(), category.getCodCategory());
    }

    @Override
    public void delete(Long idCategory) {
        String sql = "CALL sp_delete_category(?)";
        jdbcTemplate.update(sql, idCategory);
    }
}
