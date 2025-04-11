package com.softcraft.ohhsansibackend.fechasolimpiada.application.ports;

import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.FechaOlimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.services.FechaOlimpiadaDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class FechaOlimpiadaAdapter {
    private final FechaOlimpiadaDomainService fechaOlimpiadaDomainService;

    @Autowired
    public FechaOlimpiadaAdapter(FechaOlimpiadaDomainService fechaOlimpiadaDomainService) {
        this.fechaOlimpiadaDomainService = fechaOlimpiadaDomainService;
    }

    public FechaOlimpiada upsertFechaOlimpiada(FechaOlimpiada fechaOlimpiada) {
        return fechaOlimpiadaDomainService.upsertFechaOlimpiada(fechaOlimpiada);
    }

    public boolean deleteFechaOlimpiada(int id) {
        return fechaOlimpiadaDomainService.deleteFechaOlimpiada(id);
    }

    public List<FechaOlimpiada> getFechaOlimpiada() {
        return fechaOlimpiadaDomainService.getFechaOlimpiada();
    }

    public FechaOlimpiada getFechaOlimpiadaById(int idFechaOlimpiada) {
        return fechaOlimpiadaDomainService.getFechaOlimpiadaById(idFechaOlimpiada);
    }

    public FechaOlimpiada getFechaOlimpiadaPublic() {
        return fechaOlimpiadaDomainService.getFechaOlimpiadaPublic();
    }
}
