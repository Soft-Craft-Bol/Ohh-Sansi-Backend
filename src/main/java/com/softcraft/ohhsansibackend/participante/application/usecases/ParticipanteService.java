package com.softcraft.ohhsansibackend.participante.application.usecases;

import com.softcraft.ohhsansibackend.exception.DuplicateResourceException;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.participante.application.ports.ParticipanteAdapter;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
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
    public Map<String, Object> save(Participante participante) {
        try {
            participanteAdapter.save(participante);
        } catch (DuplicateKeyException e) {
            throw new DuplicateResourceException("Email o carnet de identidad del participante ya registrados");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Participante registrado exitosamente");
        return response;
    }
    public Map<String, Object> findById(Long id) {
        Map<String, Object> response = new HashMap<>();
        Participante participante = participanteAdapter.findById(id);
        if (participante == null) {
            throw new ResourceNotFoundException("Participante no encontrado");
        }
        response.put("participante", participante);
        return response;
    }

    public Map<String,Object> findAll(){
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("participantes", participanteAdapter.findAll());
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de participantes");
        }
    }

    public Map<String, Object> findByEmail(String email) {
        Participante participante = participanteAdapter.findByEmail(email);
        if (participante == null) {
            throw new ResourceNotFoundException("Participante no encontrado");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("participante", participante);
        return response;
    }

    public Map<String, Object> findByCarnetIdentidad(int carnetIdentidad) {
        Participante participante = participanteAdapter.findByCarnetIdentidad(carnetIdentidad);
        if (participante == null) {
            throw new ResourceNotFoundException("Participante no encontrado");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("participante", participante);
        return response;
    }
}