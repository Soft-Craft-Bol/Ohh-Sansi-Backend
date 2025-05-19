CREATE OR REPLACE FUNCTION public.validar_cambio_estado()
    RETURNS TRIGGER AS $$
DECLARE
    v_estado_actual VARCHAR;
    v_estado_nuevo VARCHAR;
    v_orden_actual INTEGER;
    v_orden_nuevo INTEGER;
BEGIN
    -- Si el estado no está cambiando, no hay nada que validar
    IF OLD.id_estado = NEW.id_estado THEN
        RETURN NEW;
    END IF;

    -- Obtener información de estados
    SELECT nombre_estado, orden_estado INTO v_estado_actual, v_orden_actual
    FROM estado_olimpiada WHERE id_estado = OLD.id_estado;

    SELECT nombre_estado, orden_estado INTO v_estado_nuevo, v_orden_nuevo
    FROM estado_olimpiada WHERE id_estado = NEW.id_estado;

    -- Validar transición de estado (solo permitir +1 o -1 en el orden)
    IF v_orden_nuevo NOT IN (v_orden_actual - 1, v_orden_actual + 1) THEN
        RAISE EXCEPTION 'Transición no permitida de % a %', v_estado_actual, v_estado_nuevo;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Eliminar triggers existentes si es necesario
DROP TRIGGER IF EXISTS trg_validar_cambio_estado ON olimpiada;
DROP TRIGGER IF EXISTS trg_actualizar_estado_por_periodo ON periodos_olimpiada;

-- Crear trigger para validar cambios de estado
CREATE TRIGGER trg_validar_cambio_estado
    BEFORE UPDATE OF id_estado ON olimpiada
    FOR EACH ROW
EXECUTE FUNCTION validar_cambio_estado();

-- Crear trigger para actualización automática por períodos
CREATE TRIGGER trg_actualizar_estado_por_periodo
    AFTER INSERT OR UPDATE ON periodos_olimpiada
    FOR EACH ROW
EXECUTE FUNCTION actualizar_estado_por_periodo();


CREATE OR REPLACE FUNCTION obtener_detalle_inscripcion(
    p_codigo_unico_inscripcion VARCHAR
)
    RETURNS TABLE (
                      id_inscripcion            INTEGER,
                      fecha_inscripcion         DATE,
                      hora_inscripcion          TIME,
                      codigo_unico_inscripcion  VARCHAR,
                      inscripcion_masiva        BOOLEAN,
                      participante              JSON,
                      areas                     JSON,
                      num_areas                 INTEGER,
                      precio_unitario           NUMERIC,
                      precio_total              NUMERIC,
                      tutores                   JSON,
                      olimpiadas                JSON,
                      edad_participante         INTEGER,
                      orden_de_pago_generada    BOOLEAN
                  )
    LANGUAGE plpgsql
AS $$
DECLARE
    v_id_inscripcion INTEGER;
    v_fecha_inscripcion DATE;
    v_hora_inscripcion TIME;
    v_codigo_unico VARCHAR;
    v_inscripcion_masiva BOOLEAN;
    v_participante JSON;
    v_areas JSON;
    v_num_areas INTEGER;
    v_precio_unitario NUMERIC;
    v_precio_total NUMERIC;
    v_tutores JSON;
    v_olimpiadas JSON;
    v_edad_participante INTEGER;
    v_orden_generada BOOLEAN;
BEGIN
    -- Validar existencia de inscripción
    SELECT i.id_inscripcion, i.fecha_inscripcion, i.hora_inscripcion,
           i.codigo_unico_inscripcion, i.inscripcion_masiva
    INTO v_id_inscripcion, v_fecha_inscripcion, v_hora_inscripcion,
        v_codigo_unico, v_inscripcion_masiva
    FROM inscripcion i
    WHERE i.codigo_unico_inscripcion = p_codigo_unico_inscripcion;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'No existe inscripción con el código %', p_codigo_unico_inscripcion;
    END IF;

    -- Verificar si se requiere tutor
    IF EXISTS (
        SELECT 1
        FROM participante p
                 JOIN inscripcion i ON p.id_inscripcion = i.id_inscripcion
                 LEFT JOIN participante_tutor pt ON pt.id_inscripcion = p.id_inscripcion
        WHERE i.codigo_unico_inscripcion = p_codigo_unico_inscripcion
          AND DATE_PART('year', AGE(NOW(), p.fecha_nacimiento)) < 15
          AND pt.id_tutor IS NULL
    ) THEN
        RAISE EXCEPTION 'Se requiere al menos un tutor para participantes menores de 15 años';
    END IF;

    -- Participante y edad
    SELECT to_json(p.*),
           (DATE_PART('year', AGE(NOW(), p.fecha_nacimiento)))::INT
    INTO v_participante, v_edad_participante
    FROM participante p
    WHERE p.id_inscripcion = v_id_inscripcion;

    -- Áreas, precio unitario, precio total
    SELECT json_agg(a.nombre_area),
           COUNT(a.id_area),
           o.precio_olimpiada,
           COUNT(a.id_area) * o.precio_olimpiada
    INTO v_areas, v_num_areas, v_precio_unitario, v_precio_total
    FROM participante_catalogo pc
             JOIN area a ON pc.id_area = a.id_area
             JOIN catalogo_olimpiada co ON a.id_catalogo_olimpiada = co.id_catalogo_olimpiada
             JOIN olimpiada o ON co.id_olimpiada = o.id_olimpiada
    WHERE pc.id_inscripcion = v_id_inscripcion
    GROUP BY o.precio_olimpiada;

    -- Tutores
    SELECT json_agg(
                   json_build_object(
                           'email', t.email_tutor,
                           'nombres', t.nombres_tutor,
                           'apellidos', t.apellidos_tutor,
                           'telefono', t.telefono,
                           'ci', t.carnet_identidad_tutor,
                           'tipo', tt.nombre_tipo_tutor
                   )
           )
    INTO v_tutores
    FROM tutor t
             JOIN participante_tutor pt ON pt.id_tutor = t.id_tutor
             JOIN tipo_tutor tt ON t.id_tipo_tutor = tt.id_tipo_tutor
    WHERE pt.id_inscripcion = v_id_inscripcion;

    -- Olimpiadas (en estado PLANIFICACION)
    SELECT json_agg(
                   json_build_object(
                           'id_olimpiada', o.id_olimpiada,
                           'nombre', o.nombre_olimpiada,
                           'precio', o.precio_olimpiada,
                           'estado', e.nombre_estado
                   )
           )
    INTO v_olimpiadas
    FROM olimpiada o
             JOIN estado_olimpiada e ON o.id_estado = e.id_estado
    WHERE e.nombre_estado = 'PLANIFICACION';

    -- Orden de pago
    SELECT EXISTS (
        SELECT 1
        FROM orden_de_pago op
        WHERE op.id_inscripcion = v_id_inscripcion
    )
    INTO v_orden_generada;

    -- Retornar resultado
    RETURN QUERY SELECT
                     v_id_inscripcion,
                     v_fecha_inscripcion,
                     v_hora_inscripcion,
                     v_codigo_unico,
                     v_inscripcion_masiva,
                     v_participante,
                     v_areas,
                     v_num_areas,
                     v_precio_unitario,
                     v_precio_total,
                     v_tutores,
                     v_olimpiadas,
                     v_edad_participante,
                     v_orden_generada;
END;
$$;





SELECT * FROM obtener_detalle_inscripcion('O3UXY6');