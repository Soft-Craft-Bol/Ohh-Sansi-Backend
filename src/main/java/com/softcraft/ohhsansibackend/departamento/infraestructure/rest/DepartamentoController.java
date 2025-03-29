package com.softcraft.ohhsansibackend.departamento.infraestructure.rest;

import com.softcraft.ohhsansibackend.departamento.application.usecases.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {
    private final DepartamentoService departamentoService;

    @Autowired
    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllAreas() {
        Map<String, Object> areas = departamentoService.getAllDepartamentos();
        return ResponseEntity.status(HttpStatus.OK).body(areas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAreaById(@PathVariable int id) {
        Map<String, Object> response =  departamentoService.getDepartamentoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
