package com.softcraft.ohhsansibackend.grado.application.ports;

import com.softcraft.ohhsansibackend.grado.domain.models.Grade;
import com.softcraft.ohhsansibackend.grado.domain.services.GradoDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NivelEscolarAdapter {
    private final GradoDomainService gradoDomainService;

    @Autowired
    public NivelEscolarAdapter(GradoDomainService gradoDomainService) {
        this.gradoDomainService = gradoDomainService;
    }

    public Grade saveNivelEscolar(Grade grade) {
        return gradoDomainService.createNivelEscolar(grade);
    }

    public boolean updateNivelEscolar(Grade grade) {
        return gradoDomainService.updateNivelEscolar(grade);
    }

    public boolean deleteNivelEscolar(int id) {
        return gradoDomainService.deleteNivelEscolar(id);
    }

    public Grade findNivelEscolarById(int id) {
        return gradoDomainService.findNivelEscolarById(id);
    }

    public List<Grade> getNivelEscolars() {
        return gradoDomainService.getNivelEscolars();
    }

}
