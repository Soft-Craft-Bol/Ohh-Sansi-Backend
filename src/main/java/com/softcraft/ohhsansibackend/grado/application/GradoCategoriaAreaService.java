package com.softcraft.ohhsansibackend.grado.application;

import com.softcraft.ohhsansibackend.area.application.usecases.AreaService;
import com.softcraft.ohhsansibackend.categoria.application.usecases.CategoryService;
import com.softcraft.ohhsansibackend.categoria.domain.models.Category;
import com.softcraft.ohhsansibackend.grado.application.usecases.GradoCategoriaService;
import com.softcraft.ohhsansibackend.grado.domain.models.GradoCategoria;
import com.softcraft.ohhsansibackend.grado.infraestructure.request.GradoCategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GradoCategoriaAreaService {

    private final GradoCategoriaService gradoCategoriaService;
    private final CategoryService categoryService;


    @Autowired
    public GradoCategoriaAreaService(GradoCategoriaService gradoCategoriaService, CategoryService categoryService) {
        this.gradoCategoriaService = gradoCategoriaService;
        this.categoryService = categoryService;
    }

    public  void processCategoriasGrados(GradoCategoriaDTO dto) {
        Category category = new Category();
        category.setNombreCategoria(dto.getNombreCategoria());
        Map<String, Object> response = categoryService.saveCategory(category);

        if (response.containsKey("success") && (boolean) response.get("success")) {
            int savedCategoryId = category.getIdCategoria();
            for (Integer gradoId : dto.getGrados()) {
                GradoCategoria gradoCategoria = new GradoCategoria();
                gradoCategoria.setIdGrado(gradoId);
                gradoCategoria.setIdCategoria(savedCategoryId);
                gradoCategoriaService.saveGradoCategoria(gradoCategoria);
            }
        } else {
            throw new RuntimeException("Error saving category: " + response.get("message"));
        }
    }

}