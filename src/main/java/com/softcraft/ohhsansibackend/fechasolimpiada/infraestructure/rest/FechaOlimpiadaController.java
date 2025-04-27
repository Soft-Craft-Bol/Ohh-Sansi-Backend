package com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.rest;

import com.softcraft.ohhsansibackend.fechasolimpiada.application.usecases.FechaOlimpiadaService;
import com.softcraft.ohhsansibackend.fechasolimpiada.application.usecases.OlimpiadaEventoService;
import com.softcraft.ohhsansibackend.fechasolimpiada.application.usecases.OlimpiadaService;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.FechaOlimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.Olimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.dto.OlimpiadaEventosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fecha-olimpiada")
public class FechaOlimpiadaController {
    private final FechaOlimpiadaService fechaOlimpiadaService;
    private final OlimpiadaService olimpiadaService;
    private final OlimpiadaEventoService olimpiadaEventosService;

    @Autowired
    public FechaOlimpiadaController(FechaOlimpiadaService fechaOlimpiadaService, OlimpiadaService olimpiadaService, OlimpiadaEventoService olimpiadaEventosService) {
        this.fechaOlimpiadaService = fechaOlimpiadaService;
        this.olimpiadaService = olimpiadaService;
        this.olimpiadaEventosService = olimpiadaEventosService;
    }

    @PostMapping("register")
    public ResponseEntity<Map<String, Object>> crearOActualizar(@RequestBody FechaOlimpiada fechaOlimpiada) {
        Map<String, Object> response = fechaOlimpiadaService.upsertFechaOlimpiada(fechaOlimpiada);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/full-register")
    public ResponseEntity<Map<String, Object>> registrarOlimpiadaCompleta(@RequestBody OlimpiadaEventosDTO dto) {
        Map<String, Object> response = olimpiadaEventosService.registrarOlimpiadaConEventos(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable int id) {
        Map<String, Object> response = fechaOlimpiadaService.deleteFechaOlimpiada(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> buscarTodos() {
        Map<String, Object> response = fechaOlimpiadaService.getFechaOlimpiada();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable int id) {
        Map<String, Object> response = fechaOlimpiadaService.getFechaOlimpiadaById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/public")
    public ResponseEntity<Map<String, Object>> buscarActivo() {
        Map<String, Object> response = fechaOlimpiadaService.getFechaOlimpiadaPublic();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/olimpiadas-con-eventos")
    public ResponseEntity<List<OlimpiadaEventosDTO>> buscarOlimpiadasConEventos() {
        List<OlimpiadaEventosDTO> response = fechaOlimpiadaService.getOlimpiadasconEventos();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
