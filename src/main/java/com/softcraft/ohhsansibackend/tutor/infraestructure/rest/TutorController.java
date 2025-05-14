package com.softcraft.ohhsansibackend.tutor.infraestructure.rest;

import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.infraestructure.request.ParticipanteVerifyDTO;
import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorService;
import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import com.softcraft.ohhsansibackend.tutor.domain.models.TutorRegistrationRequest;
import com.softcraft.ohhsansibackend.tutor.infraestructure.request.TutorVerifyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tutor")
public class TutorController {
    private final TutorService tutorService;
    @Autowired
    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyTutor(@RequestBody TutorVerifyDTO request) {
        Tutor tutor = tutorService.findByCarnet("", request.getCi());

        if (tutor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tutor no encontrado.");
        }

        if (!tutor.getEmailTutor().equalsIgnoreCase(request.getValuePermit())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permiso inválido.");
        }

        return ResponseEntity.ok(tutor);
    }

    @PostMapping("/{carnetParticipante}")
    public ResponseEntity<Map<String, Object>> save(
            @PathVariable int carnetParticipante,
            @RequestBody TutorRegistrationRequest request) {
        return ResponseEntity.ok(tutorService.save(request.getTutors(), carnetParticipante, request.getIdTutorParentesco()));
    }

    @GetMapping("/{idTutor}")
    public ResponseEntity<Map<String,Object>> findByIdTutor(
            @PathVariable Integer idTutor
    ) {
        return ResponseEntity.ok(tutorService.findByIdTutor(idTutor));
    }

    @GetMapping("/byCi/{ciTutor}")
    public  ResponseEntity<Tutor> findByCarnet(
            @PathVariable Integer ciTutor
    ) {
        return ResponseEntity.ok(tutorService.findByCarnet("", ciTutor));
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> findAllTutor() {
        return ResponseEntity.ok(tutorService.findAllTutor());
    }

    @PostMapping("/academico/{carnetParticipante}/{idArea}")
    public ResponseEntity<Map<String, Object>> registrarTutorAcademico(
            @PathVariable int carnetParticipante,
            @PathVariable int idArea,
            @RequestBody Tutor tutor) {
        return ResponseEntity.ok(tutorService.registrarTutorAcademico(tutor, carnetParticipante, idArea));
    }
}
