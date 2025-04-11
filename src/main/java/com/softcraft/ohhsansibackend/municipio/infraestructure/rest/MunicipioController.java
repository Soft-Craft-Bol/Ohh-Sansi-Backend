package com.softcraft.ohhsansibackend.municipio.infraestructure.rest;

import com.softcraft.ohhsansibackend.departamento.domain.models.Departamento;
import com.softcraft.ohhsansibackend.municipio.application.usecases.MunicipioService;
import com.softcraft.ohhsansibackend.municipio.domain.models.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/municipios")
public class MunicipioController {
    private final MunicipioService municipioService;

    @Autowired
    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @PostMapping("/register-municipio")
    public ResponseEntity<Map<String, Object>> addMunicipios(@RequestBody List<Municipio> municipios) {
        Map<String, Object> response = municipioService.saveMunicipios(municipios);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllMunicipios() {
        Map<String, Object> municipios = municipioService.getAllMunicipios();
        return ResponseEntity.status(HttpStatus.OK).body(municipios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getMunicipioById(@PathVariable int id) {
        Map<String, Object> response =  municipioService.getMunicipioById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/departamento/{idDepartamento}")
    public ResponseEntity<Map<String, Object>> getMunicipiosByDepartamento(@PathVariable int idDepartamento) {
        Map<String, Object> response =  municipioService.getMunicipiosByDepartamento(idDepartamento);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
