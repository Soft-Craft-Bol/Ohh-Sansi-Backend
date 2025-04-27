package com.softcraft.ohhsansibackend.fechasolimpiada.domain.services;

import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.Olimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.repository.abstraction.IOlimpiadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OlimpiadaDomainService {
    private final IOlimpiadaRepository olimpiadaRepository;

    @Autowired
    public OlimpiadaDomainService(IOlimpiadaRepository olimpiadaRepository) {
        this.olimpiadaRepository = olimpiadaRepository;
    }

    public Olimpiada saveOlimpiada(Olimpiada olimpiada) {
        return olimpiadaRepository.saveOlimpiada(olimpiada);
    }

    public boolean deleteOlimpiada(int idOlimpiada) {
        return olimpiadaRepository.deleteOlimpiada(idOlimpiada);
    }

    public Olimpiada getOlimpiada(int idOlimpiada) {
        return olimpiadaRepository.getOlimpiada(idOlimpiada);
    }

    public Olimpiada getOlimpiadaPublic() {
        return olimpiadaRepository.getOlimpiadaPublic();
    }

    public List<Olimpiada> getAllOlimpiadas() {
        return olimpiadaRepository.getOlimpiadas();
    }

    public boolean updatePrecioOlimpiada(int idOlimpiada, BigDecimal precioOlimpiada) {
        return olimpiadaRepository.updatePrecioOlimpiada(idOlimpiada, precioOlimpiada);
    }

}
