package com.softcraft.ohhsansibackend.comprobantepago.domain.model;

public enum EstadoComprobantePagoEnum {
    ACEPTADA(1),
    PENDIENTE(2),
    RECHAZADA(3);

    private final int id;

    EstadoComprobantePagoEnum(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public static EstadoComprobantePagoEnum fromId(int id) {
        for (EstadoComprobantePagoEnum estado : values()) {
            if (estado.getId() == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + id);
    }
}
