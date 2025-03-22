package com.softcraft.ohhsansibackend.nivelescolar.domain.services;

import com.softcraft.ohhsansibackend.nivelescolar.domain.models.NivelEscolar;
import com.softcraft.ohhsansibackend.nivelescolar.domain.repository.abstraction.INivelEscolarRepository;
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

    public NivelEscolar createNivelEscolar(NivelEscolar nivelEscolar) {
        return INivelEscolarRepository.save(nivelEscolar);
    }

    public boolean updateNivelEscolar(NivelEscolar nivelEscolar) {
        return INivelEscolarRepository.update(nivelEscolar);
    }

    public boolean deleteNivelEscolar(int id) {
        return INivelEscolarRepository.delete(id);
    }

    public NivelEscolar findNivelEscolarById(int id) {
        return INivelEscolarRepository.findById(id);
    }

    public List<NivelEscolar> getNivelEscolars() {
        return INivelEscolarRepository.findAll();
    }
}
