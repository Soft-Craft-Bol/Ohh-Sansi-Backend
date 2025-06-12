package com.softcraft.ohhsansibackend.area.infraestructure.rest;

import com.softcraft.ohhsansibackend.area.application.usecases.AreaService;
import com.softcraft.ohhsansibackend.area.application.usecases.ConvocatoriaService;
import com.softcraft.ohhsansibackend.area.domain.models.Area;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequestMapping("/areas")
public class AreaController {
    private final AreaService areaService;
    private final ConvocatoriaService convocatoriaService;
    @Autowired
    public AreaController(AreaService areaService, ConvocatoriaService convocatoriaService) {
        this.areaService = areaService;
        this.convocatoriaService = convocatoriaService;
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

    @PostMapping("/convocatoria/pdf")
    public ResponseEntity<Map<String, Object>> saveConvocatoria(
            @RequestParam int idArea,
            @RequestParam int idOlimpiada,
            @RequestPart("pdfFile") MultipartFile pdfFile
    ) {
        Map<String, Object> response = convocatoriaService.saveConvocatoria(idArea, idOlimpiada, pdfFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/convocatoria/{idArea}/{idOlimpiada}")
    public ResponseEntity<Map<String, Object>> getConvocatoriaByAreaAndOlimpiada(
            @PathVariable int idArea,
            @PathVariable int idOlimpiada) {
        Map<String, Object> response = convocatoriaService.getConvocatoriasByAreaOlimpiada(idArea, idOlimpiada);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

