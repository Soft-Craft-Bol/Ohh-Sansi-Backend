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