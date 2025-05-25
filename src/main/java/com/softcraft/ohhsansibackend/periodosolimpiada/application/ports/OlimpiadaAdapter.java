package com.softcraft.ohhsansibackend.periodosolimpiada.application.ports;

import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.Olimpiada;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.services.OlimpiadaDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class OlimpiadaAdapter {
    private final OlimpiadaDomainService olimpiadaDomainService;

    @Autowired
    public OlimpiadaAdapter(OlimpiadaDomainService olimpiadaDomainService) {
        this.olimpiadaDomainService = olimpiadaDomainService;
    }

    public Olimpiada saveOlimpiada(Olimpiada olimpiada) {
        return olimpiadaDomainService.saveOlimpiada(olimpiada);
    }

    public Olimpiada updateOlimpiada(Olimpiada olimpiada) {
        return olimpiadaDomainService.updateOlimpiada(olimpiada);
    }

    public List<Olimpiada> getAllOlimpiadas() {
        return olimpiadaDomainService.getAllOlimpiadas();
    }

    public Optional<Olimpiada> findById(int idOlimpiada) {
        return olimpiadaDomainService.findById(idOlimpiada);
    }

    public Olimpiada findOlimpiadaById(int idOlimpiada){
        return olimpiadaDomainService.findOlimpiadaById(idOlimpiada);
    }
}
