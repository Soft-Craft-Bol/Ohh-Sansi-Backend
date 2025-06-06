package com.softcraft.ohhsansibackend.comprobantepago.application;

import com.softcraft.ohhsansibackend.comprobantepago.domain.ComprobantePagoAppRepository;
import com.softcraft.ohhsansibackend.comprobantepago.domain.model.ComprobantePago;
import com.softcraft.ohhsansibackend.comprobantepago.domain.model.EstadoComprobantePago;
import com.softcraft.ohhsansibackend.comprobantepago.domain.model.EstadoComprobantePagoEnum;
import com.softcraft.ohhsansibackend.inscripcion.application.usecases.InscripcionService;
import com.softcraft.ohhsansibackend.mail.service.MailService;
import com.softcraft.ohhsansibackend.ordenPago.application.usecases.OrdenPagoService;
import com.softcraft.ohhsansibackend.ordenPago.domain.models.OrdenDePago;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComprobantePagoAppService {
    private final ComprobantePagoAppRepository comprobantePagoRepository;
    private final ComprobantePagoAppRepository comprobantePagoAppRepository;
    private final OrdenPagoService ordenPagoService;
    private final MailService mailService;
    private final ParticipanteService participanteService;
    private final InscripcionService inscripcionService;

    @Autowired
    private ComprobantePagoAppService(
            ComprobantePagoAppRepository comprobantePagoRepository,
            ComprobantePagoAppRepository comprobantePagoAppRepository,
            @Lazy OrdenPagoService ordenPagoService,
            MailService mailService,
            ParticipanteService participanteService, InscripcionService inscripcionService) {
        this.comprobantePagoRepository = comprobantePagoRepository;
        this.comprobantePagoAppRepository = comprobantePagoAppRepository;
        this.ordenPagoService = ordenPagoService;
        this.mailService = mailService;
        this.participanteService = participanteService;
        this.inscripcionService = inscripcionService;
    }

    public List<Map<String, Object>> getComprobantesPago(){
        try {
            return comprobantePagoRepository.getComprobantesPago();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los comprobantes de pago: " + e.getMessage());
        }
    }
    public Map<String, Object> cambiarEstadoComprobantePago(int idComprobantePago, int nuevoEstadoId) {
        Map<String, Object> response = new HashMap<>();
        try {
            EstadoComprobantePagoEnum nuevoEstado = EstadoComprobantePagoEnum.fromId(nuevoEstadoId);
            OrdenDePago ordenPago = ordenPagoService.getOrdenDePagoByIdComprobantePago(idComprobantePago);
            String emailUsuario = obtenerEmailUsuario(ordenPago);
            String codigoUnico =ordenPago.getCodOrdenPago();
            comprobantePagoRepository.actualizarEstadoComprobantePago(idComprobantePago, nuevoEstado.getId());
            response.put("success", true);
            response.put("message", "Estado del comprobante de pago actualizado correctamente.");
            response.put("idComprobantePago", idComprobantePago);
            response.put("nuevoEstado", nuevoEstado.name());
            try {
                if (nuevoEstado.getId() == 1) {
                    // Cambiar estado de la orden de pago
                    ordenPagoService.changeEstadoOrdenPagoAPagado(ordenPago.getIdOrdenPago());

                    // Enviar email de confirmación
                    mailService.sendPagoAceptadoEmail(emailUsuario, codigoUnico);
                    System.out.println("Email de pago aceptado enviado a: " + emailUsuario);

                } else if (nuevoEstado.getId() == 2) {
                    mailService.sendPagoRechazadoEmail(emailUsuario, codigoUnico);
                    System.out.println("Email de pago rechazado enviado a: " + emailUsuario);
                }

                response.put("emailEnviado", true);

            } catch (Exception emailException) {
                System.err.println("Error al enviar email: " + emailException.getMessage());
                response.put("emailEnviado", false);
                response.put("emailError", emailException.getMessage());
            }

        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", "Estado no válido: " + e.getMessage());
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al cambiar el estado del comprobante de pago: " + e.getMessage());
        }
        return response;
    }

    private String obtenerEmailUsuario(OrdenDePago ordenPago) {
        /*return participanteService.findByEmail(ordenPago.getIdOrdenPago());

        return inscripcionService.getEmailByCodigoUnico(ordenPago.getCodigoUnico());
*/
        throw new RuntimeException("Método obtenerEmailUsuario() debe ser implementado según tu estructura de datos");
    }

    public boolean verificarExistenciaComprobantePago(int ciParticipante){
        try{
            return comprobantePagoRepository.verificarExistenciaComprobantePago(ciParticipante);
        }catch(Exception e){
            throw new RuntimeException("Error al verificar la existencia del comprobante de pago: " + e.getMessage());
        }
    }

    public EstadoComprobantePago obtenerEstadoComprobantePago(int ciParticipante){
        try{
            return comprobantePagoRepository.getEstadoComprobantePago(ciParticipante);
        }catch (Exception e){
            throw new RuntimeException("Error al obtener el estado del comprobante de pago: " + e.getMessage());
        }
    }
    public ComprobantePago getComprobantePagoByCiParticipante(int ciParticipante) {
        try {
            return comprobantePagoRepository.getComprobantePagoByCiParticipante(ciParticipante);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el comprobante de pago por ci participante: " + e.getMessage());
        }
    }
    public Map<String, Object> findComprobantePagoByIdOlimpiada(int idOlimpiada) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ComprobantePago> comprobantesPago = comprobantePagoAppRepository.findAllComprobanteByIdOlimpiada(idOlimpiada);
            if (comprobantesPago.isEmpty()) {
                response.put("error", "No se encontraron comprobantes de pago para la olimpiada con ID: " + idOlimpiada);
                return response;
            }else{
                List<EstadoComprobantePago> estadosComprobantePago = comprobantePagoAppRepository.findAllEstadosComprobantePago();
                response.put("estadosComprobantePago", estadosComprobantePago);
                response.put("comprobantesPago", comprobantesPago);
            }
        } catch (Exception e) {
            response.put("error", "Error al obtener los comprobantes de pago: " + e.getMessage());
        }
        return response;
    }
    public Map<String, Object> sendInfoForComprobantePago(String codComprobantePago) {
        Map<String, Object> response = new HashMap<>();
        try{
            response.put("success", true);
            response.put("estadosComprobante",comprobantePagoAppRepository.getEstadosComprobantePago());
            response.put("comprobantePago", comprobantePagoAppRepository.getComprobantePagoByCod(codComprobantePago));
            //response.put()
        }catch (Exception e){
            throw new RuntimeException("Error al enviar los estados de comprobante de pago: " + e.getMessage());
        }
        return response;
    }



}
