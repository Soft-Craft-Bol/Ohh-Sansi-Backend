package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.application.exception.DuplicateResourceException;
import com.softcraft.ohhsansibackend.application.ports.ParticipanteAdapter;
import com.softcraft.ohhsansibackend.domain.models.Participante;
import com.softcraft.ohhsansibackend.domain.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ParticipanteService {
    private final ParticipanteAdapter participanteAdapter;
    @Autowired
    public ParticipanteService(ParticipanteAdapter participanteAdapter) {
        this.participanteAdapter = participanteAdapter;
    }
    public java.util.Map<String, Object> save(Participante participante) {
        try {
            participanteAdapter.save(participante);
        } catch (DuplicateKeyException e) {
            throw new DuplicateResourceException("Email o carnet de identidad del participante ya registrados");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Participante registrado exitosamente");
        return response;
    }
}
