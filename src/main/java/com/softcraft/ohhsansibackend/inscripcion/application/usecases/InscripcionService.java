package com.softcraft.ohhsansibackend.inscripcion.application.usecases;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.application.ports.InscripcionAdapter;
import com.softcraft.ohhsansibackend.inscripcion.domain.repository.implementation.InscripcionDomainRepository;
import com.softcraft.ohhsansibackend.inscripcion.domain.services.InscripcionDomainService;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.utils.UniqueCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Service
public class InscripcionService {
    private final InscripcionAdapter inscripcionAdapter;
    private final InscripcionDomainService inscripcionDomainService;
    private final UniqueCodeGenerator uniqueCodeGenerator;
    private final InscripcionDomainRepository inscripcionDomainRepository;

    @Autowired
    public InscripcionService(InscripcionAdapter inscripcionAdapter, InscripcionDomainService inscripcionDomainService, UniqueCodeGenerator uniqueCodeGenerator, InscripcionDomainRepository inscripcionDomainRepository) {
        this.inscripcionAdapter = inscripcionAdapter;
        this.inscripcionDomainService = inscripcionDomainService;
        this.uniqueCodeGenerator = uniqueCodeGenerator;
        this.inscripcionDomainRepository = inscripcionDomainRepository;
    }

    public Inscripcion saveInscripcion() {
        try {
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setFechaInscripcion(Date.valueOf(LocalDate.now()));
            inscripcion.setHoraInscripcion(Time.valueOf(LocalTime.now()));
            String uniqueCode = uniqueCodeGenerator.generate();
            inscripcion.setCodigoUnicoInscripcion(uniqueCode);
            return inscripcionAdapter.saveInscripcion(inscripcion);
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar la inscripción: " + e.getMessage());
        }
    }

    public Inscripcion findInscripcionById(int id) {
        Inscripcion inscripcion = inscripcionAdapter.findInscripcionById(id);
        if (inscripcion == null) {
            throw new ResourceNotFoundException("Inscripcion con ID " + id + " no encontrada");
        }
        return inscripcion;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripcionAdapter.findAllInscripciones();
    }


    public int createInscripcionAndReturnId(Inscripcion inscripcion) {
        inscripcion.setFechaInscripcion(Date.valueOf(LocalDate.now()));
        inscripcion.setHoraInscripcion(Time.valueOf(LocalTime.now()));
        Inscripcion savedInscripcion = inscripcionAdapter.saveInscripcion(inscripcion);
        return savedInscripcion.getIdInscripcion();
    }
    //codigo
    public Long findIdByCodigoUnico(String codigoUnico) {
        return inscripcionAdapter.findIdByCodigoUnico(codigoUnico);
    }
    //nuevos sql
    public List<Map<String, Object>> getInscripcionById(int idInscripcion) {
        return inscripcionDomainService.getInscripcionById(idInscripcion);
    }

    public List<Map<String, Object>> getParticipantesByInscripcionId(int idInscripcion) {
        return inscripcionDomainService.getParticipantesByInscripcionId(idInscripcion);
    }

    public List<Map<String, Object>> getInscripcionAreasByInscripcionId(int idInscripcion) {
        return inscripcionDomainService.getInscripcionAreasByInscripcionId(idInscripcion);
    }

    public List<Map<String, Object>> getAreasByInscripcionId(int idInscripcion) {
        return inscripcionDomainService.getAreasByInscripcionId(idInscripcion);
    }

    public List<Map<String, Object>> getTutoresByInscripcionId(int idInscripcion) {
        return inscripcionDomainService.getTutoresByInscripcionId(idInscripcion);
    }
    public Map<String, Object> getInscripcionDetails(String codigoUnico) {
        int idInscripcion = findIdByCodigoUnico(codigoUnico).intValue();
        Participante participante = inscripcionAdapter.findParticipanteByIdInscripcion(idInscripcion);
        int edadParticipante = inscripcionAdapter.calculateEdad(participante);
        List<Map<String,Object>> areas = getAreasByInscripcionId(idInscripcion);
        if (areas.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron áreas para la inscripción con código único: " + codigoUnico);
        }
        List<Map<String,Object>> tutores = getTutoresByInscripcionId(idInscripcion);
        if(tutores.isEmpty() && edadParticipante<15){
            throw new ResourceNotFoundException("No se puede generar la orden de pago, necesitas al menos un tutor para terminar el registro, edad menor a 15 años: " + codigoUnico);
        }

        return Map.of(
                "inscripcion", getInscripcionById(idInscripcion),
                "participantes", getParticipantesByInscripcionId(idInscripcion),
                "areas", areas,
                "tutores", tutores,
                "olimpiadas", inscripcionDomainRepository.findOlimapiada(),
                "edadParticipante", edadParticipante
        );
    }


}
