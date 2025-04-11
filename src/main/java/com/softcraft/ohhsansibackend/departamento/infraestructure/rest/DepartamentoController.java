package com.softcraft.ohhsansibackend.departamento.infraestructure.rest;

import com.softcraft.ohhsansibackend.departamento.application.usecases.DepartamentoService;
import com.softcraft.ohhsansibackend.departamento.domain.models.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {
    private final DepartamentoService departamentoService;

    @Autowired
    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @PostMapping("/register-departamento")
    public ResponseEntity<Map<String, Object>> addDepartamentos(@RequestBody List<Departamento> departamentos) {
        Map<String, Object> response = departamentoService.saveDepartamentos(departamentos);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllDepartamento() {
        Map<String, Object> areas = departamentoService.getAllDepartamentos();
        return ResponseEntity.status(HttpStatus.OK).body(areas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAreaById(@PathVariable int id) {
        Map<String, Object> response =  departamentoService.getDepartamentoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
