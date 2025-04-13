package com.softcraft.ohhsansibackend.mail.controller;

import com.softcraft.ohhsansibackend.mail.dto.MailRequest;
import com.softcraft.ohhsansibackend.mail.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class MailController {

  @Autowired
   private MailService mailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody MailRequest emailRequest) {
        try {
            //en caso de pruebas o enviar otro correo con el mismo codigo
            mailService.sendEmail(
                    emailRequest.getTo(),emailRequest.getCodigoUnico()
            );
            return "Correo enviado con éxito";
        } catch (MessagingException e) {
            return "Error al enviar el correo: " + e.getMessage();
        }
    }
}
