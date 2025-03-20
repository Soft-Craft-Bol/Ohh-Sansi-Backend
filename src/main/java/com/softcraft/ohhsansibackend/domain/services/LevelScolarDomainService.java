package com.softcraft.ohhsansibackend.domain.services;

import com.softcraft.ohhsansibackend.domain.models.LevelScolar;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.LevelScolarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelScolarDomainService {
    private final LevelScolarRepository levelScolarRepository;

    @Autowired
    public LevelScolarDomainService(LevelScolarRepository levelScolarRepository) {
        this.levelScolarRepository = levelScolarRepository;
    }

    public void createLevelScolar(LevelScolar levelScolar) {
        levelScolarRepository.save(levelScolar);
    }

    public void updateLevelScolar(LevelScolar levelScolar) {
        levelScolarRepository.update(levelScolar);
    }

    public void deleteLevelScolar(Long id) {
        levelScolarRepository.delete(id);
    }

    public Optional<LevelScolar> findLevelScolarById(Long id) {
        return levelScolarRepository.findById(id);
    }

    public List<LevelScolar> getLevelScolar() {
        return levelScolarRepository.findAll();
    }
}
