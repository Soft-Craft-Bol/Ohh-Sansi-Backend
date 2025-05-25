package com.softcraft.ohhsansibackend.periodosolimpiada.domain.services;

import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.Olimpiada;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.repository.abstraction.IOlimpiadaRepository;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.repository.implementation.OlimpiadaDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OlimpiadaDomainService {
    private final IOlimpiadaRepository olimpiadaRepository;
    private final OlimpiadaDomainRepository olimpiadaDomainRepository;

    @Autowired
    public OlimpiadaDomainService(IOlimpiadaRepository olimpiadaRepository, OlimpiadaDomainRepository olimpiadaDomainRepository) {
        this.olimpiadaRepository = olimpiadaRepository;
        this.olimpiadaDomainRepository = olimpiadaDomainRepository;
    }

    public Olimpiada saveOlimpiada(Olimpiada olimpiada) {
        return olimpiadaRepository.saveOlimpiada(olimpiada);
    }

    public Olimpiada updateOlimpiada(Olimpiada olimpiada) {
        return olimpiadaRepository.updateOlimpiada(olimpiada);
    }

    public List<Olimpiada> getAllOlimpiadas() {
        return olimpiadaRepository.getOlimpiadas();
    }

    public Optional<Olimpiada> findById(int idOlimpiada) {
        return olimpiadaRepository.findById(idOlimpiada);
    }

    public Olimpiada findOlimpiadaById(int idOlimpiada) {
        return olimpiadaDomainRepository.findOlimpiadaById(idOlimpiada);
    }
}
