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

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    //Datos del correo enviado
    private final String subject = "Inscripción exitosa";
    private final String content = "<html><body style=\"font-family: Arial, sans-serif;\">"
            + "<h1 style=\"color: #4CAF50;\">¡Hola!</h1>"
            + "<p>Este es un correo de prueba <strong>con HTML</strong> desde Spring Boot con un contenido predefinido.</p>"
            + "<p>Este contenido siempre será el mismo, solo cambia el destinatario.</p>"
            + "</body></html>";

    public void sendEmail(String to) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        helper.setFrom(fromEmail);

        mailSender.send(message);

    }
}