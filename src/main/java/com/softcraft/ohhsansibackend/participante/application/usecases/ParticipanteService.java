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
    private MailService mailService;
    @Autowired
    public ParticipanteService(ParticipanteAdapter participanteAdapter, InscripcionService inscripcionService) {
        this.participanteAdapter = participanteAdapter;
        this.inscripcionService = inscripcionService;
    }
    public Map<String, Object> save(Participante participante) {
        Inscripcion inscripcion = createInscripcion();
        try {
            participante.setIdInscripcion(inscripcion.getIdInscripcion());
            String codUnique = inscripcion.getCodigoUnicoInscripcion();
            String destinatario = participante.getEmailParticipante();
            participanteAdapter.save(participante);
            try {
                mailService.sendEmail(destinatario, codUnique);
            } catch (MessagingException e) {
                throw new RuntimeException("Error al enviar el correo de confirmación", e);
            }        } catch (DuplicateKeyException e) {
            try {
                inscripcionService.deleteInscripcionById(inscripcion.getIdInscripcion());
            } catch (RuntimeException ex) {
                throw new RuntimeException("Error al eliminar la inscripción del participante");
            }
            throw new DuplicateResourceException("Email o carnet de identidad del participante ya registrados");
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
            throw new ResourceNotFoundException("Participante no encontrado");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("participante", participante);
        return response;
    }

    public Participante findByCarnetIdentidadService(int carnetIdentidad) {
        Participante participante = participanteAdapter.findByCarnetIdentidad(carnetIdentidad);
        if (participante == null) {
            throw new ParticipanteNotFoundException("Participante no encontrado");
        }
        return participante;
    }
    public Participante findParticipanteByIdInscripcion(int idInscripcion) {
        return participanteAdapter.findParticipanteByIdInscripcion(idInscripcion);
    }

}