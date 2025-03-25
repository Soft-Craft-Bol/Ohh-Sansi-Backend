package com.softcraft.ohhsansibackend.inscripcion.application.usecases;

import com.softcraft.ohhsansibackend.area.application.usecases.AreaService;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.AreaCompetenciaDTO;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.InscripcionDTO;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.ParticipanteDTO;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.TutorDTO;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteTutorService;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorAreaService;
import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorService;
import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InscripcionLargaServiceTest {

    @Mock
    private InscripcionService inscripcionService;
    @Mock
    private ParticipanteService participanteService;
    @Mock
    private ParticipanteTutorService participanteTutorService;
    @Mock
    private TutorService tutorService;
    @Mock
    private TutorAreaService tutorAreaService;
    @Mock
    private AreaService areaService;
    @Mock
    private InscripcionAreaService inscripcionAreaService;

    @InjectMocks
    private InscripcionLargaService inscripcionLargaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerInscripcion_success() {
        InscripcionDTO inscripcionDTO = new InscripcionDTO();
        ParticipanteDTO participanteDTO = new ParticipanteDTO();
        inscripcionDTO.setParticipante(participanteDTO);

        when(inscripcionService.createInscripcionAndReturnId(any(Inscripcion.class))).thenReturn(1);
        Participante participante = new Participante();
        participante.setIdParticipante(1);
        Map<String, Object> participanteResponse = new HashMap<>();
        participanteResponse.put("message", "Participante registrado exitosamente");
        when(participanteService.save(any(Participante.class))).thenAnswer(invocation -> {
            Participante p = invocation.getArgument(0);
            p.setIdParticipante(1); // Simulate setting the ID after saving
            return participanteResponse;
        });

        Map<String, Object> response = inscripcionLargaService.registerInscripcion(inscripcionDTO);

        assertTrue((Boolean) response.get("success"));
        assertEquals("Inscripción registrada exitosamente", response.get("message"));
        assertEquals(1, response.get("inscripcionId"));
    }

    @Test
    void registerInscripcion_noParticipante_throwsException() {
        InscripcionDTO inscripcionDTO = new InscripcionDTO();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            inscripcionLargaService.registerInscripcion(inscripcionDTO);
        });

        assertEquals("Datos del participante son requeridos", exception.getMessage());
    }

    @Test
    void registerInscripcion_participanteNotRegistered_throwsException() {
        InscripcionDTO inscripcionDTO = new InscripcionDTO();
        ParticipanteDTO participanteDTO = new ParticipanteDTO();
        inscripcionDTO.setParticipante(participanteDTO);

        when(inscripcionService.createInscripcionAndReturnId(any(Inscripcion.class))).thenReturn(1);
        Map<String, Object> participanteResponse = new HashMap<>();
        participanteResponse.put("message", "Participante registrado exitosamente");
        when(participanteService.save(any(Participante.class))).thenReturn(participanteResponse);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            inscripcionLargaService.registerInscripcion(inscripcionDTO);
        });

        assertEquals("Participante no fue registrado correctamente", exception.getMessage());
    }

    @Test
    void registerInscripcion_withAreasCompetencia() {
        InscripcionDTO inscripcionDTO = new InscripcionDTO();
        ParticipanteDTO participanteDTO = new ParticipanteDTO();
        inscripcionDTO.setParticipante(participanteDTO);
        AreaCompetenciaDTO areaCompetenciaDTO = new AreaCompetenciaDTO();
        areaCompetenciaDTO.setIdArea(1);
        inscripcionDTO.setAreasCompetenciaEstudiante(Collections.singletonList(areaCompetenciaDTO));

        when(inscripcionService.createInscripcionAndReturnId(any(Inscripcion.class))).thenReturn(1);
        Participante participante = new Participante();
        participante.setIdParticipante(1);
        Map<String, Object> participanteResponse = new HashMap<>();
        participanteResponse.put("message", "Participante registrado exitosamente");
        when(participanteService.save(any(Participante.class))).thenAnswer(invocation -> {
            Participante p = invocation.getArgument(0);
            p.setIdParticipante(1); // Simulate setting the ID after saving
            return participanteResponse;
        });

        Map<String, Object> response = inscripcionLargaService.registerInscripcion(inscripcionDTO);

        verify(inscripcionAreaService, times(1)).createInscripcionArea(1, 1);
        assertTrue((Boolean) response.get("success"));
    }

    @Test
    void registerInscripcion_withTutores() {
        InscripcionDTO inscripcionDTO = new InscripcionDTO();
        ParticipanteDTO participanteDTO = new ParticipanteDTO();
        inscripcionDTO.setParticipante(participanteDTO);
        TutorDTO tutorDTO = new TutorDTO();
        tutorDTO.setEmailTutor("test@example.com");
        tutorDTO.setCarnetIdentidadTutor(123456);
        inscripcionDTO.setTutores(Collections.singletonList(tutorDTO));

        when(inscripcionService.createInscripcionAndReturnId(any(Inscripcion.class))).thenReturn(1);
        Participante participante = new Participante();
        participante.setIdParticipante(1);
        Map<String, Object> participanteResponse = new HashMap<>();
        participanteResponse.put("message", "Participante registrado exitosamente");
        when(participanteService.save(any(Participante.class))).thenAnswer(invocation -> {
            Participante p = invocation.getArgument(0);
            p.setIdParticipante(1); // Simulate setting the ID after saving
            return participanteResponse;
        });
        when(tutorService.findByEmailOrCarnet(anyString(), anyInt())).thenReturn(null);
        Tutor tutor = new Tutor();
        tutor.setIdTutor(1L);
        Map<String, Object> tutorResponse = new HashMap<>();
        tutorResponse.put("message", "Tutor registrado exitosamente");
        when(tutorService.save(any(Tutor.class))).thenAnswer(invocation -> {
            Tutor t = invocation.getArgument(0);
            t.setIdTutor(1L); // Simulate setting the ID after saving
            return tutorResponse;
        });

        Map<String, Object> response = inscripcionLargaService.registerInscripcion(inscripcionDTO);

        verify(tutorService, times(1)).save(any(Tutor.class));
        verify(participanteTutorService, times(1)).createParticipanteTutor(anyInt(), anyInt(), anyInt());
        assertTrue((Boolean) response.get("success"));
    }
}