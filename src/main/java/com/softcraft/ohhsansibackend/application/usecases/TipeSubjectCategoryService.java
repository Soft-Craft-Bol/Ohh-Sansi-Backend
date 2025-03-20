package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.domain.models.TipeSubjectCategory;
import com.softcraft.ohhsansibackend.domain.services.TipeSubjectCategoryDomainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipeSubjectCategoryService {
    private final TipeSubjectCategoryDomainService tipeSubjectCategoryDomainService;

    public TipeSubjectCategoryService(TipeSubjectCategoryDomainService tipeSubjectCategoryDomainService) {
        this.tipeSubjectCategoryDomainService = tipeSubjectCategoryDomainService;

    }

    public void saveTipeSubjectCategory(TipeSubjectCategory tipeSubjectCategory) {
        tipeSubjectCategoryDomainService.createTipeSubjectCategory(tipeSubjectCategory);
    }

    public void updateTipeSubjectCategory(TipeSubjectCategory tipeSubjectCategory) {
        tipeSubjectCategoryDomainService.updateTipeSubjectCategory(tipeSubjectCategory);
    }

    public void deleteTipeSubjectCategory(Long id) {
        tipeSubjectCategoryDomainService.deleteTipeSubjectCategory(id);
    }

    public TipeSubjectCategory findTipeSubjectCategoryById(Long id) {
        return tipeSubjectCategoryDomainService.findTipeSubjectCategoryById(id).orElse(null);
    }

    public List<TipeSubjectCategory> getTipeSubjectCategories() {
        return tipeSubjectCategoryDomainService.getTipeSubjectCategories();
    }
}
