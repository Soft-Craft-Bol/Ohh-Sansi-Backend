package com.softcraft.ohhsansibackend.inscripcion.infraestructure.request;

import java.util.Map;

public class InscripcionDTO {
    private ParticipanteDTO participante;
    private Map<String, AreaCompetenciaDTO> areasCompetenciaEstudiante;
    private Map<String, TutorDTO> tutores;

    public ParticipanteDTO getParticipante() {
        return participante;
    }

    public void setParticipante(ParticipanteDTO participante) {
        this.participante = participante;
    }

    public Map<String, AreaCompetenciaDTO> getAreasCompetenciaEstudiante() {
        return areasCompetenciaEstudiante;
    }

    public void setAreasCompetenciaEstudiante(Map<String, AreaCompetenciaDTO> areasCompetenciaEstudiante) {
        this.areasCompetenciaEstudiante = areasCompetenciaEstudiante;
    }

    public Map<String, TutorDTO> getTutores() {
        return tutores;
    }

    public void setTutores(Map<String, TutorDTO> tutores) {
        this.tutores = tutores;
    }

    public Map<String, Integer> getTutorAreaDecompetencia() {
        return tutorAreaDecompetencia;
    }

    public void setTutorAreaDecompetencia(Map<String, Integer> tutorAreaDecompetencia) {
        this.tutorAreaDecompetencia = tutorAreaDecompetencia;
    }

    private Map<String, Integer> tutorAreaDecompetencia;
}
