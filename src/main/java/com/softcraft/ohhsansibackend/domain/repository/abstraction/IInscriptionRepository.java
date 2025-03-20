package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.domain.models.Inscription;

import java.util.List;
import java.util.Optional;

public interface IInscriptionRepository {
    void saveInscription(Inscription inscription);

    void updateInscription(Inscription inscription);

    void deleteInscription(Long inscriptionId);

    Optional<Inscription> findByIdInscription(Long inscriptionId);

    List <Inscription> findAllInscription();

    List<Inscription> findByDateAndTime(String date, String time);
}
