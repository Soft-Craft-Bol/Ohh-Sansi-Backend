package com.softcraft.ohhsansibackend.mail.controller;

import com.softcraft.ohhsansibackend.mail.dto.BulkEmailResult;
import com.softcraft.ohhsansibackend.mail.dto.ParticipanteReminderDto;
import com.softcraft.ohhsansibackend.mail.service.MailService;
import com.softcraft.ohhsansibackend.participante.domain.repository.implementation.ParticipanteDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bulk-email")
public class BulEmailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private ParticipanteDomainRepository participanteDomainRepository;

    @PostMapping("/send-reminders")
    public ResponseEntity<Map<String, Object>> sendBulkReminders(
            @RequestParam(defaultValue = "7") int diasAnticipacion) {

        try {
            CompletableFuture<BulkEmailResult> futureResult =
                    mailService.sendBulkReminders(diasAnticipacion);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Proceso de envío masivo iniciado");
            response.put("status", "processing");
            response.put("diasAnticipacion", diasAnticipacion);
            response.put("timestamp", LocalDateTime.now());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al iniciar envío masivo");
            errorResponse.put("details", e.getMessage());
            errorResponse.put("timestamp", LocalDateTime.now());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }

    @PostMapping("/send-reminders-by-period")
    public ResponseEntity<Map<String, Object>> sendBulkRemindersByPeriod(
            @RequestParam String tipoPeriodo) {

        // Validar tipo de período
        if (!tipoPeriodo.equals("INSCRIPCION") && !tipoPeriodo.equals("AMPLIACION")) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Tipo de período inválido");
            errorResponse.put("validTypes", Arrays.asList("INSCRIPCION", "AMPLIACION"));
            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            CompletableFuture<BulkEmailResult> futureResult =
                    mailService.sendBulkRemindersByTipoPeriodo(tipoPeriodo);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Proceso de envío masivo iniciado");
            response.put("status", "processing");
            response.put("tipoPeriodo", tipoPeriodo);
            response.put("timestamp", LocalDateTime.now());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al iniciar envío masivo");
            errorResponse.put("details", e.getMessage());
            errorResponse.put("timestamp", LocalDateTime.now());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }

    @GetMapping("/preview-reminders")
    public ResponseEntity<Map<String, Object>> previewReminders(
            @RequestParam(defaultValue = "7") int diasAnticipacion) {

        try {
            List<ParticipanteReminderDto> participantes =
                    participanteDomainRepository.findParticipantesSinPagoProximoVencimiento(diasAnticipacion);

            Map<String, Object> response = new HashMap<>();
            response.put("totalParticipantes", participantes.size());
            response.put("diasAnticipacion", diasAnticipacion);

            // Agrupar por tipo de período
            Map<String, Long> porTipoPeriodo = participantes.stream()
                    .collect(Collectors.groupingBy(
                            ParticipanteReminderDto::getTipoPeriodo,
                            Collectors.counting()
                    ));
            response.put("porTipoPeriodo", porTipoPeriodo);

            // Agrupar por días restantes
            Map<Integer, Long> porDiasRestantes = participantes.stream()
                    .collect(Collectors.groupingBy(
                            ParticipanteReminderDto::getDiasRestantes,
                            Collectors.counting()
                    ));
            response.put("porDiasRestantes", porDiasRestantes);

            // Mostrar algunos ejemplos (sin emails completos por privacidad)
            List<Map<String, Object>> ejemplos = participantes.stream()
                    .limit(5)
                    .map(p -> {
                        Map<String, Object> ejemplo = new HashMap<>();
                        ejemplo.put("email", p.getEmail().replaceAll("(?<=.{2}).(?=.*@)", "*"));
                        ejemplo.put("nombre", p.getNombreParticipante());
                        ejemplo.put("tipoPeriodo", p.getTipoPeriodo());
                        ejemplo.put("diasRestantes", p.getDiasRestantes());
                        return ejemplo;
                    })
                    .collect(Collectors.toList());
            response.put("ejemplos", ejemplos);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al obtener preview");
            errorResponse.put("details", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }

    @PostMapping("/send-test-reminder")
    public ResponseEntity<Map<String, Object>> sendTestReminder(
            @RequestParam String email,
            @RequestParam(defaultValue = "Usuario Test") String nombre,
            @RequestParam(defaultValue = "TEST123") String codUnique,
            @RequestParam(defaultValue = "2") int diasRestantes,
            @RequestParam(defaultValue = "INSCRIPCION") String tipoPeriodo) {

        try {
            mailService.sendReminderEmail(email, nombre, codUnique, diasRestantes, tipoPeriodo);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Email de prueba enviado exitosamente");
            response.put("destinatario", email);
            response.put("timestamp", LocalDateTime.now());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al enviar email de prueba");
            errorResponse.put("details", e.getMessage());
            errorResponse.put("timestamp", LocalDateTime.now());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }
}
