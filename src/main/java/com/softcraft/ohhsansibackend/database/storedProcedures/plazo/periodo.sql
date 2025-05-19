CREATE OR REPLACE FUNCTION public.validar_fechas_periodo()
    RETURNS trigger AS $$
BEGIN
    -- Verificar que la fecha de inicio no sea anterior a la fecha actual
    IF NEW.fecha_inicio < CURRENT_TIMESTAMP AND NEW.id_estado IS NOT NULL THEN
        RAISE EXCEPTION 'La fecha de inicio no puede ser anterior a la fecha y hora actual';
    END IF;

    -- Verificar que la fecha de fin no sea anterior a la fecha de inicio
    IF NEW.fecha_fin < NEW.fecha_inicio THEN
        RAISE EXCEPTION 'La fecha de fin no puede ser anterior a la fecha de inicio';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION public.validar_periodos_no_solapados()
    RETURNS trigger AS $$
DECLARE
    solapado boolean;
BEGIN
    -- Verificar si hay períodos solapados del mismo tipo para la misma olimpiada
    SELECT EXISTS (
        SELECT 1 FROM public.periodos_olimpiada
        WHERE id_olimpiada = NEW.id_olimpiada
          AND tipo_periodo = NEW.tipo_periodo
          AND id_periodo != COALESCE(NEW.id_periodo, -1)
          AND (
            (fecha_inicio, fecha_fin) OVERLAPS (NEW.fecha_inicio, NEW.fecha_fin)
            )
    ) INTO solapado;

    IF solapado THEN
        RAISE EXCEPTION 'No puede haber períodos del mismo tipo con fechas solapadas para la misma olimpiada';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-----------------------------------------------------------------------------------------------------------------------------------
ALTER TABLE public.olimpiada
    DROP CONSTRAINT IF EXISTS uq_olimpiada_anio;

ALTER TABLE public.olimpiada
    ADD CONSTRAINT uq_olimpiada_anio UNIQUE (nombre_olimpiada, anio);

-- ----------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION public.validar_orden_periodos()
    RETURNS trigger AS $$
DECLARE
    periodo_anterior RECORD;
    estado_anterior RECORD;
BEGIN
    -- Solo validar para períodos activos (con estado)
    IF NEW.id_estado IS NOT NULL THEN
        -- Obtener el período anterior en orden
        SELECT * FROM public.periodos_olimpiada
        WHERE id_olimpiada = NEW.id_olimpiada
          AND orden < NEW.orden
        ORDER BY orden DESC
        LIMIT 1
        INTO periodo_anterior;

        -- Si existe un período anterior, verificar que esté completado
        IF periodo_anterior IS NOT NULL THEN
            SELECT * FROM public.estado_olimpiada
            WHERE id_estado = periodo_anterior.id_estado
            INTO estado_anterior;

            IF NOT estado_anterior.permite_modificaciones THEN
                RAISE EXCEPTION 'No puede iniciar este período sin completar el período anterior (%)', periodo_anterior.tipo_periodo;
            END IF;
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Aplicar el trigger a la tabla periodos_olimpiada
CREATE TRIGGER trg_validar_orden_periodos
    BEFORE INSERT OR UPDATE ON public.periodos_olimpiada
    FOR EACH ROW EXECUTE FUNCTION public.validar_orden_periodos();

-- ----------------------------------------------------------------------------------------------------------------------------
-- Asegurar que los períodos estandar tengan un orden lógico
INSERT INTO public.periodos_estandar (orden, nombre_evento, descripcion) VALUES
                                                                             (1, 'CONFIGURACION', 'Período de configuración inicial de la olimpiada'),
                                                                             (2, 'PRE_INSCRIPCION', 'Período de pre-inscripción de participantes'),
                                                                             (3, 'INSCRIPCION', 'Período formal de inscripción'),
                                                                             (4, 'EVALUACION', 'Período de evaluaciones iniciales'),
                                                                             (5, 'CLASIFICATORIA', 'Fase clasificatoria'),
                                                                             (6, 'FINAL', 'Fase final'),
                                                                             (7, 'RESULTADOS', 'Publicación de resultados'),
                                                                             (8, 'PREMIACION', 'Ceremonia de premiación')
ON CONFLICT (orden) DO UPDATE SET
                                  nombre_evento = EXCLUDED.nombre_evento,
                                  descripcion = EXCLUDED.descripcion;

-- ----------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION public.actualizar_estado_por_periodo()
    RETURNS trigger AS $$
DECLARE
    periodo_actual RECORD;
    estado_periodo RECORD;
BEGIN
    -- Solo actuar si el período tiene un estado asignado
    IF NEW.id_estado IS NOT NULL THEN
        -- Obtener el estado del período
        SELECT * FROM public.estado_olimpiada WHERE id_estado = NEW.id_estado INTO estado_periodo;

        -- Si el período actual no permite modificaciones, actualizar el estado de la olimpiada
        IF NOT estado_periodo.permite_modificaciones THEN
            UPDATE public.olimpiada
            SET id_estado = NEW.id_estado,
                fecha_actualizacion = CURRENT_TIMESTAMP
            WHERE id_olimpiada = NEW.id_olimpiada;
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE VIEW public.vw_validacion_periodos AS
SELECT
    o.id_olimpiada,
    o.nombre_olimpiada,
    o.anio,
    po.tipo_periodo,
    po.nombre_periodo,
    po.fecha_inicio,
    po.fecha_fin,
    eo.nombre_estado,
    eo.permite_modificaciones,
    po.orden,
    CASE
        WHEN po.fecha_inicio > CURRENT_TIMESTAMP THEN 'PENDIENTE'
        WHEN CURRENT_TIMESTAMP BETWEEN po.fecha_inicio AND po.fecha_fin THEN 'EN_CURSO'
        WHEN CURRENT_TIMESTAMP > po.fecha_fin THEN 'FINALIZADO'
        END AS estado_actual
FROM public.olimpiada o
         JOIN public.periodos_olimpiada po ON o.id_olimpiada = po.id_olimpiada
         JOIN public.estado_olimpiada eo ON po.id_estado = eo.id_estado
ORDER BY o.anio DESC, o.id_olimpiada, po.orden;

--------------------------------------------------------------------------------------------------------------------------------
-- Insertar estados de olimpiada
INSERT INTO public.estado_olimpiada (id_estado, nombre_estado, permite_modificaciones, descripcion, orden_estado)
VALUES
    (1, 'PLANIFICACION', true, 'Olimpiada en fase de planificación', 1),
    (2, 'INSCRIPCION', true, 'Olimpiada en fase de inscripción', 2),
    (3, 'EN_CURSO', false, 'Olimpiada en ejecución', 3),
    (4, 'FINALIZADA', false, 'Olimpiada completada', 4)
ON CONFLICT (id_estado) DO NOTHING;

-- Insertar una olimpiada para el año actual
INSERT INTO public.olimpiada (nombre_olimpiada, precio_olimpiada, id_estado, anio)
VALUES ('Olimpiada Científica Nacional', 50.00, 1, EXTRACT(YEAR FROM CURRENT_DATE))
RETURNING id_olimpiada;

-- Insertar una olimpiada para el próximo año (mismo nombre, diferente año)
INSERT INTO public.olimpiada (nombre_olimpiada, precio_olimpiada, id_estado, anio)
VALUES ('Olimpiada Científica Nacional', 50.00, 1, EXTRACT(YEAR FROM CURRENT_DATE) + 1)
RETURNING id_olimpiada;