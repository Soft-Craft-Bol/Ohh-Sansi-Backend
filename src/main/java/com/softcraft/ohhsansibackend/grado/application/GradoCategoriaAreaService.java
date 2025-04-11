package com.softcraft.ohhsansibackend.grado.application;

import com.softcraft.ohhsansibackend.area.application.usecases.AreaService;
import com.softcraft.ohhsansibackend.area.domain.models.AreaNivelEscolar;
import com.softcraft.ohhsansibackend.categoria.application.usecases.CategoryService;
import com.softcraft.ohhsansibackend.categoria.domain.models.Category;
import com.softcraft.ohhsansibackend.grado.application.usecases.GradoCategoriaService;
import com.softcraft.ohhsansibackend.grado.domain.models.GradoCategoria;
import com.softcraft.ohhsansibackend.grado.infraestructure.request.GradoCategoriaAreaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GradoCategoriaAreaService {

    private final GradoCategoriaService gradoCategoriaService;
    private final CategoryService categoryService;
    private final AreaService areaService;


    @Autowired
    public GradoCategoriaAreaService(GradoCategoriaService gradoCategoriaService, CategoryService categoryService, AreaService areaService) {
        this.gradoCategoriaService = gradoCategoriaService;
        this.categoryService = categoryService;
        this.areaService = areaService;
    }

    public void proccessGradoCategoriaArea(GradoCategoriaAreaDTO dto) {
        if(dto.getFlag() == 1) {
            processCategoriasNiveles(dto);
        }
    }

    private void processCategoriasNiveles(GradoCategoriaAreaDTO dto) {
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