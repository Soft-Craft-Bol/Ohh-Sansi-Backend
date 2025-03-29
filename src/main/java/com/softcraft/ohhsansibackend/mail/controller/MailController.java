package com.softcraft.ohhsansibackend.mail.controller;

import com.softcraft.ohhsansibackend.mail.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class MailController {

    @Autowired
    private MailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String content) {
        try {
            emailService.sendEmail(to, subject, content);
            return "Correo enviado con éxito";
        } catch (MessagingException e) {
            return "Error al enviar el correo: " + e.getMessage();
        }
    }
}

