package com.softcraft.ohhsansibackend.estadoinscripcion.application;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.CatalogoService;
import com.softcraft.ohhsansibackend.comprobantepago.application.ComprobantePagoAppService;
import com.softcraft.ohhsansibackend.comprobantepago.domain.model.ComprobantePago;
import com.softcraft.ohhsansibackend.comprobantepago.domain.model.EstadoComprobantePago;
import com.softcraft.ohhsansibackend.comprobantepago.domain.model.EstadoComprobantePagoEnum;
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
    private final ComprobantePagoAppService comprobantePagoAppService;

    @Autowired
    public EstadoInscripcionService(EstadoInscripcionDomainRepository estadoInscripcionDomainRepository,
                                    ParticipanteService participanteService,
                                    TutorService tutorService,
                                    InscripcionAdapter inscripcionAdapter,
                                    CatalogoService catalogoService,
                                    OrdenPagoService ordenPagoService,
                                    ComprobantePagoAppService comprobantePagoAppService
    ) {
        this.estadoInscripcionDomainRepository = estadoInscripcionDomainRepository;
        this.participanteService = participanteService;
        this.tutorService = tutorService;
        this.inscripcionAdapter = inscripcionAdapter;
        this.catalogoService = catalogoService;
        this.ordenPagoService = ordenPagoService;
        this.comprobantePagoAppService = comprobantePagoAppService;
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
        int tutoresAcademicos = tutorService.countTutorsAcademicosByParticipanteId(participante.getIdParticipante());
        int tutoresLegales = tutorService.countTutorsLegalesByParticipanteId(participante.getIdParticipante());
        int totalTutores = tutoresAcademicos + tutoresLegales;
        int edadParticipante = inscripcionAdapter.calculateEdad(participante);

        if (tutoresLegales >= 1) {
            if(tutoresAcademicos == 0){
                response.put("registroDatosTutor",
                        Map.of(
                                "estado", "Completado",
                                "fechaRegistro", "No seteado hacer querys",
                                "comentarios", "Datos del tutor registrados correctamente. Recuerda que puedes registrar hasta 2 tutores academicos",
                                "tutores", tutorService.findTutorsByCarnetParticipante(ciParticipante)
                        ));
            }
            if(tutoresAcademicos == 1){
                response.put("registroDatosTutor",
                        Map.of(
                                "estado", "Completado",
                                "fechaRegistro", "No seteado hacer querys",
                                "comentarios", "Datos del tutor registrados correctamente. Recuerda que puedes registrar hasta 1 tutor académico más",
                                "tutores", tutorService.findTutorsByCarnetParticipante(ciParticipante)
                        ));

            }
            if(tutoresAcademicos == 2){
                response.put("registroDatosTutor",
                        Map.of(
                                "estado", "Completado",
                                "fechaRegistro", "No seteado hacer querys",
                                "comentarios", "Datos del tutor registrados correctamente.",
                                "tutores", tutorService.findTutorsByCarnetParticipante(ciParticipante)
                        ));
            }
        }else {
            if(tutoresAcademicos>=1){
                if(edadParticipante >= 15) {
                    response.put("registroDatosTutor",
                            Map.of(
                                    "estado", "Completado",
                                    "fechaRegistro", "No seteado hacer querys",
                                    "comentarios", "Datos del tutor registrados correctamente. Recuerda que puedes registrar 3 tutores como maximo, 2 de estos; academicos como maximo",
                                    "tutores", tutorService.findTutorsByCarnetParticipante(ciParticipante)
                            ));
                }else{
                    response.put("registroDatosTutor",
                            Map.of(
                                    "estado", "No Completado",
                                    "fechaRegistro", "No seteado hacer querys",
                                    "comentarios", "Es necesario que registres al menos un tutor legal",
                                    "tutores", tutorService.findTutorsByCarnetParticipante(ciParticipante)
                            ));
                }
            }else{
                response.put("registroDatosTutor",
                        Map.of(
                                "estado", "No Completado",
                                "fechaRegistro", "No seteado hacer querys",
                                "comentarios", "Es necesario que registres al menos un tutor legal y si quieres puedes registrar uno o dos tutor academico",
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
        if(comprobantePagoAppService.verificarExistenciaComprobantePago(ciParticipante)){
            ComprobantePago comprobantePago = comprobantePagoAppService.getComprobantePagoByCiParticipante(ciParticipante);
            EstadoComprobantePago estadoComprobantePago = comprobantePagoAppService.obtenerEstadoComprobantePago(ciParticipante);
            if(EstadoComprobantePagoEnum.ACEPTADA.getId()==estadoComprobantePago.getIdEstadoComprobantePago()){
                response.put("comprobantePagoStatus", Map.of(
                        "estado",comprobantePagoAppService.obtenerEstadoComprobantePago(ciParticipante).getNombreEstadoComprobante(),
                        "fechaRegistro", comprobantePago.getFechaPago(),
                        "comentarios","El comprobante de pago fue Aceptado",
                        "comprobantePago",comprobantePago
                ));
            }else if(EstadoComprobantePagoEnum.PENDIENTE.getId()==estadoComprobantePago.getIdEstadoComprobantePago()){
                response.put("comprobantePagoStatus", Map.of(
                        "estado",comprobantePagoAppService.obtenerEstadoComprobantePago(ciParticipante).getNombreEstadoComprobante(),
                        "fechaRegistro", comprobantePago.getFechaPago(),
                        "comentarios","El comprobante de pago esta pendiente de revision",
                        "comprobantePago",comprobantePago
                ));
            } else if (EstadoComprobantePagoEnum.RECHAZADA.getId()==estadoComprobantePago.getIdEstadoComprobantePago()) {
                response.put("comprobantePagoStatus", Map.of(
                        "estado",comprobantePagoAppService.obtenerEstadoComprobantePago(ciParticipante).getNombreEstadoComprobante(),
                        "fechaRegistro", comprobantePago.getFechaPago(),
                        "comentarios","El comprobante de pago fue rechazado, sube de nuevo el comprobante de pago",
                        "comprobantePago",comprobantePago
                ));
            }else{
                response.put("comprobantePagoStatus", Map.of(
                        "estado","No Pagado",
                        "fechaRegistro", "No existe fecha",
                        "comentarios","No existe ningun pago para la orden de pago",
                        "comprobantePago","No existen registros"
                ));
            }

        }else{
            response.put("comprobantePagoStatus", Map.of(
                    "estado","No Pagado",
                    "fechaRegistro", "No existe fecha",
                    "comentarios","No existe ningun pago para la orden de pago",
                    "comprobantePago","No existen registros"
            ));
        }
        return response;
    }

    public Participante getParticipanteByCarnetIdentidad(int ciParticipante){
        return participanteService.findByCarnetIdentidadService(ciParticipante);
    }


}
