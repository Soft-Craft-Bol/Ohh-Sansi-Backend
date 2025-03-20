package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.application.ports.LevelScolarAdapter;
import com.softcraft.ohhsansibackend.domain.models.LevelScolar;
import com.softcraft.ohhsansibackend.domain.services.LevelScolarDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelScolarService {
    private final LevelScolarAdapter levelScolarAdapter;

    public LevelScolarService(LevelScolarAdapter levelScolarAdapter) {
        this.levelScolarAdapter = levelScolarAdapter;
    }

    public void saveLevelScolar(LevelScolar levelScolar) {
        levelScolarAdapter.saveLevelScolar(levelScolar);
    }

    public Optional<LevelScolar> findLevelScolarById(Long id) {
        return levelScolarAdapter.findLevelScolarById(id);
    }

    public List<LevelScolar> getLevelScolars() {
        return levelScolarAdapter.getLevels();
    }

    public void updateLevelScolar(LevelScolar levelScolar) {
        levelScolarAdapter.updateLevelScolar(levelScolar);
    }

    public void deleteLevelScolar(Long id) {
        levelScolarAdapter.deleteLevelScolar(id);
    }
}
