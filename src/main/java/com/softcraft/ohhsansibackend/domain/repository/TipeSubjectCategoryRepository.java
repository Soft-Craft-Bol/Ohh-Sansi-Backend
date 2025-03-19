package com.softcraft.ohhsansibackend.domain.repository;

import com.softcraft.ohhsansibackend.domain.models.TipeSubjectCategory;

import java.util.List;
import java.util.Optional;

public interface TipeSubjectCategoryRepository {
    void save(TipeSubjectCategory tipeSubjectCategory);
    void update(TipeSubjectCategory tipeSubjectCategory);
    void delete(Long idTipeSubjectCategory);
    Optional<TipeSubjectCategory> findById(Long idTipeSubjectCategory);
    List<TipeSubjectCategory> findAll();
}
