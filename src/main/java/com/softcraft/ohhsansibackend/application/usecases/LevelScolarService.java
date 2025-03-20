package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.domain.models.LevelScolar;
import com.softcraft.ohhsansibackend.domain.services.LevelScolarDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelScolarService {
    private final LevelScolarDomainService levelScolarDomainService;

    public LevelScolarService(LevelScolarDomainService levelScolarDomainService) {
        this.levelScolarDomainService = levelScolarDomainService;
    }

    public void saveLevelScolar(LevelScolar levelScolar) {
        levelScolarDomainService.createLevelScolar(levelScolar);
    }

    public void updateLevelScolar(LevelScolar levelScolar) {
        levelScolarDomainService.updateLevelScolar(levelScolar);
    }

    public void deleteLevelScolar(Long id) {
        levelScolarDomainService.deleteLevelScolar(id);
    }

    public Optional<LevelScolar> findLevelScolarById(Long id) {
        return levelScolarDomainService.findLevelScolarById(id);
    }

    public List<LevelScolar> getLevels() {
        return levelScolarDomainService.getLevelScolar();
    }
}
