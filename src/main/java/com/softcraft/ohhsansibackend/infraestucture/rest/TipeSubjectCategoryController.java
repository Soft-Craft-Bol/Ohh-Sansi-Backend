package com.softcraft.ohhsansibackend.infraestucture.rest;


import com.softcraft.ohhsansibackend.application.usecases.TipeSubjectCategoryService;
import com.softcraft.ohhsansibackend.domain.models.TipeSubjectCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tipe-subject-categories")
public class TipeSubjectCategoryController {
    private TipeSubjectCategoryService tipeSubjectCategoryService;

    @Autowired
    public TipeSubjectCategoryController(TipeSubjectCategoryService tipeSubjectCategoryService) {
        this.tipeSubjectCategoryService = tipeSubjectCategoryService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createTipeSubjectCategory(@RequestBody TipeSubjectCategory tipeSubjectCategory) {
        tipeSubjectCategoryService.saveTipeSubjectCategory(tipeSubjectCategory);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Tipe Subject Category created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipeSubjectCategory> getTipeSubjectCategoryById(@PathVariable Long id) {
        TipeSubjectCategory tipeSubjectCategory = tipeSubjectCategoryService.findTipeSubjectCategoryById(id);
        return ResponseEntity.ok(tipeSubjectCategory);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getTipeSubjectCategories() {
        return ResponseEntity.ok(Map.of("tipeSubjectCategories", tipeSubjectCategoryService.getTipeSubjectCategories()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTipeSubjectCategory(@PathVariable Long id, @RequestBody TipeSubjectCategory tipeSubjectCategory) {
        tipeSubjectCategory.setIdTipeSubjectCategory(id);
        tipeSubjectCategoryService.updateTipeSubjectCategory(tipeSubjectCategory);
        return ResponseEntity.ok(Map.of("message", "Tipe Subject Category updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteTipeSubjectCategory(@PathVariable Long id) {
        tipeSubjectCategoryService.deleteTipeSubjectCategory(id);
        return ResponseEntity.ok(Map.of("message", "Tipe Subject Category deleted successfully"));
    }

}
