package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.application.ports.InscriptionAdapter;
import com.softcraft.ohhsansibackend.domain.models.Inscription;
import com.softcraft.ohhsansibackend.domain.services.InscriptionDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {
    private final InscriptionAdapter inscriptionAdapter;

    public InscriptionService(InscriptionAdapter inscriptionAdapter) {
        this.inscriptionAdapter = inscriptionAdapter;
    }

    public void saveInscription(Inscription inscription) {
        inscriptionAdapter.saveInscription(inscription);
    }

    public Optional<Inscription> findInscriptionById(Long id) {
        return inscriptionAdapter.findInscriptionById(id);
    }

    public List<Inscription> getInscriptions() {
        return inscriptionAdapter.findAllInscriptions();
    }

    public List<Inscription> findByDateAndTime(String date, String time) {
        return inscriptionAdapter.findByDateAndTime(date, time);
    }

    public void updateInscription(Inscription inscription) {
        inscriptionAdapter.updateInscription(inscription);
    }

    public void deleteInscription(Long id) {
        inscriptionAdapter.deleteInscription(id);
    }


}
