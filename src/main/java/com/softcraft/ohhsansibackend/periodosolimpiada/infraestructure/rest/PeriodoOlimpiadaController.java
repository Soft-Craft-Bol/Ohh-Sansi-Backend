package com.softcraft.ohhsansibackend.periodosolimpiada.infraestructure.rest;

import com.softcraft.ohhsansibackend.periodosolimpiada.application.usecases.PeriodoOlimpiadaService;
import com.softcraft.ohhsansibackend.periodosolimpiada.application.usecases.OlimpiadaService;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.PeriodoOlimpiada;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.repository.implementation.PeriodoOlimpiadaDomainRepository;
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
    private final PeriodoOlimpiadaDomainRepository periodoService;

    @Autowired
    public PeriodoOlimpiadaController(PeriodoOlimpiadaService periodoOlimpiadaService, OlimpiadaService olimpiadaService, PeriodoOlimpiadaDomainRepository periodoService) {
        this.periodoOlimpiadaService = periodoOlimpiadaService;
        this.olimpiadaService = olimpiadaService;
        this.periodoService = periodoService;
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

    @GetMapping("/periodo-inscripcion-actual")
    public ResponseEntity<Map<String, Object>> buscarPeriodoInscripcionActual() {
        Map<String, Object> response = periodoOlimpiadaService.encontrarPeriodoInscripcionActualMap();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{idPeriodo}")
    public ResponseEntity<Map<String, Object>> actualizarPeriodo(@RequestBody PeriodoOlimpiada periodoOlimpiada,
                                                                @PathVariable("idPeriodo") Integer idPeriodo) {
        Map<String, Object> response = new HashMap<>();
        try {
            periodoOlimpiada.setIdPeriodo(idPeriodo);
            Map<String, Object> updatedResponse = periodoOlimpiadaService.actualizarPeriodo(periodoOlimpiada);
            if ("success".equals(updatedResponse.get("status"))) {
                return ResponseEntity.ok(updatedResponse);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedResponse);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al actualizar el período: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }


    @GetMapping("/verificar-estados")
    public ResponseEntity<String> verificarEstados() {
        periodoService.verificarYActualizarEstados();
        return ResponseEntity.ok("Estados verificados y actualizados");
    }

}
