package com.softcraft.ohhsansibackend.domain.services;

import com.softcraft.ohhsansibackend.domain.models.Inscription;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.IInscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscriptionDomainService {
    private final IInscriptionRepository inscriptionRepository;

    @Autowired
    public InscriptionDomainService(IInscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public void createInscription(Inscription inscription) {
        inscriptionRepository.saveInscription(inscription);
    }

    public Optional<Inscription> getInscription(Long id) {
        return inscriptionRepository.findByIdInscription(id);
    }

    public List<Inscription> listInscription() {
        return inscriptionRepository.findAllInscription();
    }

    public void updateInscription(Inscription inscription) {
        inscriptionRepository.updateInscription(inscription);
    }

    public void deleteInscription(Long id) {
        inscriptionRepository.deleteInscription(id);
    }

    public List<Inscription> findByDateAndTime(String date, String time) {
        return inscriptionRepository.findByDateAndTime(date, time);
    }
}
