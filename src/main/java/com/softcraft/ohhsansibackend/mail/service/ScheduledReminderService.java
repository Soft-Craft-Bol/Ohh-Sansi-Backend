package com.softcraft.ohhsansibackend.mail.service;

import com.softcraft.ohhsansibackend.mail.dto.BulkEmailResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
@Service
@Component
public class ScheduledReminderService {
    @Autowired
    private MailService mailService;

    private static final Logger logger = LoggerFactory.getLogger(ScheduledReminderService.class);

    // Ejecutar todos los días a las 9:00 AM
    @Scheduled(cron = "0 0 9 * * *")
    public void sendDailyReminders() {
        logger.info("Iniciando envío automático de recordatorios diarios");

        try {
            // Enviar recordatorios para participantes con vencimiento en 3 días
            CompletableFuture<BulkEmailResult> result = mailService.sendBulkReminders(3);

            result.thenAccept(bulkResult -> {
                logger.info("Envío automático completado: {} exitosos, {} fallidos de {} total",
                        bulkResult.getSuccessCount(),
                        bulkResult.getFailureCount(),
                        bulkResult.getTotalEmails());

                if (!bulkResult.getErrors().isEmpty()) {
                    logger.warn("Errores en envío automático: {}", bulkResult.getErrors());
                }
            });

        } catch (Exception e) {
            logger.error("Error en envío automático de recordatorios", e);
        }
    }

    // Ejecutar todos los días a las 18:00 PM para recordatorios urgentes
    @Scheduled(cron = "0 0 18 * * *")
    public void sendUrgentReminders() {
        logger.info("Iniciando envío automático de recordatorios urgentes");

        try {
            // Enviar recordatorios urgentes para participantes con vencimiento en 1 día
            CompletableFuture<BulkEmailResult> result = mailService.sendBulkReminders(1);

            result.thenAccept(bulkResult -> {
                logger.info("Envío urgente completado: {} exitosos, {} fallidos de {} total",
                        bulkResult.getSuccessCount(),
                        bulkResult.getFailureCount(),
                        bulkResult.getTotalEmails());
            });

        } catch (Exception e) {
            logger.error("Error en envío automático de recordatorios urgentes", e);
        }
    }
}
