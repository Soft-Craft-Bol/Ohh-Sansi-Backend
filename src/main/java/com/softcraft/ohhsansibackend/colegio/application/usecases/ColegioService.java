package com.softcraft.ohhsansibackend.colegio.application.usecases;


import com.softcraft.ohhsansibackend.colegio.application.ports.ColegioAdapter;
import com.softcraft.ohhsansibackend.colegio.domain.models.Colegio;
import com.softcraft.ohhsansibackend.municipio.domain.models.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ColegioService {
    private final ColegioAdapter colegioAdapter;

    @Autowired
    public ColegioService(ColegioAdapter colegioAdapter) {
        this.colegioAdapter = colegioAdapter;
    }

    public Map<String, Object> getColegiosByMunicipio(int idMunicipio) {
        List<Colegio> colegios = colegioAdapter.getColegiosByMunicipio(idMunicipio);
        return Map.of("colegios", colegios);
    }
}
