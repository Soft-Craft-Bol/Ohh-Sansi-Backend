package com.softcraft.ohhsansibackend.domain.services;


import com.softcraft.ohhsansibackend.domain.repository.CategoryDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryDomainService {
    private final CategoryDomainRepository categoriaDomainRepository;

    @Autowired
    public CategoryDomainService(CategoryDomainRepository categoryDomainRepository) {
        this.categoriaDomainRepository = categoryDomainRepository;
    }
}
