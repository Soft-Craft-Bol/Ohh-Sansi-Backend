package com.softcraft.ohhsansibackend.inscripcion.infraestructure.dto.inscripcionmasiva;

import java.util.List;

public class ExcelInscriptionDTO {
    private MasiveParticipanteDTO participante;
    private MasiveTutorDTO tutor;
    private MasiveAreaDTO areas;
    private List<MasiveProfesorDTO> profesor;

    public ExcelInscriptionDTO() {
    }

    public ExcelInscriptionDTO(MasiveParticipanteDTO participante, MasiveTutorDTO tutor, MasiveAreaDTO areas, List<MasiveProfesorDTO> profesor) {
        this.participante = participante;
        this.tutor = tutor;
        this.areas = areas;
        this.profesor = profesor;
    }

    public MasiveParticipanteDTO getParticipante() {
        return participante;
    }

    public void setParticipante(MasiveParticipanteDTO participante) {
        this.participante = participante;
    }

    public MasiveTutorDTO getTutor() {
        return tutor;
    }

    public void setTutor(MasiveTutorDTO tutor) {
        this.tutor = tutor;
    }

    public MasiveAreaDTO getAreas() {
        return areas;
    }

    public void setAreas(MasiveAreaDTO areas) {
        this.areas = areas;
    }

    public List<MasiveProfesorDTO> getProfesor() {
        return profesor;
    }

    public void setProfesor(List<MasiveProfesorDTO> profesor) {
        this.profesor = profesor;
    }
}
