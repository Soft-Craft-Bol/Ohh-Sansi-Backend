package com.softcraft.ohhsansibackend.colegio.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.colegio.domain.models.Colegio;

import java.util.List;

public interface IColegioDomainRepository {
    List<Colegio> getColegios();

    List<Colegio> getColegiosByMunicipio(int idMunicipio);
}
