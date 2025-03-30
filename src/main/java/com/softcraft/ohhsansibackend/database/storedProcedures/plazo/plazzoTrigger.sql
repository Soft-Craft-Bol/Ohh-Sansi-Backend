DROP TRIGGER IF EXISTS trg_update_plazo_inscripcion ON plazo_inscripcion;
DROP FUNCTION IF EXISTS update_plazo_inscripcion_auto();


CREATE OR REPLACE FUNCTION update_plazo_inscripcion_auto()
    RETURNS TRIGGER AS $$
BEGIN
    -- Desactivar el plazo que estaba activo solo si cambia el estado
    IF NEW.fecha_plazo_inscripcion_activo = TRUE THEN
        UPDATE plazo_inscripcion
        SET fecha_plazo_inscripcion_activo = FALSE
        WHERE fecha_plazo_inscripcion_activo = TRUE
          AND id_plazo_inscripcion <> NEW.id_plazo_inscripcion; -- Evita desactivar el que se está insertando/actualizando
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

   CREATE TRIGGER trg_update_plazo_inscripcion
    AFTER INSERT OR UPDATE ON plazo_inscripcion
    FOR EACH ROW
    EXECUTE FUNCTION update_plazo_inscripcion_auto();
