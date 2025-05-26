CREATE OR REPLACE FUNCTION public.crear_periodo(
    p_id_olimpiada INTEGER,
    p_tipo_periodo VARCHAR,
    p_fecha_inicio TIMESTAMP,
    p_fecha_fin TIMESTAMP,
    p_nombre_personalizado VARCHAR
) RETURNS TABLE (
    id_periodo INTEGER,
    id_olimpiada INTEGER,
    tipo_periodo VARCHAR,
    nombre_periodo VARCHAR,
    fecha_inicio TIMESTAMP,
    fecha_fin TIMESTAMP,
    id_estado INTEGER
) AS $$
DECLARE
    v_periodo periodos_olimpiada;
    v_estado_planificacion_id INTEGER;
    v_anio_olimpiada INTEGER;
    v_fecha_actual TIMESTAMP := NOW();
    v_solapamiento BOOLEAN;
BEGIN
    -- Validación básica: fecha fin no puede ser anterior a fecha inicio
    IF p_fecha_fin < p_fecha_inicio THEN
        RETURN json_build_object(
                'status', 'error',
                'message', 'La fecha de fin no puede ser anterior a la fecha de inicio'
               );
    END IF;

    -- Obtener el año de la olimpiada
    SELECT anio INTO v_anio_olimpiada
    FROM olimpiada
    WHERE id_olimpiada = p_id_olimpiada;

    IF NOT FOUND THEN
        RETURN json_build_object(
                'status', 'error',
                'message', 'La olimpiada especificada no existe'
               );
    END IF;

    -- Validar que las fechas no sean anteriores a la fecha actual
    IF p_fecha_inicio < v_fecha_actual OR p_fecha_fin < v_fecha_actual THEN
        RETURN json_build_object(
                'status', 'error',
                'message', 'No se pueden crear períodos con fechas anteriores a la fecha actual'
               );
    END IF;

    -- Validar que el año de las fechas coincida con el año de la olimpiada
    IF EXTRACT(YEAR FROM p_fecha_inicio) != v_anio_olimpiada OR
       EXTRACT(YEAR FROM p_fecha_fin) != v_anio_olimpiada THEN
        RETURN json_build_object(
                'status', 'error',
                'message', 'Las fechas del período deben coincidir con el año de la olimpiada (' || v_anio_olimpiada || ')'
               );
    END IF;

    -- Verificar si hay períodos del MISMO TIPO que se solapen
    SELECT EXISTS(
        SELECT 1 FROM periodos_olimpiada
        WHERE id_olimpiada = p_id_olimpiada
          AND tipo_periodo = p_tipo_periodo
          AND (fecha_inicio, fecha_fin) OVERLAPS (p_fecha_inicio, p_fecha_fin)
    ) INTO v_solapamiento;

    IF v_solapamiento THEN
        RETURN json_build_object(
                'status', 'error',
                'message', 'Ya existe un período del tipo "' || p_tipo_periodo || '" que se solapa con las fechas proporcionadas'
               );
    END IF;

    -- Obtener el ID del estado PLANIFICACION
    SELECT id_estado INTO v_estado_planificacion_id
    FROM estado_olimpiada
    WHERE nombre_estado = 'PLANIFICACION';
    -- Insertar el nuevo período
    INSERT INTO periodos_olimpiada (
        id_olimpiada,
        tipo_periodo,
        nombre_periodo,
        fecha_inicio,
        fecha_fin,
        id_estado
    ) VALUES (
                 p_id_olimpiada,
                 p_tipo_periodo,
                 COALESCE(p_nombre_personalizado,
                          CASE p_tipo_periodo
                              WHEN 'INSCRIPCION' THEN 'Inscripciones'
                              WHEN 'AMPLIACION' THEN 'Ampliacion'
                              END),
                 p_fecha_inicio,
                 p_fecha_fin,
                 v_estado_planificacion_id
             ) RETURNING * INTO v_periodo;

    RETURN json_build_object(
            'status', 'success',
            'message', 'Período creado exitosamente',
            'data', row_to_json(v_periodo)
           );
EXCEPTION WHEN OTHERS THEN
    RETURN json_build_object(
            'status', 'error',
            'message', 'Error al crear el período: ' || SQLERRM
           );
END;
$$ LANGUAGE plpgsql;
SELECT * FROM crear_periodo(
        69,
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
                      fecha_inicio TIMESTAMP,
                      fecha_fin TIMESTAMP,
                      estado_periodo VARCHAR(50),
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
            e.nombre_estado::VARCHAR(50) AS estado_periodo,
            CASE
                WHEN NOW() < p.fecha_inicio THEN 'PENDIENTE'::VARCHAR(20)
                WHEN NOW() BETWEEN p.fecha_inicio AND p.fecha_fin THEN 'ACTIVO'::VARCHAR(20)
                ELSE 'FINALIZADO'::VARCHAR(20)
                END AS estado_actual
        FROM
            public.periodos_olimpiada p
                JOIN
            public.olimpiada o ON p.id_olimpiada = o.id_olimpiada
                LEFT JOIN
            public.estado_olimpiada e ON p.id_estado = e.id_estado;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM select_all_periodos_olimpiadas();
----------------------------------------------------------------------------------------------------------------------------
