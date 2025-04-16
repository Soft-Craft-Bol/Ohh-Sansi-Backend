package com.softcraft.ohhsansibackend.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
    //Datos del correo enviado
    private final String subject = "Inscripción exitosa";
    private String urlOrdenPago = "http://localhost:5173/orden-de-pago";

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private String buildEmailContent(String codUnique) {
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

    @Async
    public void sendEmailAsync(String to, String codUnique) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String dynamicContent = buildEmailContent(codUnique);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(dynamicContent, true);
        helper.setFrom(fromEmail);

        mailSender.send(message);

    }
}