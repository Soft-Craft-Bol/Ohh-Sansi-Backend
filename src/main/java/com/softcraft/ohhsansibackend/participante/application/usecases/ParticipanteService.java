package com.softcraft.ohhsansibackend.participante.application.usecases;

import com.softcraft.ohhsansibackend.exception.DuplicateResourceException;
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
        try {
            Participante participante = participanteAdapter.findById(id);
            response.put("participante", participante);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al obtener el participante");
        }
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

    public Map<String, Object> findByEmail(String email){
        try{
            Map<String, Object> response = new HashMap<>();
            response.put("participante", participanteAdapter.findByEmail(email));
            return response;
        }catch (Exception e){
            throw new RuntimeException("Error al obtener el participante");
        }
    }
    public Map<String, Object> findByCarnetIdentidad(int carnetIdentidad){
        try{
            Map<String, Object> response = new HashMap<>();
            response.put("participante", participanteAdapter.findByCarnetIdentidad(carnetIdentidad));
            return response;
        }catch (Exception e){
            throw new RuntimeException("Error al obtener el participante");
        }
    }
}