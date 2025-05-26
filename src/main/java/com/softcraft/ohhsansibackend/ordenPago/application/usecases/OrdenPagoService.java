package com.softcraft.ohhsansibackend.ordenPago.application.usecases;

import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.application.usecases.InscripcionService;
import com.softcraft.ohhsansibackend.ordenPago.domain.models.EstadoOrdenDePago;
import com.softcraft.ohhsansibackend.ordenPago.domain.models.OrdenDePago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softcraft.ohhsansibackend.ordenPago.domain.repository.implementation.OrdenPagoDomainRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public boolean verificarExistenciaDeInscripcionEnOrdenPago(int idInscripcion){
        return ordenPagoDomainRepository.verificarExistenciaDeInscripcionEnOrdenPago(idInscripcion);
    }
    public List<OrdenDePago> obtenerOrdenesNoVencidasEnRango(Date fechaInicio, Date fechaFin) {
        return ordenPagoDomainRepository.findOrdenesNoVencidasEnRango(fechaInicio, fechaFin);
    }

    public OrdenDePago findOrdenDePagoByIdOrdenPago(int idOrdenDePago) {
        try{
             return ordenPagoDomainRepository.findOrdenPagoByIDOrdenPago(idOrdenDePago);
        }catch(Exception e){
            throw new ResourceNotFoundException("Orden de pago con ID " + idOrdenDePago + " no encontrada");
        }
    }
    public Map<String, Object> findOrdenPagoByOlimpiada(int idOlimpiada){
        Map<String, Object> response = new java.util.HashMap<>();
        List<OrdenDePago> ordenes = ordenPagoDomainRepository.findOrdenPagoByOlimpiada(idOlimpiada);
        List<EstadoOrdenDePago> estadoOrdenDePagos = ordenPagoDomainRepository.getAllEstadoOrdenPago();
        try{
            if (ordenes.isEmpty()) {
                throw new ResourceNotFoundException("No se encontraron órdenes de pago para la olimpiada"); //con ID: " + idInscripcion);
            }
            response.put("estadosOrden", estadoOrdenDePagos);
            response.put("ordenes", ordenes);

            return response;
        }catch (Exception e){
            throw new ResourceNotFoundException("No se encontraron órdenes de pago para la olimpiada con ID: " + idOlimpiada);
        }

    }


}
