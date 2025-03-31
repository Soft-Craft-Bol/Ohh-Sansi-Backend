package com.softcraft.ohhsansibackend.categoria.domain.repository.implementation;

import com.softcraft.ohhsansibackend.categoria.domain.models.Category;
import com.softcraft.ohhsansibackend.categoria.domain.repository.abstraction.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;


@Repository
public class CategoryDomainRepository implements ICategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public Category save(Category category) {
//        String sql = "INSERT INTO categorias(codigo_categoria, id_area) VALUES (?, ?)";
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, category.getCodigoCategoria());
//            ps.setInt(2, category.getIdArea());
//            return ps;
//        }, keyHolder);
//
//        int newCategoryId = keyHolder.getKey().intValue();
//        category.setIdCategoria(newCategoryId);
//        return category;
//    }
    @Override
    public Category save(Category category) {
        String sql = "INSERT INTO categorias(codigo_categoria, id_area) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getCodigoCategoria());
            ps.setInt(2, category.getIdArea());
            return ps;
        }, keyHolder);

        category.setIdCategoria(((Number) keyHolder.getKeyList().get(0).get("id_categoria")).intValue());
        category.setIdArea(((Number) keyHolder.getKeyList().get(0).get("id_area")).intValue());
        return category;
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
