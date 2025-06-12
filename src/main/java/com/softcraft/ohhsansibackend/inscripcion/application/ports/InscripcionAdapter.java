package com.softcraft.ohhsansibackend.inscripcion.application.ports;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.inscripcion.domain.services.InscripcionDomainService;
import com.softcraft.ohhsansibackend.ordenPago.application.usecases.OrdenPagoService;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;


@Repository
public class InscripcionAdapter {
    private final InscripcionDomainService inscripcionDomainService;
    private final ParticipanteService participanteService;
    private final OrdenPagoService ordenPagoService;

    @Autowired
    public InscripcionAdapter(InscripcionDomainService inscripcionDomainService, @Lazy ParticipanteService participanteService,@Lazy OrdenPagoService ordenPagoService) {
        this.inscripcionDomainService = inscripcionDomainService;
        this.participanteService = participanteService;
        this.ordenPagoService = ordenPagoService;
    }

    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
        return inscripcionDomainService.createInscripcion(inscripcion);
    }

    public String findCodigoUnicoById(int idInscripcion) {
        return inscripcionDomainService.findCodigoUnicoByIdInscripcion(idInscripcion);
    }


    public Inscripcion findInscripcionById(int id) {
        return inscripcionDomainService.getInscripcion(id);
    }

    public List<Inscripcion> findAllInscripciones() {
        return inscripcionDomainService.listInscripcion();
    }

    public Map<String, Object> getDetalleInscripcion(String codigoUnico) {
        return inscripcionDomainService.getInscripcionDetails(codigoUnico);
    }

    public Long findIdByCodigoUnico(String codigoUnico) {
        return inscripcionDomainService.findIdByCodigoUnico(codigoUnico);
    }

    public int calculateEdad(Participante participante) {
        java.util.Date fechaNacimiento = participante.getFechaNacimiento();
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento del participante no está registrada.");
        }
        LocalDate birthDate = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
    public Participante findParticipanteByIdInscripcion(int idInscripcion) {
        return participanteService.findParticipanteByIdInscripcion(idInscripcion);
    }
    public boolean deleteInscripcionById(int idInscripcion) {
        return inscripcionDomainService.deleteInscripcionById(idInscripcion);
    }

    public boolean verificarEstadoOrdenPago(int idInscripcion) {
        return ordenPagoService.verificarExistenciaDeInscripcionEnOrdenPago(idInscripcion);
    }

    public List<Map<String, Object>> getReporteInscripcionByArea(Integer idArea, int idOlimpiada) {
        return inscripcionDomainService.getReporteInscripcionByArea(idArea, idOlimpiada);
    }
}
