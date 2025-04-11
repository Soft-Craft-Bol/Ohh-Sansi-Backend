package com.softcraft.ohhsansibackend.tutor.infraestructure.rest;


import com.softcraft.ohhsansibackend.config.TestSecurityConfig;
import com.softcraft.ohhsansibackend.exception.DuplicateResourceException;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorService;
import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TutorController.class)
@Import(TestSecurityConfig.class)
@ActiveProfiles("test")
class TutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TutorService tutorService;

    private Tutor tutor;
    private Map<String, Object> response;

    @BeforeEach
    void setUp() {
        tutor = new Tutor();
        tutor.setIdTutor(1L);
        tutor.setIdTipoTutor(1);
        tutor.setEmailTutor("asd@asd.com");
        tutor.setNombresTutor("Juan");
        tutor.setApellidosTutor("Perez");
        tutor.setTelefono(123456789);
        tutor.setCarnetIdentidadTutor(987654321);

        response = Map.of("tutor", tutor);
    }

    @Test
    void saveTutor_Positive() throws Exception {
        when(tutorService.save(any(Tutor.class),1)).thenReturn(Map.of("message", "Tutor registrado exitosamente"));

        mockMvc.perform(post("/tutor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                {
                                  "idTipoTutor": 1,
                                  "emailTutor": "asd@asd.com",
                                  "nombresTutor": "Juan",
                                  "apellidosTutor": "Perez",
                                  "telefono": 123456789,
                                  "carnetIdentidadTutor": 987654321
                                }
                                """
                        ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Tutor registrado exitosamente"));
    }

//    @Test
//    void saveTutorWithDuplicateEmailOrCarnet_Negative() throws Exception {
//        when(tutorService.save(any(Tutor.class))).thenThrow(new DuplicateResourceException("Email o carnet de identidad del tutor ya registrados"));
//
//        mockMvc.perform(post("/tutor")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(
//                                """
//                                {
//                                  "idTipoTutor": 1,
//                                  "emailTutor": "asd@asd.com",
//                                  "nombresTutor": "Juan",
//                                  "apellidosTutor": "Perez",
//                                  "telefono": 123456789,
//                                  "carnetIdentidadTutor": 987654321
//                                }
//                                """
//                        ))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.message").value("Email o carnet de identidad del tutor ya registrados"));
//    }

    @Test
    void findByIdTutor_Positive() throws Exception {
        when(tutorService.findByIdTutor(anyInt())).thenReturn(response);

        mockMvc.perform(get("/tutor/{idTutor}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tutor.emailTutor").value("tutor@example.com"));
    }

    @Test
    void findByIdTutor_Negative() throws Exception {
        when(tutorService.findByIdTutor(anyInt())).thenThrow(new ResourceNotFoundException("Recurso no econtrado"));

        mockMvc.perform(get("/tutor/{idTutor}", 99)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Recurso no econtrado"));
    }

    @Test
    void findAllTutor_Positive() throws Exception {
        when(tutorService.findAllTutor()).thenReturn(Map.of("tutores", List.of(tutor)));

        mockMvc.perform(get("/tutor")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tutores").isArray())
                .andExpect(jsonPath("$.tutores[0].emailTutor").value("tutor@example.com"));
    }

    @Test
    void findAllTutor_Empty() throws Exception {
        when(tutorService.findAllTutor()).thenThrow(new ResourceNotFoundException("Recurso no econtrado"));

        mockMvc.perform(get("/tutor")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Recurso no econtrado"));
    }
}