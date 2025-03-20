package com.softcraft.ohhsansibackend.infraestucture.rest;

import com.softcraft.ohhsansibackend.application.usecases.InscriptionService;
import com.softcraft.ohhsansibackend.domain.models.Inscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscription")
public class InscriptionController {
    private final InscriptionService inscriptionService;

    @Autowired
    public InscriptionController(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @PostMapping
    public ResponseEntity<Inscription> createInscription(@RequestBody Inscription inscription) {
        inscriptionService.saveInscription(inscription);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscription> getInscription(@PathVariable Long id) {
        return inscriptionService.findInscriptionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Inscription>>listInscription() {
        return ResponseEntity.ok(inscriptionService.getInscriptions());
    }

    @GetMapping("/by-date-time")
    public ResponseEntity<List<Inscription>> findByDateAndTime(@RequestParam String date, @RequestParam String time) {
        List<Inscription> inscriptions = inscriptionService.findByDateAndTime(date, time);
        return ResponseEntity.ok(inscriptions);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Inscription> updateInscription(@PathVariable Long id, @RequestBody Inscription inscription) {
        inscription.setIdInscription(id);
        inscriptionService.updateInscription(inscription);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Inscription> deleteInscription(@PathVariable Long id) {
        inscriptionService.deleteInscription(id);
        return ResponseEntity.noContent().build();
    }

}
