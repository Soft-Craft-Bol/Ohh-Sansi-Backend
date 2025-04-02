package com.softcraft.ohhsansibackend.area.infraestructure.rest;

import com.softcraft.ohhsansibackend.area.application.usecases.AreaService;
import com.softcraft.ohhsansibackend.area.domain.models.Area;
import com.softcraft.ohhsansibackend.area.infraestructure.DTO.UpdatePrecioAreaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public ResponseEntity<Map<String, Object>> createArea(@Valid @RequestBody Area area) {
        Map<String,Object> response = areaService.saveArea(area);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllAreas() {
        Map<String, Object> areas = areaService.getAreas();
        return ResponseEntity.status(HttpStatus.OK).body(areas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAreaById(@PathVariable int id) {
        Map<String, Object> response =  areaService.findAreaById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateArea(@Valid @PathVariable int id, @RequestBody Area area) {
        area.setIdArea(id);
        Map<String, Object> response = areaService.updateArea(area);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteArea(@PathVariable int id) {
        Map<String, Object> response = areaService.deleteArea(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update-precio")
    public ResponseEntity<Map<String, Object>> updatePrecioArea(@RequestBody UpdatePrecioAreaDTO updateDTO) {
        Map<String, Object> response = areaService.updatePrecioArea(updateDTO.getIdArea(), updateDTO.getPrecioArea());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

