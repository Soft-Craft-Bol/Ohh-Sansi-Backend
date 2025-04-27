package com.softcraft.ohhsansibackend.fechasolimpiada.application.ports;

import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.Olimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.services.OlimpiadaDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

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

    public boolean deleteOlimpiada(int id) {
        return olimpiadaDomainService.deleteOlimpiada(id);
    }

    public Olimpiada getOlimpiadaPublic() {
        return olimpiadaDomainService.getOlimpiadaPublic();
    }

    public Olimpiada getOlimpiadaById(int id) {
        return olimpiadaDomainService.getOlimpiada(id);
    }

    public List<Olimpiada> getAllOlimpiadas() {
        return olimpiadaDomainService.getAllOlimpiadas();
    }

    public boolean updatePrecioOlimpiada(int idOlimpiada, BigDecimal precioOlimpiada) {
        return olimpiadaDomainService.updatePrecioOlimpiada(idOlimpiada, precioOlimpiada);
    }

}
