package com.softcraft.ohhsansibackend.ordenPago.domain.repository.model;

import com.softcraft.ohhsansibackend.comprobantepago.domain.model.EstadoComprobantePagoEnum;

public enum OrdenPagoEstadoEnum {
    PAGADO(1),
    GENERADO(2),
    NOGENERADO(3),
    ENREVISION(4);

    private final int id;

    OrdenPagoEstadoEnum(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public static OrdenPagoEstadoEnum fromId(int id) {
        for (OrdenPagoEstadoEnum estado : values()) {
            if (estado.getId() == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + id);
    }
}
