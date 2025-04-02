package com.softcraft.ohhsansibackend.municipio.application.usecases;

import com.softcraft.ohhsansibackend.municipio.application.ports.MunicipioAdapter;
import com.softcraft.ohhsansibackend.municipio.domain.models.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MunicipioService {
    private final MunicipioAdapter municipioAdapter;

    @Autowired
    public MunicipioService(MunicipioAdapter municipioAdapter) {
        this.municipioAdapter = municipioAdapter;
    }

    public Map<String, Object> getMunicipioById(int id) {
        Municipio municipio = municipioAdapter.getMunicipioById(id);
        return Map.of("municipio", municipio);
    }

    public Map<String, Object> getAllMunicipios() {
        List<Municipio> municipios = municipioAdapter.getAllMunicipios();
        return Map.of("municipios", municipios);
    }

    public Map<String, Object> getMunicipiosByDepartamento(int idDepartamento) {
        List<Municipio> municipios = municipioAdapter.getMunicipiosByDepartamento(idDepartamento);
        return Map.of("municipios", municipios);
    }
}
