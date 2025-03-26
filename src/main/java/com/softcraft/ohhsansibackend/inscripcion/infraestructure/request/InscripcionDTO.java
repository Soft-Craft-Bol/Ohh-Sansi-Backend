package com.softcraft.ohhsansibackend.inscripcion.infraestructure.request;

import java.util.List;
import java.util.Map;

public class InscripcionDTO {
    private ParticipanteDTO participante;
    private List<AreaCompetenciaDTO> areasCompetenciaEstudiante;
    private List<TutorDTO> tutores;

    public ParticipanteDTO getParticipante() {
        return participante;
    }

    public void setParticipante(ParticipanteDTO participante) {
        this.participante = participante;
    }

    public List<AreaCompetenciaDTO> getAreasCompetenciaEstudiante() {
        return areasCompetenciaEstudiante;
    }

    public void setAreasCompetenciaEstudiante(List<AreaCompetenciaDTO> areasCompetenciaEstudiante) {
        this.areasCompetenciaEstudiante = areasCompetenciaEstudiante;
    }

    public List<TutorDTO> getTutores() {
        return tutores;
    }

    public void setTutores(List<TutorDTO> tutores) {
        this.tutores = tutores;
    }
}
