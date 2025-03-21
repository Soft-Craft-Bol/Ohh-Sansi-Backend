package com.softcraft.ohhsansibackend.application.ports;

import com.softcraft.ohhsansibackend.domain.models.NivelEscolar;
import com.softcraft.ohhsansibackend.domain.services.NivelEscolarDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class NivelEscolarAdapter {
    private final NivelEscolarDomainService nivelEscolarDomainService;

    @Autowired
    public NivelEscolarAdapter(NivelEscolarDomainService nivelEscolarDomainService) {
        this.nivelEscolarDomainService = nivelEscolarDomainService;
    }

    public NivelEscolar saveNivelEscolar(NivelEscolar nivelEscolar) {
        return nivelEscolarDomainService.createNivelEscolar(nivelEscolar);
    }

    public boolean updateNivelEscolar(NivelEscolar nivelEscolar) {
        return nivelEscolarDomainService.updateNivelEscolar(nivelEscolar);
    }

    public boolean deleteNivelEscolar(int id) {
        return nivelEscolarDomainService.deleteNivelEscolar(id);
    }

    public NivelEscolar findNivelEscolarById(int id) {
        return nivelEscolarDomainService.findNivelEscolarById(id);
    }

    public List<NivelEscolar> getNivelEscolars() {
        return nivelEscolarDomainService.getNivelEscolars();
    }

}
