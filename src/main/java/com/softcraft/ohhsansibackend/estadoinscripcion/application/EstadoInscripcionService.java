package com.softcraft.ohhsansibackend.estadoinscripcion.application;

import com.softcraft.ohhsansibackend.estadoinscripcion.domain.EstadoInscripcionDomainRepository;
import com.softcraft.ohhsansibackend.exception.ParticipanteNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.application.ports.InscripcionAdapter;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EstadoInscripcionService {
    private final EstadoInscripcionDomainRepository estadoInscripcionDomainRepository;
    private final ParticipanteService participanteService;
    private final TutorService tutorService;
    private final InscripcionAdapter inscripcionAdapter;
    @Autowired
    public EstadoInscripcionService(EstadoInscripcionDomainRepository estadoInscripcionDomainRepository, ParticipanteService participanteService, TutorService tutorService, InscripcionAdapter inscripcionAdapter) {
        this.estadoInscripcionDomainRepository = estadoInscripcionDomainRepository;
        this.participanteService = participanteService;
        this.tutorService = tutorService;
        this.inscripcionAdapter = inscripcionAdapter;
    }
    public Map<String, Object> verificarExistenciaDeTutores(int ciParticipante) {
        Map<String, Object> response = new HashMap<>();
        int cantRegistros = estadoInscripcionDomainRepository.countParticipanteByCarnetIdentidad(ciParticipante);
        System.out.println("Cantidad de registros: " + cantRegistros);

        Participante participante = getParticipanteByCarnetIdentidad(ciParticipante);
        if (participante != null) {
            response.put("RegistroDatosParticipante",
                    Map.of(
                            "estado", "Completado",
                            "fechaRegistro", "No seteado hacer querys",
                            "comentarios", "Datos del participante registrados correctamente.",
                            "participante", participante
                    ));
        } else {
            throw new ParticipanteNotFoundException("No se encontraron datos del participante");
        }

        if (cantRegistros > 0) {
            response.put("RegistroDatosTutor",
                    Map.of(
                            "estado", "Completado",
                            "fechaRegistro", "No seteado hacer querys",
                            "comentarios", "Datos del tutor registrados correctamente.",
                            "tutores", tutorService.findTutorsByCarnetParticipante(ciParticipante)
                    ));
        } else {
            if (inscripcionAdapter.calculateEdad(participante) < 15) {
                response.put("RegistroDatosTutor",
                        Map.of(
                                "estado", "No Completado",
                                "fechaRegistro", "No seteado hacer querys",
                                "comentarios", "No se encontraron tutores registrados para el participante.",
                                "tutores", "No existen tutores registrados"
                        ));
            } else {
                response.put("RegistroDatosTutor",
                        Map.of(
                                "estado", "No Completado",
                                "fechaRegistro", "No seteado hacer querys",
                                "comentarios", "No es necesario registrar tutores.",
                                "tutores", "No existen tutores registrados"
                        ));
            }
        }
        return response;
    }

    public Participante getParticipanteByCarnetIdentidad(int ciParticipante){
        return participanteService.findByCarnetIdentidadService(ciParticipante);
    }


}
