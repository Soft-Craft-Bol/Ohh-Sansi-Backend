package com.softcraft.ohhsansibackend.inscripcion.infraestructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softcraft.ohhsansibackend.config.TestSecurityConfig;
import com.softcraft.ohhsansibackend.inscripcion.application.usecases.InscripcionLargaService;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.AreaCompetenciaDTO;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.InscripcionDTO;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.ParticipanteDTO;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.TutorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(InscripcionLargaController.class)
@Import(TestSecurityConfig.class)
@ActiveProfiles("test")
class InscripcionLargaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InscripcionLargaService inscripcionLargaService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private java.sql.Date parseDate(String date) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(date);
        return new java.sql.Date(parsed.getTime());
    }

    @Test
    void registerInscripcion_success() throws Exception {
        InscripcionDTO inscripcionDTO = new InscripcionDTO();
        inscripcionDTO.setParticipante(new ParticipanteDTO(2432, 2428, 2424, 15, "Zeballos", "Peredo", "Dylan Amin", parseDate("2003-10-02"), "dyla2n@email.com", 66663333));
        inscripcionDTO.setAreasCompetenciaEstudiante(List.of(new AreaCompetenciaDTO(1), new AreaCompetenciaDTO(34)));
        inscripcionDTO.setTutores(List.of(
                new TutorDTO(3, "padre1@email.com", "Juan", "Zeballos", 77777771, 123456, 1),
                new TutorDTO(3, "madre1@email.com", "María", "Peredo", 88888881, 654321, 34)
        ));

        Map<String, Object> response = Map.of(
                "success", true,
                "message", "Inscripción registrada exitosamente",
                "inscripcionId", 1,
                "participanteId", 1
        );

        when(inscripcionLargaService.registerInscripcion(any(InscripcionDTO.class))).thenReturn(response);

        mockMvc.perform(post("/inscripcion/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inscripcionDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Inscripción registrada exitosamente"))
                .andExpect(jsonPath("$.inscripcionId").value(1))
                .andExpect(jsonPath("$.participanteId").value(1));
    }

    @Test
    void registerInscripcion_noParticipante_throwsException() throws Exception {
        InscripcionDTO inscripcionDTO = new InscripcionDTO();

        when(inscripcionLargaService.registerInscripcion(any(InscripcionDTO.class)))
                .thenThrow(new IllegalArgumentException("Datos del participante son requeridos"));

        mockMvc.perform(post("/inscripcion/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inscripcionDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Datos del participante son requeridos"));
    }

    @Test
    void registerInscripcion_participanteNotRegistered_throwsException() throws Exception {
        InscripcionDTO inscripcionDTO = new InscripcionDTO();
        inscripcionDTO.setParticipante(new ParticipanteDTO(2432, 2428, 2424, 15, "Zeballos", "Peredo", "Dylan Amin", parseDate("2003-10-02"), "dyla2n@email.com", 66663333));

        when(inscripcionLargaService.registerInscripcion(any(InscripcionDTO.class)))
                .thenThrow(new IllegalStateException("Participante no fue registrado correctamente"));

        mockMvc.perform(post("/inscripcion/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inscripcionDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Participante no fue registrado correctamente"));
    }

    @Test
    void registerInscripcion_withAreasCompetencia() throws Exception {
        InscripcionDTO inscripcionDTO = new InscripcionDTO();
        inscripcionDTO.setParticipante(new ParticipanteDTO(2432, 2428, 2424, 15, "Zeballos", "Peredo", "Dylan Amin", parseDate("2003-10-02"), "dyla2n@email.com", 66663333));
        inscripcionDTO.setAreasCompetenciaEstudiante(List.of(new AreaCompetenciaDTO(1), new AreaCompetenciaDTO(34)));

        Map<String, Object> response = Map.of(
                "success", true,
                "message", "Inscripción registrada exitosamente",
                "inscripcionId", 1,
                "participanteId", 1
        );

        when(inscripcionLargaService.registerInscripcion(any(InscripcionDTO.class))).thenReturn(response);

        mockMvc.perform(post("/inscripcion/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inscripcionDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Inscripción registrada exitosamente"))
                .andExpect(jsonPath("$.inscripcionId").value(1))
                .andExpect(jsonPath("$.participanteId").value(1));
    }

    @Test
    void registerInscripcion_withTutores() throws Exception {
        InscripcionDTO inscripcionDTO = new InscripcionDTO();
        inscripcionDTO.setParticipante(new ParticipanteDTO(2432, 2428, 2424, 15, "Zeballos", "Peredo", "Dylan Amin", parseDate("2003-10-02"), "dyla2n@email.com", 66663333));
        inscripcionDTO.setTutores(List.of(
                new TutorDTO(3, "padre1@email.com", "Juan", "Zeballos", 77777771, 123456, 1),
                new TutorDTO(3, "madre1@email.com", "María", "Peredo", 88888881, 654321, 34)
        ));

        Map<String, Object> response = Map.of(
                "success", true,
                "message", "Inscripción registrada exitosamente",
                "inscripcionId", 1,
                "participanteId", 1
        );

        when(inscripcionLargaService.registerInscripcion(any(InscripcionDTO.class))).thenReturn(response);

        mockMvc.perform(post("/inscripcion/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inscripcionDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Inscripción registrada exitosamente"))
                .andExpect(jsonPath("$.inscripcionId").value(1))
                .andExpect(jsonPath("$.participanteId").value(1));
    }
}