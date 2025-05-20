CREATE OR REPLACE FUNCTION public.crear_olimpiada(
    p_anio INTEGER,
    p_nombre VARCHAR(100),
    p_precio NUMERIC(10,2),
    p_fecha_inicio DATE,
    p_fecha_fin DATE
) RETURNS public.olimpiada AS $$
DECLARE
    v_olimpiada public.olimpiada;
BEGIN
    -- Validar año futuro
    IF p_anio < EXTRACT(YEAR FROM CURRENT_DATE) THEN
        RAISE EXCEPTION 'Solo se pueden crear olimpiadas para el año actual o futuros';
    END IF;

    INSERT INTO public.olimpiada (
        nombre_olimpiada,
        precio_olimpiada,
        anio,
        id_estado,
        fecha_inicio,
        fecha_fin
    ) VALUES (
                 p_nombre,
                 p_precio,
                 p_anio,
                 (SELECT id_estado FROM public.estado_olimpiada WHERE nombre_estado = 'PLANIFICACION'),
                p_fecha_inicio,
                p_fecha_fin
             ) RETURNING * INTO v_olimpiada;

    RETURN v_olimpiada;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM public.crear_olimpiada(2024, 'Olimpiada Nacional de Ciencias', 50.00, '2024-01-01', '2024-12-31');
---------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectOlimpiada()
    RETURNS TABLE (
                      id_olimpiada INTEGER,
                      anio INTEGER,
                      nombre_olimpiada VARCHAR,
                      nombre_estado VARCHAR,
                      precio_olimpiada DECIMAL
                  ) AS $$
BEGIN
    RETURN QUERY
        SELECT
            o.id_olimpiada,
            o.anio,
            o.nombre_olimpiada,
            e.nombre_estado,
            o.precio_olimpiada
        FROM
            olimpiada o
                JOIN
            estado_olimpiada e ON o.id_estado = e.id_estado;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectOlimpiada();
---------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION public.cambiar_estado_olimpiada(
    p_id_olimpiada INTEGER,
    p_nuevo_estado VARCHAR(50))
    RETURNS BOOLEAN AS $$
BEGIN
    UPDATE public.olimpiada
    SET id_estado = (SELECT id_estado FROM public.estado_olimpiada WHERE nombre_estado = p_nuevo_estado)
    WHERE id_olimpiada = p_id_olimpiada;

    RETURN FOUND;
END;
$$ LANGUAGE plpgsql;
------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION public.acciones_permitidas_olimpiada(
    p_id_olimpiada INTEGER
) RETURNS JSON AS $$
DECLARE
    v_result JSON;
    v_estado VARCHAR;
BEGIN
    SELECT e.nombre_estado INTO v_estado
    FROM public.olimpiada o
             JOIN public.estado_olimpiada e ON o.id_estado = e.id_estado
    WHERE o.id_olimpiada = p_id_olimpiada;

    SELECT json_build_object(
                   'editar', v_estado IN ('PLANIFICACION', 'PRE_INSCRIPCION'),
                   'cambiar_precio', v_estado IN ('PLANIFICACION', 'PRE_INSCRIPCION'),
                   'eliminar', v_estado = 'PLANIFICACION',
                   'avanzar_estado', v_estado IN ('PLANIFICACION', 'PRE_INSCRIPCION', 'INSCRIPCION', 'EN_CURSO'),
                   'retroceder_estado', v_estado IN ('PRE_INSCRIPCION', 'INSCRIPCION', 'EN_CURSO', 'FINALIZADA')
           ) INTO v_result;

    RETURN v_result;
END;
$$ LANGUAGE plpgsql;
-------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION public.actualizar_estados_olimpiadas()
    RETURNS VOID AS $$
BEGIN
    -- Actualizar olimpiadas en PRE_INSCRIPCION que deben pasar a INSCRIPCION
    UPDATE public.olimpiada o
    SET id_estado = (SELECT id_estado FROM estado_olimpiada WHERE nombre_estado = 'INSCRIPCION')
    WHERE o.id_estado = (SELECT id_estado FROM estado_olimpiada WHERE nombre_estado = 'PRE_INSCRIPCION')
      AND EXISTS (
        SELECT 1 FROM periodos_olimpiada p
        WHERE p.id_olimpiada = o.id_olimpiada
          AND p.nombre_evento = 'INSCRIPCION'
          AND CURRENT_DATE BETWEEN p.fecha_inicio AND p.fecha_fin
    );

    -- Actualizar olimpiadas en INSCRIPCION que deben pasar a EN_CURSO
    UPDATE public.olimpiada o
    SET id_estado = (SELECT id_estado FROM estado_olimpiada WHERE nombre_estado = 'EN_CURSO')
    WHERE o.id_estado = (SELECT id_estado FROM estado_olimpiada WHERE nombre_estado = 'INSCRIPCION')
      AND EXISTS (
        SELECT 1 FROM periodos_olimpiada p
        WHERE p.id_olimpiada = o.id_olimpiada
          AND p.nombre_evento = 'EVALUACION'
          AND CURRENT_DATE >= p.fecha_inicio
    );

    -- Actualizar olimpiadas en EN_CURSO que deben finalizar
    UPDATE public.olimpiada o
    SET id_estado = (SELECT id_estado FROM estado_olimpiada WHERE nombre_estado = 'FINALIZADA')
    WHERE o.id_estado = (SELECT id_estado FROM estado_olimpiada WHERE nombre_estado = 'EN_CURSO')
      AND NOT EXISTS (
        SELECT 1 FROM periodos_olimpiada p
        WHERE p.id_olimpiada = o.id_olimpiada
          AND (p.fecha_fin IS NULL OR p.fecha_fin > CURRENT_DATE)
    );
END;
$$ LANGUAGE plpgsql;
-------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION public.actualizar_estado_automatico()
    RETURNS TRIGGER AS $$
DECLARE
    v_estado_actual VARCHAR;
    v_hay_periodos_activos BOOLEAN;
    v_todos_periodos_completados BOOLEAN;
BEGIN
    -- Obtener estado actual
    SELECT e.nombre_estado INTO v_estado_actual
    FROM estado_olimpiada e
    WHERE e.id_estado = NEW.id_estado;

    -- Lógica para estado PRE_INSCRIPCION
    IF v_estado_actual = 'PLANIFICACION' THEN
        -- Verificar si existe al menos un período de PRE_INSCRIPCION activo
        SELECT EXISTS (
            SELECT 1 FROM periodos_olimpiada
            WHERE id_olimpiada = NEW.id_olimpiada
              AND nombre_evento = 'PRE_INSCRIPCION'
              AND CURRENT_DATE BETWEEN fecha_inicio AND fecha_fin
        ) INTO v_hay_periodos_activos;

        IF v_hay_periodos_activos THEN
            NEW.id_estado = (SELECT id_estado FROM estado_olimpiada WHERE nombre_estado = 'PRE_INSCRIPCION');
        END IF;

        -- Lógica para estado INSCRIPCION
    ELSIF v_estado_actual = 'PRE_INSCRIPCION' THEN
        -- Verificar si existe período de INSCRIPCION activo
        SELECT EXISTS (
            SELECT 1 FROM periodos_olimpiada
            WHERE id_olimpiada = NEW.id_olimpiada
              AND nombre_evento = 'INSCRIPCION'
              AND CURRENT_DATE BETWEEN fecha_inicio AND fecha_fin
        ) INTO v_hay_periodos_activos;

        IF v_hay_periodos_activos THEN
            NEW.id_estado = (SELECT id_estado FROM estado_olimpiada WHERE nombre_estado = 'INSCRIPCION');
        END IF;

        -- Lógica para estado FINALIZADA
    ELSIF v_estado_actual = 'EN_CURSO' THEN
        -- Verificar si todos los períodos han finalizado
        SELECT NOT EXISTS (
            SELECT 1 FROM periodos_olimpiada
            WHERE id_olimpiada = NEW.id_olimpiada
              AND fecha_fin > CURRENT_DATE
        ) INTO v_todos_periodos_completados;

        IF v_todos_periodos_completados THEN
            NEW.id_estado = (SELECT id_estado FROM estado_olimpiada WHERE nombre_estado = 'FINALIZADA');
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
---------------------------------------------------------------------------------------------
-- Necesitas extensión pg_cron instalada
CREATE EXTENSION IF NOT EXISTS pg_cron;

-- Programar ejecución diaria a medianoche
SELECT cron.schedule('actualizar_estados_olimpiadas', '0 0 * * *',
                     $$SELECT public.actualizar_estados_olimpiadas()$$);
-------------------------------------------------------------------------------------------------
CREATE TRIGGER trg_actualizar_estado_automatico
    BEFORE UPDATE ON public.olimpiada
    FOR EACH ROW
EXECUTE FUNCTION public.actualizar_estado_automatico();