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
        areaService.saveArea(area);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Área creada con éxito", "area", area));
    }

    @GetMapping
    public ResponseEntity<List<Area>> getAllAreas() {
        return ResponseEntity.ok(areaService.getAreas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> getAreaById(@PathVariable Long id) {
        Optional<Area> area = areaService.findAreaById(id);
        return area.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateArea(@PathVariable Long id, @RequestBody Area area) {
        area.setIdArea(id);
        areaService.updateArea(area);
        return ResponseEntity.ok(Map.of("message", "Área actualizada con éxito", "area", area));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteArea(@PathVariable Long id) {
        areaService.deleteArea(id);
        return ResponseEntity.ok(Map.of("message", "Área eliminada con éxito"));
    }
}

