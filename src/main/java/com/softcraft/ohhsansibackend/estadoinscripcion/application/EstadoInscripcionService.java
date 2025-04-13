package com.softcraft.ohhsansibackend.estadoinscripcion.application;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.CatalogoService;
import com.softcraft.ohhsansibackend.estadoinscripcion.domain.EstadoInscripcionDomainRepository;
import com.softcraft.ohhsansibackend.exception.ParticipanteNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.application.ports.InscripcionAdapter;
import com.softcraft.ohhsansibackend.ordenPago.application.usecases.OrdenPagoService;
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
    private final OrdenPagoService ordenPagoService;
    @Autowired
    public EstadoInscripcionService(EstadoInscripcionDomainRepository estadoInscripcionDomainRepository,
                                    ParticipanteService participanteService,
                                    TutorService tutorService,
                                    InscripcionAdapter inscripcionAdapter,
                                    CatalogoService catalogoService,
                                    OrdenPagoService ordenPagoService
    ) {
        this.estadoInscripcionDomainRepository = estadoInscripcionDomainRepository;
        this.participanteService = participanteService;
        this.tutorService = tutorService;
        this.inscripcionAdapter = inscripcionAdapter;
        this.catalogoService = catalogoService;
        this.ordenPagoService = ordenPagoService;
    }
    public Map<String, Object> verificarExistenciaDeTutores(int ciParticipante) {
        Map<String, Object> response = new HashMap<>();
        int cantRegistrosTutorParticipante = estadoInscripcionDomainRepository.countParticipanteByCarnetIdentidad(ciParticipante);

        Participante participante = getParticipanteByCarnetIdentidad(ciParticipante);
        if (participante != null) {
            response.put("registroDatosParticipante",
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
            response.put("registroAreas",
                    Map.of(
                            "estado", "Completado",
                            "fechaRegistro", "No seteado hacer querys",
                            "comentarios", "Areas de competencia registradas correctamente.",
                            "areas", catalogoService.getAreaCatalogoByCiParticipante(ciParticipante)
                    ));
        }else{
            response.put("registroAreas",
                    Map.of(
                            "estado", "No Completado",
                            "fechaRegistro", "No seteado hacer querys",
                            "comentarios", "No se encontraron areas de competencia registradas para el participante.",
                            "areas", "No existen areas registradas"
                    ));
        }
        if (cantRegistrosTutorParticipante > 0) {
            response.put("registroDatosTutor",
                    Map.of(
                            "estado", "Completado",
                            "fechaRegistro", "No seteado hacer querys",
                            "comentarios", "Datos del tutor registrados correctamente.",
                            "tutores", tutorService.findTutorsByCarnetParticipante(ciParticipante)
                    ));
        } else {
            if (inscripcionAdapter.calculateEdad(participante) < 15) {
                response.put("registroDatosTutor",
                        Map.of(
                                "estado", "No Completado",
                                "fechaRegistro", "No seteado hacer querys",
                                "comentarios", "No se encontraron tutores registrados para el participante.",
                                "tutores", "No existen tutores registrados"
                        ));
            } else {
                response.put("registroDatosTutor",
                        Map.of(
                                "estado", "No Completado",
                                "fechaRegistro", "No seteado hacer querys",
                                "comentarios", "No es necesario registrar tutores.",
                                "tutores", "No existen tutores registrados"
                        ));
            }
        }
        if(ordenPagoService.verificarExistenciaDeInscripcionEnOrdenPago(participante.getIdInscripcion())){
            response.put("registroOrdenPago",
                    Map.of(
                            "estado", "Generado",
                            "fechaRegistro", "No seteado hacer querys",
                            "comentarios", "Orden de pago registrada correctamente.",
                            "codigoUnico", inscripcionAdapter.
                                    findInscripcionById(participante.getIdInscripcion()).
                                    getCodigoUnicoInscripcion()
                    ));
        }else{
            response.put("registroOrdenPago",
                    Map.of(
                            "estado", "No Generado",
                            "fechaRegistro", "No seteado hacer querys",
                            "comentarios", "No se encontraron ordenes de pago registradas para el participante.",
                            "codigoUnico", inscripcionAdapter.
                                    findInscripcionById(participante.getIdInscripcion()).
                                    getCodigoUnicoInscripcion()
                    ));
        }
        return response;
    }

    public Participante getParticipanteByCarnetIdentidad(int ciParticipante){
        return participanteService.findByCarnetIdentidadService(ciParticipante);
    }


}
