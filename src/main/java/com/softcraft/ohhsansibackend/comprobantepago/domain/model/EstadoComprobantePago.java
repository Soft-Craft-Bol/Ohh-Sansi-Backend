package com.softcraft.ohhsansibackend.comprobantepago.domain.model;

public enum EstadoComprobantePago {
    ACEPTADA(1),
    PENDIENTE(2),
    RECHAZADA(3);

    private final int id;

    EstadoComprobantePago(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public static EstadoComprobantePago fromId(int id) {
        for (EstadoComprobantePago estado : values()) {
            if (estado.getId() == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + id);
    }
}
