package com.softcraft.ohhsansibackend.participante.domain.dto;


import java.util.List;


public class ParticipanteTutorAreaDTO {
    private Integer idParticipante;
    private Integer idInscripcion;
    private String nombreCompleto;
    private Long carnetIdentidad;
    private String grado;
    private List<Integer> idsAreas;
    private List<String> nombresAreas;
    private List<AreaTutorDTO> areasTutores;

    public ParticipanteTutorAreaDTO() {

    }


    public static class AreaTutorDTO {
        private Integer idArea;
        private String nombreArea;
        private Long carnetIdentidadTutor;
        private Integer idTutor;
        private String nombresTutor;
        private String apellidosTutor;
        private String emailTutor;
        private String telefono;

        public AreaTutorDTO() {

        }

        public Integer getIdArea() {
            return idArea;
        }

        public void setIdArea(Integer idArea) {
            this.idArea = idArea;
        }

        public String getNombreArea() {
            return nombreArea;
        }

        public void setNombreArea(String nombreArea) {
            this.nombreArea = nombreArea;
        }

        public Long getCarnetIdentidadTutor() {
            return carnetIdentidadTutor;
        }
        public void setCarnetIdentidadTutor(Long carnetIdentidadTutor) {
            this.carnetIdentidadTutor = carnetIdentidadTutor;
        }

        public Integer getIdTutor() {
            return idTutor;
        }

        public void setIdTutor(Integer idTutor) {
            this.idTutor = idTutor;
        }

        public String getNombresTutor() {
            return nombresTutor;
        }

        public void setNombresTutor(String nombresTutor) {
            this.nombresTutor = nombresTutor;
        }

        public String getApellidosTutor() {
            return apellidosTutor;
        }

        public void setApellidosTutor(String apellidosTutor) {
            this.apellidosTutor = apellidosTutor;
        }

        public String getEmailTutor() {
            return emailTutor;
        }

        public void setEmailTutor(String emailTutor) {
            this.emailTutor = emailTutor;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
    }

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
        this.idParticipante = idParticipante;
    }

    public Integer getIdInscripcion() {
        return idInscripcion;
    }
    public void setIdInscripcion(Integer idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Long getCarnetIdentidad() {
        return carnetIdentidad;
    }

    public void setCarnetIdentidad(Long carnetIdentidad) {
        this.carnetIdentidad = carnetIdentidad;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public List<Integer> getIdsAreas() {
        return idsAreas;
    }

    public void setIdsAreas(List<Integer> idsAreas) {
        this.idsAreas = idsAreas;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public List<AreaTutorDTO> getAreasTutores() {
        return areasTutores;
    }

    public void setAreasTutores(List<AreaTutorDTO> areasTutores) {
        this.areasTutores = areasTutores;
    }

    public List<String> getNombresAreas() {
        return nombresAreas;
    }

    public void setNombresAreas(List<String> nombresAreas) {
        this.nombresAreas = nombresAreas;
    }
}