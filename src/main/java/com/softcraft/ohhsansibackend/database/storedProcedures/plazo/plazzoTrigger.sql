CREATE OR REPLACE FUNCTION validar_cambios_precio()
    RETURNS TRIGGER AS $$
BEGIN
    IF OLD.id_estado > (SELECT id_estado FROM estado_olimpiada WHERE nombre_estado = 'PLANIFICACION')
        AND NEW.precio_olimpiada <> OLD.precio_olimpiada THEN
        RAISE EXCEPTION 'No se puede modificar el precio después de la fase de PLANIFICACION';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_validar_precio
    BEFORE UPDATE ON olimpiada
    FOR EACH ROW EXECUTE FUNCTION validar_cambios_precio();