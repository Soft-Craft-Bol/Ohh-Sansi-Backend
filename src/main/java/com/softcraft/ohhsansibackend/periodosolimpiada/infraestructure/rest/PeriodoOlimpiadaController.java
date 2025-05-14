package com.softcraft.ohhsansibackend.periodosolimpiada.infraestructure.rest;

import com.softcraft.ohhsansibackend.periodosolimpiada.application.usecases.PeriodoOlimpiadaService;
import com.softcraft.ohhsansibackend.periodosolimpiada.application.usecases.OlimpiadaService;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.PeriodoOlimpiada;
import com.softcraft.ohhsansibackend.periodosolimpiada.infraestructure.dto.OlimpiadaEventosDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fecha-olimpiada")
public class PeriodoOlimpiadaController {
    private final PeriodoOlimpiadaService periodoOlimpiadaService;
    private final OlimpiadaService olimpiadaService;

    @Autowired
    public PeriodoOlimpiadaController(PeriodoOlimpiadaService periodoOlimpiadaService, OlimpiadaService olimpiadaService) {
        this.periodoOlimpiadaService = periodoOlimpiadaService;
        this.olimpiadaService = olimpiadaService;
    }

    @PostMapping("register")
    public ResponseEntity<Map<String, Object>> crearPeriodo(
            @Valid @RequestBody PeriodoOlimpiada periodo,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));

            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "status", "error",
                            "message", "Error de validación",
                            "errors", errors
                    ));
        }

        Map<String, Object> response = periodoOlimpiadaService.insertPeriodoOlimpiada(periodo);

        if ("success".equals(response.get("status"))) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/olimpiadas-con-eventos")
    public ResponseEntity<List<OlimpiadaEventosDTO>> buscarOlimpiadasConEventos() {
        List<OlimpiadaEventosDTO> response = periodoOlimpiadaService.getOlimpiadasconEventos();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
