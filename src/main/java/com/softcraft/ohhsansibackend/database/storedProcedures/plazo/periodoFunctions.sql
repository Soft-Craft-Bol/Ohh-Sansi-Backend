CREATE OR REPLACE FUNCTION public.crear_periodo(
    p_id_olimpiada INTEGER,
    p_tipo_periodo VARCHAR,
    p_fecha_inicio DATE,
    p_fecha_fin DATE,
    p_nombre_personalizado VARCHAR
) RETURNS periodos_olimpiada AS $$
DECLARE
    v_periodo periodos_olimpiada%ROWTYPE;
    v_estado_planificacion_id INTEGER;
    v_anio_olimpiada INTEGER;
    v_fecha_actual DATE := CURRENT_DATE;
    v_solapamiento BOOLEAN;
    v_error_message TEXT;
BEGIN
    -- Validación básica: fecha fin no puede ser anterior a fecha inicio
    IF p_fecha_fin < p_fecha_inicio THEN
        RAISE EXCEPTION 'La fecha de fin no puede ser anterior a la fecha de inicio';
    END IF;

    -- Obtener el año de la olimpiada
    SELECT anio INTO v_anio_olimpiada
    FROM olimpiada
    WHERE id_olimpiada = p_id_olimpiada;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'La olimpiada especificada no existe';
    END IF;

    -- Validar que las fechas no sean anteriores a la fecha actual
    IF p_fecha_inicio < v_fecha_actual OR p_fecha_fin < v_fecha_actual THEN
        RAISE EXCEPTION 'No se pueden crear períodos con fechas anteriores a la fecha actual';
    END IF;

    -- Validar que el año de las fechas coincida con el año de la olimpiada
    IF EXTRACT(YEAR FROM p_fecha_inicio) != v_anio_olimpiada OR
       EXTRACT(YEAR FROM p_fecha_fin) != v_anio_olimpiada THEN
        RAISE EXCEPTION 'Las fechas del período deben coincidir con el año de la olimpiada (%)', v_anio_olimpiada;
    END IF;

    -- Verificar si hay períodos del MISMO TIPO que se solapen
    SELECT EXISTS(
        SELECT 1 FROM periodos_olimpiada
        WHERE id_olimpiada = p_id_olimpiada
          AND tipo_periodo = p_tipo_periodo
          AND (fecha_inicio, fecha_fin) OVERLAPS (p_fecha_inicio, p_fecha_fin)
    ) INTO v_solapamiento;

    IF v_solapamiento THEN
        RAISE EXCEPTION 'Ya existe un período del tipo "%" que se solapa con las fechas proporcionadas', p_tipo_periodo;
    END IF;

    -- Obtener el ID del estado PLANIFICACION
    SELECT id_estado INTO v_estado_planificacion_id
    FROM estado_olimpiada
    WHERE nombre_estado = 'PLANIFICACION';

    IF NOT FOUND THEN
        RAISE EXCEPTION 'No se encontró el estado PLANIFICACION en la base de datos';
    END IF;

    -- Insertar el nuevo período
    INSERT INTO periodos_olimpiada (
        id_olimpiada,
        tipo_periodo,
        nombre_periodo,
        fecha_inicio,
        fecha_fin
    ) VALUES (
                 p_id_olimpiada,
                 p_tipo_periodo,
                 COALESCE(p_nombre_personalizado,
                          CASE p_tipo_periodo
                              WHEN 'INSCRIPCION' THEN 'Inscripciones ' || v_anio_olimpiada
                              WHEN 'AMPLIACION' THEN 'Ampliación ' || v_anio_olimpiada
                              ELSE 'Período ' || v_anio_olimpiada
                              END),
                 p_fecha_inicio,
                 p_fecha_fin
             ) RETURNING * INTO v_periodo;

    RETURN v_periodo;
EXCEPTION
    WHEN OTHERS THEN
        -- Capturar el mensaje de error y relanzar con contexto adicional
        v_error_message := SQLERRM;
        RAISE EXCEPTION 'Error al crear el período: %', v_error_message;
END;
$$ LANGUAGE plpgsql;
SELECT * FROM crear_periodo(
        102,
        'PRE_INSCRIPCION',
        '2024-10-01 00:00:00',
        '2025-10-30 23:59:59',
        'Configuración Inicial'
              );
-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION public.select_all_periodos_olimpiadas()
    RETURNS TABLE (
                      id_periodo INTEGER,
                      id_olimpiada INTEGER,
                      nombre_olimpiada VARCHAR(255),
                      tipo_periodo VARCHAR(50),
                      nombre_periodo VARCHAR(255),
                      fecha_inicio DATE,
                      fecha_fin DATE,
                      estado_actual VARCHAR(20)
                  ) AS $$
BEGIN
    RETURN QUERY
        SELECT
            p.id_periodo,
            p.id_olimpiada,
            o.nombre_olimpiada::VARCHAR(255),
            p.tipo_periodo::VARCHAR(50),
            p.nombre_periodo::VARCHAR(255),
            p.fecha_inicio,
            p.fecha_fin,
            CASE
                WHEN NOW() < p.fecha_inicio THEN 'PENDIENTE'::VARCHAR(20)
                WHEN NOW() BETWEEN p.fecha_inicio AND p.fecha_fin THEN 'ACTIVO'::VARCHAR(20)
                ELSE 'FINALIZADO'::VARCHAR(20)
                END AS estado_actual
        FROM
            public.periodos_olimpiada p
                JOIN
            public.olimpiada o ON p.id_olimpiada = o.id_olimpiada;

END;
$$ LANGUAGE plpgsql;

SELECT * FROM select_all_periodos_olimpiadas();
----------------------------------------------------------------------------------------------------------------------------CREATE OR REPLACE FUNCTION public.update_periodo_olimpiada(
CREATE OR REPLACE FUNCTION public.update_periodo_olimpiada(
    p_id_periodo INTEGER,
    p_id_olimpiada INTEGER,
    p_fecha_inicio DATE DEFAULT NULL,
    p_fecha_fin DATE DEFAULT NULL,
    p_nombre_personalizado VARCHAR DEFAULT NULL,
    p_id_estado INTEGER DEFAULT NULL
) RETURNS periodos_olimpiada AS $$
DECLARE
    v_periodo periodos_olimpiada%ROWTYPE;
    v_solapamiento BOOLEAN;
    v_nueva_fecha_inicio DATE;
    v_nueva_fecha_fin DATE;
    v_tipo_periodo VARCHAR;
BEGIN
    SELECT * INTO v_periodo FROM periodos_olimpiada
    WHERE id_periodo = p_id_periodo AND id_olimpiada = p_id_olimpiada;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'El período especificado no existe (ID: %, Olimpiada: %)', p_id_periodo, p_id_olimpiada;
    END IF;

    v_nueva_fecha_inicio := COALESCE(p_fecha_inicio, v_periodo.fecha_inicio);
    v_nueva_fecha_fin := COALESCE(p_fecha_fin, v_periodo.fecha_fin);

    IF v_nueva_fecha_fin IS NOT NULL AND v_nueva_fecha_inicio IS NOT NULL AND v_nueva_fecha_fin < v_nueva_fecha_inicio THEN
        RAISE EXCEPTION 'La fecha de fin no puede ser anterior a la fecha de inicio';
    END IF;

    IF p_fecha_inicio IS NOT NULL AND p_fecha_inicio != v_periodo.fecha_inicio AND p_fecha_inicio < CURRENT_DATE THEN
        RAISE EXCEPTION 'La nueva fecha de inicio debe ser mayor a la fecha actual';
    END IF;

    IF v_periodo.id_estado IN (4) THEN
        RAISE EXCEPTION 'No se pueden modificar períodos COMPLETADOS';
    END IF;

    SELECT tipo_periodo INTO v_tipo_periodo FROM periodos_olimpiada
    WHERE id_periodo = p_id_periodo AND id_olimpiada = p_id_olimpiada;

    IF v_tipo_periodo IN ('INSCRIPCION', 'AMPLIACION') THEN
        SELECT EXISTS(
            SELECT 1 FROM periodos_olimpiada
            WHERE id_periodo != p_id_periodo
              AND tipo_periodo IN ('INSCRIPCION', 'AMPLIACION')
              AND (
                (fecha_inicio <= v_nueva_fecha_fin AND fecha_fin >= v_nueva_fecha_inicio)
                )
        ) INTO v_solapamiento;

        IF v_solapamiento THEN
            RAISE EXCEPTION 'Las fechas se solapan con otro período de inscripción existente';
        END IF;
    END IF;

    RAISE NOTICE 'Procediendo a actualizar con: nombre=%, inicio=%, fin=%, estado=%',
        COALESCE(p_nombre_personalizado, v_periodo.nombre_periodo),
        v_nueva_fecha_inicio,
        v_nueva_fecha_fin,
        COALESCE(p_id_estado, v_periodo.id_estado);

    UPDATE periodos_olimpiada
    SET
        nombre_periodo = COALESCE(p_nombre_personalizado, nombre_periodo),
        fecha_inicio = v_nueva_fecha_inicio,
        fecha_fin = v_nueva_fecha_fin,
        id_estado = COALESCE(p_id_estado, id_estado)
    WHERE id_periodo = p_id_periodo AND id_olimpiada = p_id_olimpiada;

    -- Verificar que se actualizó
    IF NOT FOUND THEN
        RAISE EXCEPTION 'No se pudo actualizar el período. Verificar condiciones WHERE';
    END IF;

    -- Obtener el registro actualizado
    SELECT * INTO v_periodo FROM periodos_olimpiada
    WHERE id_periodo = p_id_periodo AND id_olimpiada = p_id_olimpiada;

    BEGIN
        PERFORM actualizar_estado_olimpiada_por_periodos(p_id_olimpiada);
    EXCEPTION WHEN OTHERS THEN
        RAISE NOTICE 'No se pudo actualizar el estado de la olimpiada: %', SQLERRM;
    END;

    RETURN v_periodo;
END;
$$ LANGUAGE plpgsql;
SELECT * FROM update_periodo_olimpiada(200, 102, NULL, '2025-08-29', NULL, NULL);
SELECT 'Período existe:', * FROM periodos_olimpiada WHERE id_periodo = 200 AND id_olimpiada = 102;
SELECT 'Resultado función:', * FROM update_periodo_olimpiada(200, 102, NULL, '2025-08-30', NULL, NULL);
SELECT * FROM periodos_olimpiada WHERE id_periodo = 200 AND id_olimpiada = 102;



CREATE OR REPLACE FUNCTION public.actualizar_estado_periodo_automatico()
    RETURNS TRIGGER AS $$
DECLARE
    v_nuevo_estado INTEGER;
    v_fecha_actual DATE := CURRENT_DATE;
BEGIN
    -- Determinar el estado basado en fechas
    IF v_fecha_actual < NEW.fecha_inicio THEN
        v_nuevo_estado := 1; -- PENDIENTE
    ELSIF v_fecha_actual BETWEEN NEW.fecha_inicio AND NEW.fecha_fin THEN
        v_nuevo_estado := 2; -- ACTIVO
    ELSE
        v_nuevo_estado := 4; -- COMPLETADO
    END IF;

    -- Solo actualizar si el estado actual no es CERRADO (3) o CANCELADO (5)
    -- y si el nuevo estado es diferente al actual
    IF NEW.id_estado NOT IN (3, 5) AND NEW.id_estado != v_nuevo_estado THEN
        NEW.id_estado := v_nuevo_estado;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Crear trigger para actualización automática
CREATE OR REPLACE TRIGGER trg_actualizar_estado_periodo
    BEFORE INSERT OR UPDATE OF fecha_inicio, fecha_fin ON periodos_olimpiada
    FOR EACH ROW EXECUTE FUNCTION actualizar_estado_periodo_automatico();


CREATE OR REPLACE FUNCTION public.actualizar_estado_olimpiada_por_periodos(p_id_olimpiada INTEGER)
    RETURNS VOID AS $$
DECLARE
    v_nuevo_estado INTEGER;
BEGIN
    -- Determinar el nuevo estado basado en los períodos activos
    SELECT
        CASE
            WHEN EXISTS (
                SELECT 1 FROM periodos_olimpiada
                WHERE id_olimpiada = p_id_olimpiada
                  AND id_estado = 2 -- ACTIVO
                  AND tipo_periodo IN ('INSCRIPCION', 'AMPLIACION')
            ) THEN 2 -- EN INSCRIPCION
            WHEN EXISTS (
                SELECT 1 FROM periodos_olimpiada
                WHERE id_olimpiada = p_id_olimpiada
                  AND id_estado = 3 -- CERRADO
                  AND tipo_periodo IN ('INSCRIPCION', 'AMPLIACION')
            ) THEN 4 -- INSCRIPCION CERRADA
            WHEN EXISTS (
                SELECT 1 FROM periodos_olimpiada
                WHERE id_olimpiada = p_id_olimpiada
                  AND id_estado = 5 -- CANCELADO
                  AND tipo_periodo IN ('INSCRIPCION', 'AMPLIACION')
            ) THEN 5 -- INSCRIPCION CANCELADA
            ELSE 1 -- PLANIFICACION
            END INTO v_nuevo_estado;

    -- Actualizar solo si cambió el estado
    UPDATE olimpiada
    SET id_estado = v_nuevo_estado
    WHERE id_olimpiada = p_id_olimpiada
      AND id_estado != v_nuevo_estado;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION public.cerrar_periodo_manual(
    p_id_periodo INTEGER,
    p_id_olimpiada INTEGER,
    p_motivo VARCHAR(300) DEFAULT 'Cerrado manualmente'
) RETURNS periodos_olimpiada AS $$
DECLARE
    v_periodo periodos_olimpiada%ROWTYPE;
BEGIN
    -- Verificar que el período existe y es de tipo INSCRIPCION o AMPLIACION
    SELECT * INTO v_periodo FROM periodos_olimpiada
    WHERE id_periodo = p_id_periodo AND id_olimpiada = p_id_olimpiada
      AND tipo_periodo IN ('INSCRIPCION', 'AMPLIACION');

    IF NOT FOUND THEN
        RAISE EXCEPTION 'Período no encontrado o no es de inscripción/ampliación';
    END IF;

    -- Solo se puede cerrar si está ACTIVO o PENDIENTE
    IF v_periodo.id_estado NOT IN (1, 2) THEN
        RAISE EXCEPTION 'Solo se pueden cerrar períodos PENDIENTES o ACTIVOS';
    END IF;

    -- Actualizar a CERRADO
    UPDATE periodos_olimpiada
    SET id_estado = 3
    WHERE id_periodo = p_id_periodo AND id_olimpiada = p_id_olimpiada
    RETURNING * INTO v_periodo;

    -- Actualizar estado de la olimpiada
    PERFORM actualizar_estado_olimpiada_por_periodos(p_id_olimpiada);

    RETURN v_periodo;
END;
$$ LANGUAGE plpgsql;