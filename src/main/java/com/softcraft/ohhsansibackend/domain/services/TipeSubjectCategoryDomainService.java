package com.softcraft.ohhsansibackend.domain.services;

import com.softcraft.ohhsansibackend.domain.models.TipeSubjectCategory;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.TipeSubjectCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipeSubjectCategoryDomainService {
    private final TipeSubjectCategoryRepository tipeSubjectCategoryRepository;

    @Autowired
    public TipeSubjectCategoryDomainService(TipeSubjectCategoryRepository tipeSubjectCategoryRepository) {
        this.tipeSubjectCategoryRepository = tipeSubjectCategoryRepository;
    }

    public void createTipeSubjectCategory(TipeSubjectCategory tipeSubjectCategory) {
        tipeSubjectCategoryRepository.save(tipeSubjectCategory);
    }

    public void updateTipeSubjectCategory(TipeSubjectCategory tipeSubjectCategory) {
        tipeSubjectCategoryRepository.update(tipeSubjectCategory);
    }

    public void deleteTipeSubjectCategory(Long id) {
        tipeSubjectCategoryRepository.delete(id);
    }

    public Optional<TipeSubjectCategory> findTipeSubjectCategoryById(Long id) {
        return tipeSubjectCategoryRepository.findById(id);
    }

    public List<TipeSubjectCategory> getTipeSubjectCategories() {
        return tipeSubjectCategoryRepository.findAll();
    }
}
