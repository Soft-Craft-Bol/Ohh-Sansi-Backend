package com.softcraft.ohhsansibackend.nivelescolar.application;

import com.softcraft.ohhsansibackend.area.domain.models.AreaNivelEscolar;
import com.softcraft.ohhsansibackend.categoria.application.usecases.CategoryService;
import com.softcraft.ohhsansibackend.categoria.domain.models.Category;
import com.softcraft.ohhsansibackend.nivelescolar.application.usecases.AreaNivelEscolarService;
import com.softcraft.ohhsansibackend.nivelescolar.application.usecases.NivelEscolarCategoriaService;
import com.softcraft.ohhsansibackend.nivelescolar.domain.models.NivelEscolarCategorias;
import com.softcraft.ohhsansibackend.nivelescolar.infraestructure.request.NivelEscolarCategoriaAreaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NivelEscolarCategoriaAreaService {

    private final NivelEscolarCategoriaService nivelEscolarCategoriaService;
    private final CategoryService categoryService;
    private final AreaNivelEscolarService areaNivelEscolarService;


    @Autowired
    public NivelEscolarCategoriaAreaService(NivelEscolarCategoriaService nivelEscolarCategoriaService, CategoryService categoryService, AreaNivelEscolarService areaNivelEscolarService) {
        this.nivelEscolarCategoriaService = nivelEscolarCategoriaService;
        this.categoryService = categoryService;
        this.areaNivelEscolarService = areaNivelEscolarService;
    }

    public void proccessNivelEscolarCategoriaArea(NivelEscolarCategoriaAreaDTO dto) {
        if(dto.getFlag() == 1) {
            processCategoriasNiveles(dto);
        } else {
            processAreasNiveles(dto);
        }
    }

    private void processCategoriasNiveles(NivelEscolarCategoriaAreaDTO dto) {
        Category category = new Category();
        category.setCodigoCategoria(dto.getCodCategory());
        category.setIdArea(dto.getIdArea().get(0));
        Map<String, Object> response = categoryService.saveCategory(category);

        if (response.containsKey("success") && (boolean) response.get("success")) {
            int savedCategoryId = category.getIdCategoria();
            for (Integer nivelEscolarId : dto.getNivelesEscolares()) {
                NivelEscolarCategorias nivelEscolarCategorias = new NivelEscolarCategorias();
                nivelEscolarCategorias.setIdNivel(nivelEscolarId);
                nivelEscolarCategorias.setIdArea(dto.getIdArea().get(0));
                nivelEscolarCategorias.setIdCategoria(savedCategoryId);
                nivelEscolarCategoriaService.saveNivelEscolarCategorias(nivelEscolarCategorias);
            }
        } else {
            throw new RuntimeException("Error saving category: " + response.get("message"));
        }
    }

    private void processAreasNiveles(NivelEscolarCategoriaAreaDTO dto) {
        for (Integer idArea : dto.getIdArea()) {
            for (Integer idNivel : dto.getNivelesEscolares()) {
                AreaNivelEscolar areaNivelEscolar = new AreaNivelEscolar();
                areaNivelEscolar.setIdArea(idArea);
                areaNivelEscolar.setIdNivel(idNivel);
                areaNivelEscolarService.saveAreaNivelEscolar(areaNivelEscolar);
            }
        }
    }
}