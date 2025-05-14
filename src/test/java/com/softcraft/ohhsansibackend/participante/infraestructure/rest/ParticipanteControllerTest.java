package com.softcraft.ohhsansibackend.participante.infraestructure.rest;

import com.softcraft.ohhsansibackend.config.TestSecurityConfig;
import com.softcraft.ohhsansibackend.exception.DuplicateResourceException;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
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
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
        //participante.setCorreoElectronicoParticipante("alf@gmail.com");
        participante.setCarnetIdentidadParticipante(1234561);
        response = new HashMap<>();
        response.put("participante", participante);
    }

    @Test
    void registerParticipant() throws Exception {
        when(participanteService.save(any(Participante.class))).thenReturn(Map.of("message", "Participante registrado exitosamente"));

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
                .andExpect(jsonPath("$.message").value("Participante registrado exitosamente"));
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

    @Test
    void registerParticipantWithMultipleNullFields() throws Exception {
        mockMvc.perform(post("/participante/register-participant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                    {
                                      "idInscripcion": 1,
                                      "idDepartamento": 1,
                                      "idMunicipio": 1,
                                      "idColegio": null,
                                      "participanteHash": "hash123131",
                                      "apellidoPaterno": null,
                                      "apellidoMaterno": "Peredo",
                                      "nombreParticipante": "Dylan Amin",
                                      "fechaNacimiento": "2003-10-02",
                                      "correoElectronicoParticipante": "dyklanamin@gmail.com",
                                      "carnetIdentidadParticipante": 121212312
                                    }
                                """
                        ))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.errors.apellidoPaterno").value("apellidoPaterno es requerido"))
                .andExpect(jsonPath("$.errors.idColegio").value("idColegio es requerido"))
                .andExpect(jsonPath("$.details").value("uri=/participante/register-participant"));
    }

    @Test
    void findById_Positive() throws Exception {
        when(participanteService.findById(anyLong())).thenReturn(response);

        mockMvc.perform(get("/participante/{id}", 8L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findById_Negative() throws Exception {
        when(participanteService.findById(anyLong())).thenThrow(new ResourceNotFoundException("Participante no encontrado"));

        mockMvc.perform(get("/participante/{id}", 28L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Recurso no econtrado"));
    }

    @Test
    void findAll_Positive() throws Exception {
        Map<String, Object> response = new HashMap<>();
        response.put("participantes", List.of(participante));
        when(participanteService.findAll()).thenReturn(response);

        mockMvc.perform(get("/participante")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participantes").isArray())
                .andExpect(jsonPath("$.participantes[0].correoElectronicoParticipante").value("alf@gmail.com"));
    }

    @Test
    void findAll_Empty() throws Exception {
        Map<String, Object> response = new HashMap<>();
        when(participanteService.findAll()).thenReturn(response);

        mockMvc.perform(get("/participante")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void findByEmail_Positive() throws Exception {
        when(participanteService.findByEmail("alf@gmail.com")).thenReturn(response);

        mockMvc.perform(get("/participante/email/{email}", "alf@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participante.correoElectronicoParticipante").value("alf@gmail.com"));
    }

    @Test
    void findByEmail_Negative() throws Exception {
        when(participanteService.findByEmail("nonexistent@gmail.com")).thenThrow(new ResourceNotFoundException("Participante no encontrado"));

        mockMvc.perform(get("/participante/email/{email}", "nonexistent@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Recurso no econtrado"));
    }

    @Test
    void findByCarnetIdentidad_Positive() throws Exception {
        when(participanteService.findByCarnetIdentidad(1234561)).thenReturn(response);

        mockMvc.perform(get("/participante/carnet/{carnetIdentidad}", 1234561)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participante.carnetIdentidadParticipante").value(1234561));
    }

    @Test
    void findByCarnetIdentidad_Negative() throws Exception {
        when(participanteService.findByCarnetIdentidad(9999999)).thenThrow(new ResourceNotFoundException("Participante no encontrado"));

        mockMvc.perform(get("/participante/carnet/{carnetIdentidad}", 9999999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Recurso no econtrado"));
    }
}