package com.softcraft.ohhsansibackend.infraestucture.rest;

import com.softcraft.ohhsansibackend.application.usecases.LevelScolarService;
import com.softcraft.ohhsansibackend.domain.models.LevelScolar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/levelscolar")
public class LevelScolarController {
    private final LevelScolarService levelScolarService;

    @Autowired
    public LevelScolarController(LevelScolarService levelScolarService) {
        this.levelScolarService = levelScolarService;
    }

    @PostMapping
    public ResponseEntity<LevelScolar> addLevelScolar(@RequestBody LevelScolar levelScolar) {
        levelScolarService.saveLevelScolar(levelScolar);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LevelScolar> updateLevelScolar(@PathVariable Long id, @RequestBody LevelScolar levelScolar) {
        levelScolar.setIdLevel(id);
        levelScolarService.updateLevelScolar(levelScolar);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LevelScolar> deleteLevelScolar(@PathVariable Long id) {
        levelScolarService.deleteLevelScolar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LevelScolar> getLevelById(@PathVariable Long id) {
        return levelScolarService.findLevelScolarById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<LevelScolar>> getLevelScolar() {
        return ResponseEntity.ok(levelScolarService.getLevelScolars());
    }

}
