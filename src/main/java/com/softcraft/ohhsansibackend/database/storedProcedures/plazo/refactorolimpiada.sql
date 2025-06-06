CREATE TABLE IF NOT EXISTS public.estado_periodo (
                                                     id_estado SERIAL PRIMARY KEY,
                                                     nombre_estado VARCHAR(50) NOT NULL UNIQUE,
                                                     descripcion VARCHAR(300),
                                                     permite_modificacion BOOLEAN DEFAULT true
);

-- Insertar estados básicos
INSERT INTO public.estado_periodo (nombre_estado, permite_modificacion, descripcion) VALUES
                                                                                         ('PENDIENTE', true, 'El período está programado pero no ha comenzado'),
                                                                                         ('ACTIVO', false, 'El período está actualmente en curso'),
                                                                                         ('CANCELADO', false, 'El período fue cancelado manualmente'),
                                                                                         ('COMPLETADO', false, 'El período finalizó normalmente'),
                                                                                         ('AMPLIACION', false, 'Período de ampliación después de un cierre');

ALTER TABLE public.periodos_olimpiada
    ADD COLUMN id_estado INTEGER NOT NULL DEFAULT 1
        CONSTRAINT fk_periodo_estado REFERENCES public.estado_periodo(id_estado),
    ADD COLUMN fecha_cancelacion TIMESTAMP,
    ADD COLUMN motivo_cancelacion VARCHAR(300),
    ADD COLUMN es_ampliacion BOOLEAN DEFAULT false,
    ADD COLUMN id_periodo_original INTEGER;  -- Para referenciar el período original en caso de ampliación

-- Eliminar la restricción única que impedía múltiples períodos del mismo tipo
ALTER TABLE public.periodos_olimpiada DROP CONSTRAINT IF EXISTS uq_periodo_olimpiada;

CREATE OR REPLACE FUNCTION actualizar_estado_periodos()
    RETURNS TRIGGER AS $$
BEGIN
    -- Actualizar estados basados en fechas (excepto CANCELADOS)
    UPDATE public.periodos_olimpiada
    SET id_estado = CASE
                        WHEN CURRENT_DATE < fecha_inicio THEN 1 -- PENDIENTE
                        WHEN CURRENT_DATE BETWEEN fecha_inicio AND fecha_fin THEN 2 -- ACTIVO
                        WHEN CURRENT_DATE > fecha_fin AND id_estado != 3 THEN 4 -- COMPLETADO
                        ELSE id_estado
        END
    WHERE id_estado != 3; -- No cambiar estado de los cancelados

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Trigger que se ejecuta diariamente
CREATE TRIGGER trg_actualizar_estado_periodos
    AFTER INSERT OR UPDATE OR DELETE ON public.periodos_olimpiada
EXECUTE FUNCTION actualizar_estado_periodos();


CREATE TABLE IF NOT EXISTS public.historial_periodos (
                                                         id_historial SERIAL PRIMARY KEY,
                                                         id_periodo INTEGER NOT NULL,
                                                         id_olimpiada INTEGER NOT NULL,
                                                         accion VARCHAR(10) NOT NULL, -- 'CREATE', 'UPDATE', 'CANCEL'
                                                         fecha_cambio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                         usuario VARCHAR(100) NOT NULL,
                                                         cambios JSONB, -- Detalles de los cambios
                                                         motivo VARCHAR(300),
                                                         CONSTRAINT fk_historial_periodo FOREIGN KEY (id_periodo, id_olimpiada)
                                                             REFERENCES public.periodos_olimpiada(id_periodo, id_olimpiada)
);


select po.*
from olimpiada o, estado_olimpiada eo, periodos_olimpiada po
where po.id_estado = eo.id_estado
  and o.id_olimpiada = po.id_olimpiada
  and eo.nombre_estado = 'EN INSCRIPCION'
  and CURRENT_DATE between DATE(po.fecha_inicio) and DATE(po.fecha_fin)



select distinct cp.*, ecp.nombre_estado_comprobante
from comprobante_pago cp, estado_comprobante_pago ecp, orden_de_pago op, periodos_olimpiada po,
     olimpiada o, estado_olimpiada eo
where cp.id_estado_comprobante = ecp.id_estado_comprobante
and o.id_olimpiada = po.id_olimpiada
and eo.id_estado = po.id_estado
and eo.nombre_estado= 'EN INSCRIPCION'


select * from participante where id_inscripcion=767


DELETE FROM participante;-- Primero, eliminar la restricción foreign key existente
ALTER TABLE excel_association
    DROP CONSTRAINT fk_participante;

-- Luego, crear la nueva restricción con CASCADE
ALTER TABLE excel_association
    ADD CONSTRAINT fk_participante
        FOREIGN KEY (ci_participante)
            REFERENCES participante(carnet_identidad_participante)
            ON DELETE CASCADE;

-- Ahora puedes eliminar de participante y se eliminará en cascada
DELETE FROM participante;

-- Modificar la restricción en excel_association
ALTER TABLE excel_association
    DROP CONSTRAINT fk_participante;

ALTER TABLE excel_association
    ADD CONSTRAINT fk_participante
        FOREIGN KEY (carnet_identidad_participante)
            REFERENCES participante(carnet_identidad_participante)
            ON DELETE CASCADE;

-- Modificar la restricción en participante_catalogo
ALTER TABLE participante_catalogo
    DROP CONSTRAINT fk_particip_relations_particip;

ALTER TABLE participante_catalogo
    ADD CONSTRAINT fk_particip_relations_particip
        FOREIGN KEY (id_inscripcion, id_participante)
            REFERENCES participante(id_inscripcion, id_participante)
            ON DELETE CASCADE;

-- Ahora sí podrás eliminar de participante
DELETE FROM participante;

ALTER TABLE excel_association
    DROP CONSTRAINT fk_excel;

ALTER TABLE excel_association
    ADD CONSTRAINT fk_excel
        FOREIGN KEY (id_excel)
            REFERENCES participante(carnet_identidad_participante)
            ON DELETE CASCADE;
ALTER TABLE orden_de_pago
    DROP CONSTRAINT fk_orden_de_relations_inscripc;

ALTER TABLE orden_de_pago
    ADD CONSTRAINT fk_orden_de_relations_inscripc
        FOREIGN KEY (id_inscripcion)
            REFERENCES inscripcion(id_inscripcion)
            ON DELETE CASCADE;


alter table comprobante_pago
    drop constraint fk_comproba_r30_orden_de;

alter table comprobante_pago
    add constraint fk_comproba_r30_orden_de
        foreign key (id_inscripcion, id_orden_pago)
            references orden_de_pago(id_inscripcion, id_orden_pago)
            on delete cascade;
