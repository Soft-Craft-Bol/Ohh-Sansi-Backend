package com.softcraft.ohhsansibackend.application.ports;

import com.softcraft.ohhsansibackend.domain.models.Inscription;
import com.softcraft.ohhsansibackend.domain.services.CategoryDomainService;
import com.softcraft.ohhsansibackend.domain.services.InscriptionDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InscriptionAdapter {
    private final InscriptionDomainService inscriptionDomainService;

    @Autowired
    public InscriptionAdapter(InscriptionDomainService inscriptionDomainService) {
        this.inscriptionDomainService = inscriptionDomainService;
    }

    public void saveInscription(Inscription inscription) {
        inscriptionDomainService.createInscription(inscription);
    }

    public void updateInscription(Inscription inscription) {
        inscriptionDomainService.updateInscription(inscription);
    }

    public void deleteInscription(Long id) {
        inscriptionDomainService.deleteInscription(id);
    }

    public Optional<Inscription> findInscriptionById(Long id) {
        return inscriptionDomainService.getInscription(id);
    }

    public List<Inscription> findAllInscriptions() {
        return inscriptionDomainService.listInscription();
    }

    public List<Inscription> findByDateAndTime(String date, String time) {
        return inscriptionDomainService.findByDateAndTime(date, time);
    }
}
