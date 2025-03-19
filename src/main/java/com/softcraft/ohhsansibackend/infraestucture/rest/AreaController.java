package com.softcraft.ohhsansibackend.infraestucture.rest;

import com.softcraft.ohhsansibackend.application.usecases.AreaService;
import com.softcraft.ohhsansibackend.domain.models.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/areas")
public class AreaController {

    private final AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createArea(@RequestBody Area area) {
        Map<String, Object> response = areaService.saveArea(area);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Area>> getAllAreas() {
        List<Area> areas = areaService.getAllAreas();
        if (areas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(areas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAreaById(@PathVariable Long id) {
        Optional<Area> area = areaService.getAreaById(id);
        return area.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Área con ID " + id + " no encontrada")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateArea(@PathVariable Long id, @RequestBody Area area) {
        try {
            Map<String, Object> response = areaService.updateArea(id, area);

            if (response.containsKey("area")) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error al actualizar el área", "error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteArea(@PathVariable Long id) {
        boolean deleted = areaService.deleteArea(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "Área eliminada con éxito"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No se encontró el área con ID " + id));
        }
    }
}

