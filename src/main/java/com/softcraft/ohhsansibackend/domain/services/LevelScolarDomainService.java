package com.softcraft.ohhsansibackend.domain.services;

import com.softcraft.ohhsansibackend.domain.models.LevelScolar;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.ILevelScolarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelScolarDomainService {
    private final ILevelScolarRepository ILevelScolarRepository;

    @Autowired
    public LevelScolarDomainService(ILevelScolarRepository ILevelScolarRepository) {
        this.ILevelScolarRepository = ILevelScolarRepository;
    }

    public void createLevelScolar(LevelScolar levelScolar) {
        ILevelScolarRepository.save(levelScolar);
    }

    public void updateLevelScolar(LevelScolar levelScolar) {
        ILevelScolarRepository.update(levelScolar);
    }

    public void deleteLevelScolar(Long id) {
        ILevelScolarRepository.delete(id);
    }

    public Optional<LevelScolar> findLevelScolarById(Long id) {
        return ILevelScolarRepository.findById(id);
    }

    public List<LevelScolar> getLevelScolar() {
        return ILevelScolarRepository.findAll();
    }
}
