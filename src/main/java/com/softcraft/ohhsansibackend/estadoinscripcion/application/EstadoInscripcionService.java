package com.softcraft.ohhsansibackend.estadoinscripcion.application;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.CatalogoService;
import com.softcraft.ohhsansibackend.estadoinscripcion.domain.EstadoInscripcionDomainRepository;
import com.softcraft.ohhsansibackend.exception.ParticipanteNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.application.ports.InscripcionAdapter;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.catalog.Catalog;
import java.util.HashMap;
import java.util.Map;

@Service
public class EstadoInscripcionService {
    private final EstadoInscripcionDomainRepository estadoInscripcionDomainRepository;
    private final ParticipanteService participanteService;
    private final TutorService tutorService;
    private final InscripcionAdapter inscripcionAdapter;
    private final CatalogoService catalogoService;
    @Autowired
    public EstadoInscripcionService(EstadoInscripcionDomainRepository estadoInscripcionDomainRepository,
                                    ParticipanteService participanteService,
                                    TutorService tutorService,
                                    InscripcionAdapter inscripcionAdapter,
                                    CatalogoService catalogoService
    ) {
        this.estadoInscripcionDomainRepository = estadoInscripcionDomainRepository;
        this.participanteService = participanteService;
        this.tutorService = tutorService;
        this.inscripcionAdapter = inscripcionAdapter;
        this.catalogoService = catalogoService;
    }
    public Map<String, Object> verificarExistenciaDeTutores(int ciParticipante) {
        Map<String, Object> response = new HashMap<>();
        int cantRegistrosTutorParticipante = estadoInscripcionDomainRepository.countParticipanteByCarnetIdentidad(ciParticipante);

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
        if(catalogoService.existsParticipanteInCatalogo(ciParticipante)){
            response.put("RegistroAreas",
                    Map.of(
                            "estado", "Completado",
                            "fechaRegistro", "No seteado hacer querys",
                            "comentarios", "Areas de competencia registradas correctamente.",
                            "areas", catalogoService.getAreaCatalogoByCiParticipante(ciParticipante)
                    ));
        }else{
            response.put("RegistroAreas",
                    Map.of(
                            "estado", "No Completado",
                            "fechaRegistro", "No seteado hacer querys",
                            "comentarios", "No se encontraron areas de competencia registradas para el participante.",
                            "areas", "No existen areas registradas"
                    ));
        }
        if (cantRegistrosTutorParticipante > 0) {
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
