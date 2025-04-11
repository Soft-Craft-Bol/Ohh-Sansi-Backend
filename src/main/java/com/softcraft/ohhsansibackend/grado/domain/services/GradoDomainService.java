package com.softcraft.ohhsansibackend.grado.domain.services;

import com.softcraft.ohhsansibackend.grado.domain.models.Grade;
import com.softcraft.ohhsansibackend.grado.domain.repository.abstraction.INivelEscolarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NivelEscolarDomainService {
    private final INivelEscolarRepository INivelEscolarRepository;

    @Autowired
    public NivelEscolarDomainService(INivelEscolarRepository INivelEscolarRepository) {
        this.INivelEscolarRepository = INivelEscolarRepository;
    }

    public Grade createNivelEscolar(Grade grade) {
        return INivelEscolarRepository.save(grade);
    }

    public boolean updateNivelEscolar(Grade grade) {
        return INivelEscolarRepository.update(grade);
    }

    public boolean deleteNivelEscolar(int id) {
        return INivelEscolarRepository.delete(id);
    }

    public Grade findNivelEscolarById(int id) {
        return INivelEscolarRepository.findById(id);
    }

    public List<Grade> getNivelEscolars() {
        return INivelEscolarRepository.findAll();
    }
}
