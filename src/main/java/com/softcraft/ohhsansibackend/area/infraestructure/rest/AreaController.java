package com.softcraft.ohhsansibackend.area.infraestructure.rest;

import com.softcraft.ohhsansibackend.area.application.usecases.AreaService;
import com.softcraft.ohhsansibackend.area.domain.models.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/areas")
public class AreaController {
    private final AreaService areaService;
    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping("/register-area")
    public ResponseEntity<Map<String, Object>> createArea(@RequestBody Area area) {
        Map<String,Object> response = areaService.saveArea(area);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Area>> getAllAreas() {
        List<Area> areas = areaService.getAreas();
        return ResponseEntity.ok(areas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAreaById(@PathVariable int id) {
        Area area = areaService.findAreaById(id);
        if(area != null) {
            return ResponseEntity.ok(Map.of( "data", area));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", "Area not found"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateArea(@PathVariable int id, @RequestBody Area area) {
        area.setIdArea(id);
        Map<String, Object> response = areaService.updateArea(area);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteArea(@PathVariable int id) {
        Map<String, Object> response = areaService.deleteArea(id);
        return ResponseEntity.ok(response);
    }
}

