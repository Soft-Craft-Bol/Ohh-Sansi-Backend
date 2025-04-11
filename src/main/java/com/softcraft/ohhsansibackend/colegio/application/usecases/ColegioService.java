package com.softcraft.ohhsansibackend.colegio.application.usecases;

import com.softcraft.ohhsansibackend.colegio.application.ports.ColegioAdapter;
import com.softcraft.ohhsansibackend.colegio.domain.models.Colegio;
import com.softcraft.ohhsansibackend.departamento.domain.models.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ColegioService {
    private final ColegioAdapter colegioAdapter;

    @Autowired
    public ColegioService(ColegioAdapter colegioAdapter) {
        this.colegioAdapter = colegioAdapter;
    }

    public Map<String, Object> saveColegios(List<Colegio> colegios) {
        List<Colegio> savedColegios = colegioAdapter.saveColegios(colegios);
        Map<String, Object> response = new HashMap<>();
        response.put("colegios", savedColegios);
        return response;
    }
    public Map<String, Object> getColegios() {
        List<Colegio> colegios = colegioAdapter.getColegios();
        return Map.of("colegios", colegios);
    }
    public Map<String, Object> getColegiosByMunicipio(int idMunicipio) {
        List<Colegio> colegios = colegioAdapter.getColegiosByMunicipio(idMunicipio);
        return Map.of("colegios", colegios);
    }
}
