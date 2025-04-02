package com.softcraft.ohhsansibackend.tutor.infraestructure.rest;

import com.softcraft.ohhsansibackend.tutor.application.usecases.TipoTutorService;
import com.softcraft.ohhsansibackend.tutor.domain.models.TipoTutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/tipo-tutor")
public class TipoTutorController {
    private final TipoTutorService tipoTutorService;

    @Autowired
    public TipoTutorController(TipoTutorService tipoTutorService) {
        this.tipoTutorService = tipoTutorService;
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@RequestBody TipoTutor tipoTutor) {
        return ResponseEntity.ok(tipoTutorService.save(tipoTutor));
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> update(@RequestBody TipoTutor tipoTutor) {
        return ResponseEntity.ok(tipoTutorService.update(tipoTutor));
    }

    @DeleteMapping("/delete/{idTipoTutor}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable int idTipoTutor) {
        return ResponseEntity.ok(tipoTutorService.delete(idTipoTutor));
    }

    @GetMapping("/{idTipoTutor}")
    public ResponseEntity<Map<String, Object>> findByIdTipoTutor(@PathVariable int idTipoTutor) {
        return ResponseEntity.ok(tipoTutorService.findByIdTipoTutor(idTipoTutor));
    }

    @GetMapping("/findAllTipoTutor")
    public ResponseEntity<Map<String, Object>> findAllTipoTutor() {
        return ResponseEntity.ok(tipoTutorService.findAllTipoTutor());
    }
}
