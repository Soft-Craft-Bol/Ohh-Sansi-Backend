package com.softcraft.ohhsansibackend.participante.infraestructure.rest;

import com.softcraft.ohhsansibackend.config.TestSecurityConfig;
import com.softcraft.ohhsansibackend.exception.DuplicateResourceException;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParticipanteController.class)
@Import(TestSecurityConfig.class)
@ActiveProfiles("test")
class ParticipanteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParticipanteService participanteService;

    private Participante participante;
    private Map<String, Object> response;

    @BeforeEach
    void setUp() {
        participante = new Participante();
        participante.setIdInscripcion(1);
        participante.setIdParticipante(1);
        participante.setIdDepartamento(1);
        participante.setIdMunicipio(1);
        participante.setIdColegio(1);
        participante.setParticipanteHash("hashasasdasdjkhaksdhakjsdh");
        participante.setApellidoPaterno("Garcia");
        participante.setApellidoMaterno("Torrico");
        participante.setNombreParticipante("Ernesto");
        participante.setFechaNacimiento(new Date());
        participante.setCorreoElectronicoParticipante("alf@gmail.com");
        participante.setCarnetIdentidadParticipante(123456);
        response = new HashMap<>();
        response.put("status", "success");
        response.put("data", participante);
    }

    @Test
    void registerParticipant() throws Exception {
        when(participanteService.save(any(Participante.class))).thenReturn(response);

        mockMvc.perform(post("/participante/register-participant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                    {
                                      "idInscripcion": 1,
                                      "idDepartamento": 1,
                                      "idMunicipio": 1,
                                      "idColegio": 1,
                                      "participanteHash": "hashasasdasdjkhaksdhakjsdh",
                                      "apellidoPaterno": "Garcia",
                                      "apellidoMaterno": "Torrico",
                                      "nombreParticipante": "Ernesto",
                                      "fechaNacimiento": "2003-10-02",
                                      "correoElectronicoParticipante": "alf@gmail.com",
                                      "carnetIdentidadParticipante": 123456
                                    }
                                """
                        ))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.correoElectronicoParticipante").value("alf@gmail.com"));
    }

    @Test
    void registerParticipantWithDuplicateEmail() throws Exception {
        when(participanteService.save(any(Participante.class))).thenThrow(new DuplicateResourceException("Email o carnet de identidad ya registrados"));
        mockMvc.perform(post("/participante/register-participant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                {
                                  "idInscripcion": 1,
                                  "idDepartamento": 1,
                                  "idMunicipio": 1,
                                  "idColegio": 1,
                                  "apellidoPaterno": "Doe",
                                  "apellidoMaterno": "Smith",
                                  "nombreParticipante": "John",
                                  "fechaNacimiento": "2023-10-10",
                                  "correoElectronicoParticipante": "dyklanamin@gmail.com",
                                  "carnetIdentidadParticipante": 123456
                                }
                                """
                        ))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Email o carnet de identidad ya registrados"));
    }


}