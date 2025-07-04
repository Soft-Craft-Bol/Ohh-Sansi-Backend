package com.softcraft.ohhsansibackend.participante.infraestructure.rest;

import com.softcraft.ohhsansibackend.participante.application.ports.ParticipanteAdapter;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteCatalogoInscriptionService;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteTutorService;
import com.softcraft.ohhsansibackend.participante.domain.dto.ParticipanteAreasDTO;
import com.softcraft.ohhsansibackend.participante.domain.dto.ParticipanteResumenDTO;
import com.softcraft.ohhsansibackend.participante.domain.dto.ParticipanteTutorAreaDTO;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.infraestructure.request.AreaCatalogoDTO;
import com.softcraft.ohhsansibackend.participante.infraestructure.request.ParticipanteVerifyDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {
    private final ParticipanteService participanteService;
    private final ParticipanteTutorService participanteTutorService;
    private final ParticipanteCatalogoInscriptionService participanteCatalogoInscriptionService;
    private final ParticipanteAdapter participanteAdapter;

    @Autowired
    public ParticipanteController(ParticipanteService participanteService, ParticipanteTutorService participanteTutorService, ParticipanteCatalogoInscriptionService participanteCatalogoInscriptionService, ParticipanteAdapter participanteAdapter) {
        this.participanteService = participanteService;
        this.participanteTutorService = participanteTutorService;
        this.participanteCatalogoInscriptionService = participanteCatalogoInscriptionService;
        this.participanteAdapter = participanteAdapter;
    }

    @PostMapping("/register-participant")
    public ResponseEntity<Map<String, Object>> registerParticipant(@Valid @RequestBody Participante participante) {
        Map<String, Object> response = participanteService.save(participante);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyParticipante(@RequestBody ParticipanteVerifyDTO request) {
        Participante participante = participanteService.findByCarnetIdentidadService(request.getCi());

        if (participante == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participante no encontrado.");
        }

        if (!participante.getEmailParticipante().equalsIgnoreCase(request.getValuePermit())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permiso inválido.");
        }

        return ResponseEntity.ok(participante);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = participanteService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = participanteService.findAll();
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Map<String, Object>> findByEmail(@PathVariable String email) {
        Map<String, Object> response = participanteService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/carnet/{carnetIdentidad}")
    public ResponseEntity<Participante> findByCarnetIdentidad(@PathVariable int carnetIdentidad) {
        Participante response = participanteService.findByCarnetIdentidadService(carnetIdentidad);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/register-participant-catalogo/{ciParticipante}")
    public ResponseEntity<Map<String, Object>> registerParticipantCatalogo(
            @PathVariable int ciParticipante,
            @RequestBody @Valid List<AreaCatalogoDTO> areaCatalogos) {
        Map<String, Object> response = participanteCatalogoInscriptionService.registerParticipantWithCatalogoComposition(ciParticipante, areaCatalogos);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{carnetIdentidad}/areas")
    public ResponseEntity<ParticipanteAreasDTO> getAreasByCarnet(@PathVariable int carnetIdentidad) {
        return participanteCatalogoInscriptionService.execute(carnetIdentidad)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/carnet/{carnetIdentidad}/areas-tutores")
    public ResponseEntity<ParticipanteTutorAreaDTO> getAreasTutoresByCarnet(@PathVariable int carnetIdentidad) {
        return participanteCatalogoInscriptionService.getTutorArea(carnetIdentidad)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/carnet/{carnetIdentidad}/datos")
    public Optional<ParticipanteResumenDTO> getAreasResumenByCarnet(@PathVariable int carnetIdentidad) {
        return participanteAdapter.obtenerParticipanteResumenPorCi(carnetIdentidad);
    }
    @PostMapping("/register-multiple-participant")
    public ResponseEntity<Map<String, Object>> registerMultipleParticipant(
            @RequestBody List<Participante> participantes
    ){
        return ResponseEntity.ok(participanteService.saveMultipleParticipant(participantes));
    }
}