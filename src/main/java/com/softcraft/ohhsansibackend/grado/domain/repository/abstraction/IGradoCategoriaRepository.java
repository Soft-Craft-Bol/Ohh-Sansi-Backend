package com.softcraft.ohhsansibackend.grado.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.grado.domain.models.GradoCategoria;
import com.softcraft.ohhsansibackend.grado.infraestructure.request.GradoCategoriaDTO;

import java.util.List;

public interface IGradoCategoriaRepository {
    GradoCategoria save(GradoCategoria gradoCategoria);

    boolean update(GradoCategoria gradoCategoria);

    boolean delete(int idGradoCategoria);

    GradoCategoria findById(int idGradoCategoria);

    List<GradoCategoriaDTO> findAll();
}