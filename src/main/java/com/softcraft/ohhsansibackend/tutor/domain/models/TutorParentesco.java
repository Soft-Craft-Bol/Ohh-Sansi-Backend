package com.softcraft.ohhsansibackend.tutor.domain.models;

import jakarta.validation.constraints.NotNull;

public class TutorParentesco {
        private Long idTutorParentesco;

        @NotNull(message = "El nombre del parentesco de tutor no puede ser nulo")
        private String parentesco;

        public Long getIdTutorParentesco() {
            return idTutorParentesco;
        }

        public void setIdTutorParentesco(Long idTutorParentesco) {
            this.idTutorParentesco = idTutorParentesco;
        }

        public String getParentesco() {
            return parentesco;
        }

        public void setParentesco(String parentesco) {this.parentesco = parentesco;}
}
