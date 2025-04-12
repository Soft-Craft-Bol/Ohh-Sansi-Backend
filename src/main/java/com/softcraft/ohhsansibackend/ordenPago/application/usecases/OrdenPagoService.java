package com.softcraft.ohhsansibackend.ordenPago.application.usecases;

import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.application.usecases.InscripcionService;
import com.softcraft.ohhsansibackend.ordenPago.domain.models.OrdenDePago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softcraft.ohhsansibackend.ordenPago.domain.repository.implementation.OrdenPagoDomainRepository;

import java.util.List;

@Service
public class OrdenPagoService {
    private final InscripcionService inscripcionService;
    private final OrdenPagoDomainRepository ordenPagoDomainRepository;
    @Autowired
    public OrdenPagoService(InscripcionService inscripcionService, OrdenPagoDomainRepository ordenPagoDomainRepository) {
        this.inscripcionService = inscripcionService;
        this.ordenPagoDomainRepository = ordenPagoDomainRepository;
    }

    public Long getInscriptionIdbyCodigoUnico(String uniqueCode) {
        return inscripcionService.findIdByCodigoUnico(uniqueCode);
    }

    public OrdenDePago insertarOrdenDePago(OrdenDePago ordenDePago) {
        try {
            OrdenDePago existingOrden = ordenPagoDomainRepository.findOrdenPagoByID(ordenDePago.getIdInscripcion());
            return existingOrden;
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return ordenPagoDomainRepository.save(ordenDePago);
        }
    }

    public List<OrdenDePago> obtenerOrdenesPorIdInscripcion(String codUnico) {
        Long idInscripcion = getInscriptionIdbyCodigoUnico(codUnico);
        List<OrdenDePago> ordenes = ordenPagoDomainRepository.findAllByIdInscripcion(idInscripcion.intValue());
        if (ordenes.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron órdenes de pago para la inscripción"); //con ID: " + idInscripcion);
        }
        return ordenes;
    }
}
