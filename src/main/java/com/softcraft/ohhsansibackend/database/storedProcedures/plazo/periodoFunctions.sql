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
----------------------------------------------------------------------------------------------------------------------------
