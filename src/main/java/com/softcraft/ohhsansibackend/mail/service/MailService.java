package com.softcraft.ohhsansibackend.mail.service;

import com.softcraft.ohhsansibackend.mail.dto.BulkEmailResult;
import com.softcraft.ohhsansibackend.mail.dto.ParticipanteReminderDto;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.domain.repository.implementation.ParticipanteDomainRepository;
import com.softcraft.ohhsansibackend.participante.domain.services.ParticipanteDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private String urlOrdenPago = "http://localhost:5173/orden-de-pago";
    @Autowired
    private ParticipanteDomainRepository participanteDomainRepository;
    @Autowired
    private ParticipanteDomainService participanteDomainService;

    @Async
    public void sendInscripcionEmail(String to, String codUnique) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String subject = "Inscripción exitosa";
        String dynamicContent = buildInscripcionEmailContent(codUnique);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(dynamicContent, true);
        helper.setFrom(fromEmail);

        mailSender.send(message);
    }

    @Async
    public void sendPagoAceptadoEmail(String to, String codUnique) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String subject = "¡Pago Confirmado - Inscripción Completada!";
        String dynamicContent = buildPagoAceptadoEmailContent(codUnique);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(dynamicContent, true);
        helper.setFrom(fromEmail);

        mailSender.send(message);
    }

    @Async
    public void sendPagoRechazadoEmail(String to, String codUnique) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String subject = "Pago No Verificado - Acción Requerida";
        String dynamicContent = buildPagoRechazadoEmailContent(codUnique);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(dynamicContent, true);
        helper.setFrom(fromEmail);

        mailSender.send(message);
    }

    private String buildInscripcionEmailContent(String codUnique) {
        return "<html>"
                + "<head>"
                + "<style>"
                + "body { background-color: #f1f5f9; font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Ubuntu, sans-serif; }"
                + ".container { background-color: #ffffff; margin: 0 auto; padding: 40px; max-width: 600px; text-align: left; }"
                + ".box { padding: 0 20px; }"
                + ".title { font-size: 24px; font-weight: bold; margin-bottom: 10px; }"
                + ".paragraph { color: #333; font-size: 16px; line-height: 24px; }"
                + ".button { background-color: #000; border-radius: 6px; color: #fff; font-size: 16px; font-weight: bold; text-decoration: none; text-align: center; display: inline-block; padding: 12px 24px; margin: 20px 0; cursor: pointer; }"
                + ".hr { border-color: #e6ebf1; margin: 20px 0; }"
                + ".contact-info { display: flex; align-items: center; gap: 10px; font-size: 16px; }"
                + ".icon { width: 20px; height: 20px; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class=\"container\">"
                + "<div class=\"box\">"
                + "<p class=\"title\">Inscripción enviada</p>"
                + "<p class=\"paragraph\">Se ha procesado su solicitud para la inscripción en el evento Ohh-Sansi.</p>"
                + "<p class=\"paragraph\"><strong>Éste es su código de inscripción:</strong></p>"
                + "<p class=\"paragraph\" style=\"font-size: 20px; font-weight: bold; color: #2d3748;\">" + codUnique + "</p>"
                + "<a href=\"" + urlOrdenPago + "\" class=\"button\">Genere su orden de pago aquí</a>"
                + "<img src=\"https://tecnocursosedu.com/wp-content/uploads/2024/10/ohsansi.jpg\" alt=\"Logo OhSansi\" style=\"margin-top: 20px; max-width: 100%; height: auto;\"/>"
                + "<hr class=\"hr\" />"
                + "<p class=\"paragraph\">Se publicará más información pronto. Mientras tanto, si tienes alguna pregunta, no dudes en comunicarte con nosotros a través de nuestras redes:</p>"
                + "<p class=\"contact-info\"><img src=\"https://cdn-icons-png.flaticon.com/512/733/733585.png\" alt=\"WhatsApp\" class=\"icon\" /> +591 71486093</p>"
                + "<p class=\"contact-info\"><img src=\"https://cdn-icons-png.flaticon.com/512/732/732200.png\" alt=\"Email\" class=\"icon\" /> softcraft2024@gmail.com</p>"
                + "<p class=\"paragraph\">¡Te esperamos!</p>"
                + "<p class=\"paragraph\">El equipo de <a href=\"https://www.softcraftbol.com/\">Softcraft</a></p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
    }

    private String buildPagoAceptadoEmailContent(String codUnique) {
        return "<html>"
                + "<head>"
                + "<style>"
                + "body { background-color: #f1f5f9; font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Ubuntu, sans-serif; }"
                + ".container { background-color: #ffffff; margin: 0 auto; padding: 40px; max-width: 600px; text-align: left; }"
                + ".box { padding: 0 20px; }"
                + ".title { font-size: 24px; font-weight: bold; margin-bottom: 10px; color: #10b981; }"
                + ".paragraph { color: #333; font-size: 16px; line-height: 24px; }"
                + ".success-badge { background-color: #dcfce7; color: #166534; padding: 8px 16px; border-radius: 20px; font-weight: bold; display: inline-block; margin: 20px 0; }"
                + ".highlight-box { background-color: #f0fdf4; border: 2px solid #10b981; border-radius: 8px; padding: 20px; margin: 20px 0; }"
                + ".code-display { font-size: 20px; font-weight: bold; color: #2d3748; background-color: #f7fafc; padding: 15px; border-radius: 6px; text-align: center; margin: 15px 0; }"
                + ".hr { border-color: #e6ebf1; margin: 20px 0; }"
                + ".contact-info { display: flex; align-items: center; gap: 10px; font-size: 16px; }"
                + ".icon { width: 20px; height: 20px; }"
                + ".check-icon { color: #10b981; font-size: 48px; text-align: center; margin: 20px 0; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class=\"container\">"
                + "<div class=\"box\">"
                + "<div class=\"check-icon\">✅</div>"
                + "<p class=\"title\">¡Pago Confirmado!</p>"
                + "<div class=\"success-badge\">INSCRIPCIÓN COMPLETADA</div>"
                + "<p class=\"paragraph\">¡Excelentes noticias! Hemos verificado y confirmado tu pago para el evento Ohh-Sansi.</p>"
                + "<div class=\"highlight-box\">"
                + "<p class=\"paragraph\"><strong>Tu inscripción está ahora COMPLETAMENTE CONFIRMADA</strong></p>"
                + "<p class=\"paragraph\"><strong>Código de inscripción:</strong></p>"
                + "<div class=\"code-display\">" + codUnique + "</div>"
                + "</div>"
                + "<p class=\"paragraph\"><strong>¿Qué sigue ahora?</strong></p>"
                + "<p class=\"paragraph\">• Guarda este email como comprobante de tu inscripción</p>"
                + "<p class=\"paragraph\">• Mantén tu código de inscripción a la mano</p>"
                + "<p class=\"paragraph\">• Estaremos enviando más detalles sobre el evento próximamente</p>"
                + "<img src=\"https://tecnocursosedu.com/wp-content/uploads/2024/10/ohsansi.jpg\" alt=\"Logo OhSansi\" style=\"margin-top: 20px; max-width: 100%; height: auto;\"/>"
                + "<hr class=\"hr\" />"
                + "<p class=\"paragraph\">Si tienes alguna pregunta, no dudes en comunicarte con nosotros:</p>"
                + "<p class=\"contact-info\"><img src=\"https://cdn-icons-png.flaticon.com/512/733/733585.png\" alt=\"WhatsApp\" class=\"icon\" /> +591 71486093</p>"
                + "<p class=\"contact-info\"><img src=\"https://cdn-icons-png.flaticon.com/512/732/732200.png\" alt=\"Email\" class=\"icon\" /> softcraft2024@gmail.com</p>"
                + "<p class=\"paragraph\">¡Nos vemos en el evento!</p>"
                + "<p class=\"paragraph\">El equipo de <a href=\"https://www.softcraftbol.com/\">Softcraft</a></p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
    }

    private String buildPagoRechazadoEmailContent(String codUnique) {
        return "<html>"
                + "<head>"
                + "<style>"
                + "body { background-color: #f1f5f9; font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Ubuntu, sans-serif; }"
                + ".container { background-color: #ffffff; margin: 0 auto; padding: 40px; max-width: 600px; text-align: left; }"
                + ".box { padding: 0 20px; }"
                + ".title { font-size: 24px; font-weight: bold; margin-bottom: 10px; color: #dc2626; }"
                + ".paragraph { color: #333; font-size: 16px; line-height: 24px; }"
                + ".warning-badge { background-color: #fef2f2; color: #b91c1c; padding: 8px 16px; border-radius: 20px; font-weight: bold; display: inline-block; margin: 20px 0; }"
                + ".warning-box { background-color: #fefcfb; border: 2px solid #f59e0b; border-radius: 8px; padding: 20px; margin: 20px 0; }"
                + ".code-display { font-size: 20px; font-weight: bold; color: #2d3748; background-color: #f7fafc; padding: 15px; border-radius: 6px; text-align: center; margin: 15px 0; }"
                + ".button { background-color: #dc2626; border-radius: 6px; color: #fff; font-size: 16px; font-weight: bold; text-decoration: none; text-align: center; display: inline-block; padding: 12px 24px; margin: 20px 0; cursor: pointer; }"
                + ".hr { border-color: #e6ebf1; margin: 20px 0; }"
                + ".contact-info { display: flex; align-items: center; gap: 10px; font-size: 16px; }"
                + ".icon { width: 20px; height: 20px; }"
                + ".warning-icon { color: #dc2626; font-size: 48px; text-align: center; margin: 20px 0; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class=\"container\">"
                + "<div class=\"box\">"
                + "<div class=\"warning-icon\">⚠️</div>"
                + "<p class=\"title\">Pago No Verificado</p>"
                + "<div class=\"warning-badge\">ACCIÓN REQUERIDA</div>"
                + "<p class=\"paragraph\">Hemos revisado tu comprobante de pago para el evento Ohh-Sansi, pero no pudimos verificarlo exitosamente.</p>"
                + "<div class=\"warning-box\">"
                + "<p class=\"paragraph\"><strong>Código de inscripción:</strong></p>"
                + "<div class=\"code-display\">" + codUnique + "</div>"
                + "</div>"
                + "<p class=\"paragraph\"><strong>¿Qué puedes hacer?</strong></p>"
                + "<p class=\"paragraph\">• Verifica que el comprobante sea claro y legible</p>"
                + "<p class=\"paragraph\">• Asegúrate de que el monto sea correcto</p>"
                + "<p class=\"paragraph\">• Sube un nuevo comprobante de pago</p>"
                + "<p class=\"paragraph\">• Contáctanos si necesitas ayuda</p>"
                + "<a href=\"" + urlOrdenPago + "\" class=\"button\">Subir nuevo comprobante</a>"
                + "<img src=\"https://tecnocursosedu.com/wp-content/uploads/2024/10/ohsansi.jpg\" alt=\"Logo OhSansi\" style=\"margin-top: 20px; max-width: 100%; height: auto;\"/>"
                + "<hr class=\"hr\" />"
                + "<p class=\"paragraph\">Si tienes dudas o necesitas ayuda, contáctanos:</p>"
                + "<p class=\"contact-info\"><img src=\"https://cdn-icons-png.flaticon.com/512/733/733585.png\" alt=\"WhatsApp\" class=\"icon\" /> +591 71486093</p>"
                + "<p class=\"contact-info\"><img src=\"https://cdn-icons-png.flaticon.com/512/732/732200.png\" alt=\"Email\" class=\"icon\" /> softcraft2024@gmail.com</p>"
                + "<p class=\"paragraph\">Estamos aquí para ayudarte completar tu inscripción.</p>"
                + "<p class=\"paragraph\">El equipo de <a href=\"https://www.softcraftbol.com/\">Softcraft</a></p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
    }

    @Async
    public void sendEmailAsync(String to, String codUnique) throws MessagingException {
        sendInscripcionEmail(to, codUnique);
    }
    @Async
    public void sendReminderEmail(String to, String nombreParticipante, String codUnique,
                                  int diasRestantes, String tipoPeriodo) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String subject = buildReminderSubject(diasRestantes, tipoPeriodo);
        String dynamicContent = buildReminderEmailContent(nombreParticipante, codUnique, diasRestantes, tipoPeriodo);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(dynamicContent, true);
        helper.setFrom(fromEmail);

        mailSender.send(message);
    }

    private String buildReminderSubject(int diasRestantes, String tipoPeriodo) {
        if (diasRestantes <= 1) {
            return "🚨 ÚLTIMO DÍA - Completa tu inscripción Ohh-Sansi";
        } else if (diasRestantes <= 3) {
            return "⚠️ Solo " + diasRestantes + " días - Completa tu inscripción Ohh-Sansi";
        } else {
            return "Recordatorio: Completa tu inscripción Ohh-Sansi";
        }
    }

    private String buildReminderEmailContent(String nombreParticipante, String codUnique,
                                             int diasRestantes, String tipoPeriodo) {
        String alertMessage = buildAlertMessage(diasRestantes, tipoPeriodo);
        String urgencyClass = diasRestantes <= 2 ? "urgent" : "warning";

        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "body { background-color: #f8fafc; font-family: 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif; margin: 0; padding: 0; color: #334155; line-height: 1.6; }"
                + ".email-container { max-width: 600px; margin: 20px auto; background-color: #ffffff; border-radius: 12px; box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06); overflow: hidden; }"
                + ".header { background: linear-gradient(135deg, #4f46e5, #7c3aed); color: white; padding: 30px 20px; text-align: center; }"
                + ".header h1 { margin: 0; font-size: 24px; font-weight: 600; }"
                + ".content { padding: 30px; }"
                + ".alert-banner { background-color: #fee2e2; border-left: 4px solid #dc2626; padding: 16px; margin: 20px 0; border-radius: 0 4px 4px 0; }"
                + ".alert-banner.urgent { background-color: #fef2f2; border-left-color: #b91c1c; animation: pulse 2s infinite; }"
                + ".alert-banner p { margin: 0; color: #b91c1c; font-weight: 500; }"
                + ".btn { display: inline-block; background: linear-gradient(135deg, #4f46e5, #7c3aed); color: white !important; text-decoration: none; font-weight: 600; padding: 12px 24px; border-radius: 6px; margin: 20px 0; text-align: center; box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06); }"
                + ".footer { padding: 20px; text-align: center; background-color: #f1f5f9; font-size: 14px; color: #64748b; }"
                + ".contact-item { display: flex; align-items: center; margin: 10px 0; }"
                + ".contact-icon { width: 20px; height: 20px; margin-right: 10px; }"
                + ".logo { max-width: 180px; height: auto; margin: 20px auto; display: block; }"
                + ".divider { border: none; height: 1px; background-color: #e2e8f0; margin: 25px 0; }"
                + ".highlight { font-weight: 600; color: #1e293b; }"
                + ".code-display { font-size: 18px; font-weight: bold; color: #2d3748; background-color: #f7fafc; padding: 12px; border-radius: 6px; text-align: center; margin: 15px 0; border: 2px solid #e2e8f0; }"
                + "@keyframes pulse { 0%, 100% { opacity: 1; } 50% { opacity: 0.8; } }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class=\"email-container\">"
                + "<div class=\"header\">"
                + "<h1>Recordatorio de Pago Pendiente</h1>"
                + "</div>"
                + "<div class=\"content\">"
                + "<p>Estimado/a <span class=\"highlight\">" + nombreParticipante + "</span>,</p>"
                + "<p>Le informamos que aún no hemos recibido el comprobante de pago correspondiente a su inscripción en el evento <strong>Ohh-Sansi</strong>.</p>"
                + "<div class=\"alert-banner " + urgencyClass + "\">"
                + "<p>" + alertMessage + "</p>"
                + "</div>"
                + "<p><strong>Código de inscripción:</strong></p>"
                + "<div class=\"code-display\">" + codUnique + "</div>"
                + "<p>Para evitar la cancelación de su inscripción, por favor complete el proceso de pago lo antes posible.</p>"
                + "<a href=\"" + urlOrdenPago + "\" class=\"btn\">Subir Comprobante de Pago</a>"
                + "<p>Si ya realizó el pago, por favor ignore este mensaje o contáctenos para verificar el estado de su transacción.</p>"
                + "<hr class=\"divider\">"
                + "<img src=\"https://tecnocursosedu.com/wp-content/uploads/2024/10/ohsansi.jpg\" alt=\"Logo OhSansi\" class=\"logo\">"
                + "<p>Para asistencia inmediata, puede contactarnos a través de:</p>"
                + "<div class=\"contact-item\">"
                + "<img src=\"https://cdn-icons-png.flaticon.com/512/733/733585.png\" alt=\"WhatsApp\" class=\"contact-icon\">"
                + "<span>+591 71486093 (WhatsApp)</span>"
                + "</div>"
                + "<div class=\"contact-item\">"
                + "<img src=\"https://cdn-icons-png.flaticon.com/512/732/732200.png\" alt=\"Email\" class=\"contact-icon\">"
                + "<span>softcraft2024@gmail.com</span>"
                + "</div>"
                + "</div>"
                + "<div class=\"footer\">"
                + "<p>© 2024 Ohh-Sansi. Todos los derechos reservados.</p>"
                + "<p>Equipo de <a href=\"https://www.softcraftbol.com/\" style=\"color: #4f46e5; text-decoration: none;\">Softcraft</a></p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
    }

    private String buildAlertMessage(int diasRestantes, String tipoPeriodo) {
        String emoji = diasRestantes <= 1 ? "🚨" : "⚠️";
        String periodo = tipoPeriodo.equals("INSCRIPCION") ? "inscripción" : "período de ampliación";

        if (diasRestantes <= 0) {
            return emoji + " ¡ATENCIÓN! El " + periodo + " ha finalizado. Contacte con nosotros inmediatamente.";
        } else if (diasRestantes == 1) {
            return emoji + " ¡ÚLTIMO DÍA! Solo queda <strong>1 día</strong> para completar su pago en el " + periodo + ".";
        } else if (diasRestantes <= 3) {
            return emoji + " ¡Atención! Solo quedan <strong>" + diasRestantes + " días</strong> para completar su pago en el " + periodo + ".";
        } else {
            return emoji + " Quedan <strong>" + diasRestantes + " días</strong> para completar su pago en el " + periodo + ".";
        }
    }

    @Async
    public CompletableFuture<BulkEmailResult> sendBulkReminders(int diasAnticipacion) {
        BulkEmailResult result = new BulkEmailResult();

        try {
            List<ParticipanteReminderDto> participantes =
                    participanteDomainRepository.findParticipantesSinPagoProximoVencimiento(diasAnticipacion);

            result.setTotalEmails(participantes.size());

            for (ParticipanteReminderDto participante : participantes) {
                try {
                    sendReminderEmail(
                            participante.getEmail(),
                            participante.getNombreParticipante(),
                            participante.getCodUnique(),
                            participante.getDiasRestantes(),
                            participante.getTipoPeriodo()
                    );
                    result.incrementSuccessCount();

                    Thread.sleep(500);

                } catch (Exception e) {
                    result.incrementFailureCount();
                    result.addError("Error enviando a " + participante.getEmail() + ": " + e.getMessage());
                }
            }

        } catch (Exception e) {
            result.addError("Error general en envío masivo: " + e.getMessage());
        }

        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<BulkEmailResult> sendBulkRemindersByTipoPeriodo(String tipoPeriodo) {
        BulkEmailResult result = new BulkEmailResult();

        try {
            List<ParticipanteReminderDto> participantes =
                    participanteDomainRepository.findParticipantesSinPagoPorTipoPeriodo(tipoPeriodo);

            result.setTotalEmails(participantes.size());

            for (ParticipanteReminderDto participante : participantes) {
                try {
                    sendReminderEmail(
                            participante.getEmail(),
                            participante.getNombreParticipante(),
                            participante.getCodUnique(),
                            participante.getDiasRestantes(),
                            participante.getTipoPeriodo()
                    );
                    result.incrementSuccessCount();
                    Thread.sleep(500);

                } catch (Exception e) {
                    result.incrementFailureCount();
                    result.addError("Error enviando a " + participante.getEmail() + ": " + e.getMessage());
                }
            }

        } catch (Exception e) {
            result.addError("Error general en envío masivo: " + e.getMessage());
        }

        return CompletableFuture.completedFuture(result);
    }

}