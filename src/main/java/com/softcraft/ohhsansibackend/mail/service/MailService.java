package com.softcraft.ohhsansibackend.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Value("${spring.mail.username}")
//    private String fromEmail;
//
//    //Datos del correo enviado
//    private final String subject = "Inscripción exitosa";
//    private final String content = "<html>"
//            + "<head>"
//            + "<style>"
//            + "body {"
//            + "background-color: #f1f5f9;"
//            + "font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Ubuntu, sans-serif;"
//            + "}"
//            + ".container {"
//            + "background-color: #ffffff;"
//            + "margin: 0 auto;"
//            + "padding: 40px;"
//            + "max-width: 600px;"
//            + "text-align: left;"
//            + "}"
//            + ".box {"
//            + "padding: 0 20px;"
//            + "}"
//            + ".title {"
//            + "font-size: 24px;"
//            + "font-weight: bold;"
//            + "margin-bottom: 10px;"
//            + "}"
//            + ".paragraph {"
//            + "color: #333;"
//            + "font-size: 16px;"
//            + "line-height: 24px;"
//            + "}"
//            + ".button {"
//            + "background-color: #000;"
//            + "border-radius: 6px;"
//            + "color: #fff;"
//            + "font-size: 16px;"
//            + "font-weight: bold;"
//            + "text-decoration: none;"
//            + "text-align: center;"
//            + "color: #fff;"
//            + "display: inline-block;"
//            + "padding: 12px 24px;"
//            + "margin: 20px 0;"
//            + "cursor: pointer;"
//            + "}"
//            + ".hr {"
//            + "border-color: #e6ebf1;"
//            + "margin: 20px 0;"
//            + "}"
//            + ".contact-info {"
//            + "display: flex;"
//            + "align-items: center;"
//            + "gap: 10px;"
//            + "font-size: 16px;"
//            + "}"
//            + ".icon {"
//            + "width: 20px;"
//            + "height: 20px;"
//            + "}"
//            + "</style>"
//            + "</head>"
//            + "<body>"
//            + "<div class=\"container\">"
//            + "<div class=\"box\">"
//            + "<p class=\"title\">Inscripción enviada</p>"
//            + "<p class=\"paragraph\">"
//            + "Se ha procesado su solicitud para la inscripción en el evento Ohh-Sansi."
//            + "</p>"
//            + "<a href=\"#\" class=\"button\">Descargar orden de pago</a>"
//            + "<img src=\"https://tecnocursosedu.com/wp-content/uploads/2024/10/ohsansi.jpg\" alt=\"Logo OhSansi\" />"
//            + "<hr class=\"hr\" />"
//            + "<p class=\"paragraph\">"
//            + "Se publicará más información pronto. Mientras tanto, si tienes alguna pregunta, no dudes en comunicarte con nosotros a través de nuestras redes:"
//            + "</p>"
//            + "<p class=\"contact-info\">"
//            + "<img src=\"https://cdn-icons-png.flaticon.com/512/733/733585.png\" alt=\"WhatsApp\" class=\"icon\" /> +591 71486093"
//            + "</p>"
//            + "<p class=\"contact-info\">"
//            + "<img src=\"https://cdn-icons-png.flaticon.com/512/732/732200.png\" alt=\"Email\" class=\"icon\" /> softcraft2024@gmail.com"
//            + "</p>"
//            + "<p class=\"paragraph\">¡Te esperamos!</p>"
//            + "<p class=\"paragraph\">"
//            + "El equipo de <a href=\"https://www.softcraftbol.com/\">Softcraft</a>"
//            + "</p>"
//            + "</div>"
//            + "</div>"
//            + "</body>"
//            + "</html>";
//
//    public void sendEmail(String to) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(content, true);
//        helper.setFrom(fromEmail);
//
//        mailSender.send(message);
//
//    }
}