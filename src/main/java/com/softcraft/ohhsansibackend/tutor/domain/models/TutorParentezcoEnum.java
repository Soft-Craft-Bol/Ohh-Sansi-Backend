package com.softcraft.ohhsansibackend.tutor.domain.models;

import com.softcraft.ohhsansibackend.comprobantepago.domain.model.EstadoComprobantePagoEnum;

public enum TutorParentezcoEnum {
    ACEPTADA(1),
    PENDIENTE(2),
    RECHAZADA(3);

    private final int id;

    TutorParentezcoEnum(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public static TutorParentezcoEnum fromId(int id) {
        for (TutorParentezcoEnum parentezco : values()) {
            if (parentezco.getId() == id) {
                return parentezco;
            }
        }
        throw new IllegalArgumentException("Parezco no válido: " + id);
    }
}
