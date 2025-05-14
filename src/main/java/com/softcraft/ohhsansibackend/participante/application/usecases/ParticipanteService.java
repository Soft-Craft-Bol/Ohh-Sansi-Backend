package com.softcraft.ohhsansibackend.participante.application.usecases;

import com.softcraft.ohhsansibackend.exception.DuplicateResourceException;
import com.softcraft.ohhsansibackend.exception.ParticipanteNotFoundException;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.application.usecases.InscripcionService;
import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.mail.service.MailService;
import com.softcraft.ohhsansibackend.participante.application.ports.ParticipanteAdapter;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.softcraft.ohhsansibackend.handler.GlobalExceptionHandler;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ParticipanteService {
    private final ParticipanteAdapter participanteAdapter;
    private final InscripcionService inscripcionService;
    private final MailService mailService;
    @Autowired
    public ParticipanteService(ParticipanteAdapter participanteAdapter, InscripcionService inscripcionService, MailService mailService) {
        this.participanteAdapter = participanteAdapter;
        this.inscripcionService = inscripcionService;
        this.mailService = mailService;
    }
    public Map<String, Object> save(Participante participante) {
        Inscripcion inscripcion = createInscripcion();
        try {
            participante.setIdInscripcion(inscripcion.getIdInscripcion());
            participanteAdapter.save(participante);
        } catch (DuplicateKeyException e) {
            try {
                inscripcionService.deleteInscripcionById(inscripcion.getIdInscripcion());
            } catch (RuntimeException ex) {
                throw new RuntimeException("Error al eliminar la inscripción del participante");
            }
            throw new DuplicateResourceException("Carnet de identidad del participante ya registrado");
        }

        try {
            mailService.sendEmailAsync(participante.getEmailParticipante(), participante.getParticipanteHash());

        } catch (MessagingException e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Participante registrado exitosamente");
        return response;
    }
    private Inscripcion createInscripcion() {
        return inscripcionService.saveInscripcion();
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
            throw new IllegalArgumentException("Participante no encontrado");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("participante", participante);
        return response;
    }

    public Participante findByCarnetIdentidadService(int carnetIdentidad) {
        Participante participante = participanteAdapter.findByCarnetIdentidad(carnetIdentidad);
        if (participante == null) {
            throw new IllegalArgumentException("Participante no encontrado");
        }
        return participante;
    }
    public Participante findParticipanteByIdInscripcion(int idInscripcion) {
        return participanteAdapter.findParticipanteByIdInscripcion(idInscripcion);
    }

}