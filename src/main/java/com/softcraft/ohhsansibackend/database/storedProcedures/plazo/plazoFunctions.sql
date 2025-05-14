CREATE OR REPLACE FUNCTION public.crear_periodo(
    p_id_olimpiada INTEGER,
    p_tipo_periodo VARCHAR,
    p_fecha_inicio TIMESTAMP,
    p_fecha_fin TIMESTAMP,
    p_nombre_personalizado VARCHAR DEFAULT NULL
) RETURNS JSON AS $$
DECLARE
    v_periodo periodos_olimpiada;
    v_orden INTEGER;
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

    -- Calcular el orden del nuevo período
    SELECT COALESCE(MAX(orden), 0) + 1 INTO v_orden
    FROM periodos_olimpiada
    WHERE id_olimpiada = p_id_olimpiada;

    -- Insertar el nuevo período
    INSERT INTO periodos_olimpiada (
        id_olimpiada,
        tipo_periodo,
        nombre_periodo,
        fecha_inicio,
        fecha_fin,
        orden,
        id_estado
    ) VALUES (
                 p_id_olimpiada,
                 p_tipo_periodo,
                 COALESCE(p_nombre_personalizado,
                          CASE p_tipo_periodo
                              WHEN 'CONFIGURACION' THEN 'Configuración Inicial'
                              WHEN 'PRE_INSCRIPCION' THEN 'Pre-Inscripciones'
                              WHEN 'INSCRIPCION' THEN 'Inscripciones Formales'
                              WHEN 'EVALUACION' THEN 'Fase de Evaluación'
                              WHEN 'FINAL' THEN 'Fase Final'
                              WHEN 'PREMIACION' THEN 'Ceremonia de Premiación'
                              END),
                 p_fecha_inicio,
                 p_fecha_fin,
                 v_orden,
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
                      estado_actual VARCHAR(20),
                      obligatorio BOOLEAN,
                      orden INTEGER
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
                END AS estado_actual,
            p.obligatorio,
            p.orden
        FROM
            public.periodos_olimpiada p
                JOIN
            public.olimpiada o ON p.id_olimpiada = o.id_olimpiada
                LEFT JOIN
            public.estado_olimpiada e ON p.id_estado = e.id_estado
        ORDER BY
            p.id_olimpiada, p.orden;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM select_all_periodos_olimpiadas();
----------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION validar_periodos_no_solapados()
    RETURNS TRIGGER AS $$
DECLARE
    v_periodo_anterior periodos_olimpiada;
    v_periodo_siguiente periodos_olimpiada;
    v_solapamiento BOOLEAN;
BEGIN
    -- Validación básica: fecha fin no puede ser anterior a fecha inicio
    IF NEW.fecha_fin < NEW.fecha_inicio THEN
        RAISE EXCEPTION 'La fecha de fin no puede ser anterior a la fecha de inicio';
    END IF;

    -- Verificar si hay períodos del MISMO TIPO que se solapen
    SELECT EXISTS(
        SELECT 1 FROM periodos_olimpiada
        WHERE id_olimpiada = NEW.id_olimpiada
          AND tipo_periodo = NEW.tipo_periodo
          AND id_periodo != COALESCE(NEW.id_periodo, -1)
          AND (
            (fecha_inicio, fecha_fin) OVERLAPS (NEW.fecha_inicio, NEW.fecha_fin)
            )
    ) INTO v_solapamiento;

    IF v_solapamiento THEN
        RAISE EXCEPTION 'Ya existe un período del tipo "%" que se solapa con las fechas proporcionadas', NEW.tipo_periodo;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_validar_periodos
    BEFORE INSERT OR UPDATE ON periodos_olimpiada
    FOR EACH ROW
EXECUTE FUNCTION validar_periodos_no_solapados();
-- ---------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE VIEW vw_periodos_olimpiada AS
SELECT
    p.id_periodo,
    p.id_olimpiada,
    o.nombre_olimpiada,
    p.tipo_periodo,
    p.nombre_periodo,
    p.fecha_inicio,
    p.fecha_fin,
    e.nombre_estado AS estado_periodo,
    CASE
        WHEN NOW() < p.fecha_inicio THEN 'PENDIENTE'
        WHEN NOW() BETWEEN p.fecha_inicio AND p.fecha_fin THEN 'ACTIVO'
        ELSE 'FINALIZADO'
        END AS estado_actual,
    p.obligatorio,
    p.orden
FROM
    public.periodos_olimpiada p
        JOIN
    public.olimpiada o ON p.id_olimpiada = o.id_olimpiada
        LEFT JOIN
    public.estado_olimpiada e ON p.id_estado = e.id_estado
ORDER BY
    p.id_olimpiada, p.orden;

SELECT * FROM vw_periodos_olimpiada WHERE id_olimpiada = 69;
-- ---------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION verificar_periodo_activo(
    p_id_olimpiada INTEGER,
    p_tipo_periodo VARCHAR
) RETURNS BOOLEAN AS $$
BEGIN
    RETURN EXISTS (
        SELECT 1 FROM public.periodos_olimpiada
        WHERE id_olimpiada = p_id_olimpiada
          AND tipo_periodo = p_tipo_periodo
          AND NOW() BETWEEN fecha_inicio AND fecha_fin
    );
END;
$$ LANGUAGE plpgsql;
-- ---------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION public.actualizar_estado_por_periodo()
    RETURNS TRIGGER AS $$
DECLARE
    v_nuevo_estado_id INTEGER;
    v_estado_actual_id INTEGER;
BEGIN
    -- Obtener el estado actual
    SELECT id_estado INTO v_estado_actual_id
    FROM olimpiada
    WHERE id_olimpiada = NEW.id_olimpiada;

    -- Determinar el nuevo estado basado en períodos activos
    SELECT id_estado INTO v_nuevo_estado_id
    FROM estado_olimpiada
    WHERE nombre_estado = (
        CASE
            WHEN EXISTS (
                SELECT 1 FROM periodos_olimpiada
                WHERE id_olimpiada = NEW.id_olimpiada
                  AND tipo_periodo = 'PRE_INSCRIPCION'
                  AND NOW() BETWEEN fecha_inicio AND fecha_fin
            ) THEN 'PRE_INSCRIPCION'

            WHEN EXISTS (
                SELECT 1 FROM periodos_olimpiada
                WHERE id_olimpiada = NEW.id_olimpiada
                  AND tipo_periodo = 'INSCRIPCION'
                  AND NOW() BETWEEN fecha_inicio AND fecha_fin
            ) THEN 'INSCRIPCION'

            WHEN EXISTS (
                SELECT 1 FROM periodos_olimpiada
                WHERE id_olimpiada = NEW.id_olimpiada
                  AND tipo_periodo IN ('EVALUACION', 'FINAL')
                  AND NOW() BETWEEN fecha_inicio AND fecha_fin
            ) THEN 'EN_CURSO'

            WHEN EXISTS (
                SELECT 1 FROM periodos_olimpiada
                WHERE id_olimpiada = NEW.id_olimpiada
                  AND tipo_periodo = 'PREMIACION'
                  AND NOW() > fecha_fin
            ) THEN 'FINALIZADA'

            ELSE (SELECT nombre_estado FROM estado_olimpiada WHERE id_estado = v_estado_actual_id)
            END
        );

    -- Solo actualizar si el estado realmente cambió
    IF v_nuevo_estado_id != v_estado_actual_id THEN
        UPDATE olimpiada
        SET id_estado = v_nuevo_estado_id
        WHERE id_olimpiada = NEW.id_olimpiada;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_actualizar_estado_por_periodo
    AFTER INSERT OR UPDATE ON periodos_olimpiada
    FOR EACH ROW
EXECUTE FUNCTION actualizar_estado_por_periodo();

DROP TRIGGER IF EXISTS trg_actualizar_estado_por_periodo ON periodos_olimpiada;
-----------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION validar_fechas_periodo()
    RETURNS TRIGGER AS $$
DECLARE
    v_anio_olimpiada INTEGER;
BEGIN
    -- Obtener el año de la olimpiada
    SELECT anio INTO v_anio_olimpiada
    FROM olimpiada
    WHERE id_olimpiada = NEW.id_olimpiada;

    -- Validar fechas no pasadas
    IF NEW.fecha_inicio < NOW() OR NEW.fecha_fin < NOW() THEN
        RAISE EXCEPTION 'No se permiten fechas pasadas para períodos olímpicos';
    END IF;

    -- Validar coincidencia de año
    IF EXTRACT(YEAR FROM NEW.fecha_inicio) != v_anio_olimpiada OR
       EXTRACT(YEAR FROM NEW.fecha_fin) != v_anio_olimpiada THEN
        RAISE EXCEPTION 'Las fechas del período deben ser del mismo año que la olimpiada (%)', v_anio_olimpiada;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tr_validar_fechas_periodo
    BEFORE INSERT OR UPDATE ON periodos_olimpiada
    FOR EACH ROW EXECUTE FUNCTION validar_fechas_periodo();