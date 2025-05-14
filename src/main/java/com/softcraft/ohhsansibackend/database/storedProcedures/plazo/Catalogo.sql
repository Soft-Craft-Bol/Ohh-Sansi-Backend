CREATE OR REPLACE FUNCTION insertCatalogo(
    idArea INTEGER,
    idCategoria INTEGER,
    idOlimpiada INTEGER
)
    RETURNS TABLE (
                      id_categoria INTEGER,
                      id_area INTEGER,
                      id_catalogo INTEGER,
                      id_olimpiada INTEGER
                  ) AS $$
DECLARE
    conflict_found INTEGER;
    exists_grado_categoria INTEGER;

    v_id_categoria INTEGER;
    v_id_area INTEGER;
    v_id_catalogo INTEGER;
    v_id_olimpiada INTEGER;
BEGIN
    SELECT 1 INTO exists_grado_categoria
    FROM grado_categoria gc
    WHERE gc.id_categoria = idCategoria
    LIMIT 1;

    IF exists_grado_categoria IS NULL THEN
        RAISE EXCEPTION 'No se puede registrar: la categoría % no tiene grados asociados.', idCategoria;
    END IF;

    IF EXISTS (
        SELECT 1
        FROM catalogo_olimpiada co
        WHERE co.id_categoria = idCategoria
          AND co.id_area = idArea
          AND co.id_olimpiada = idOlimpiada
    ) THEN
        RAISE EXCEPTION 'Ya existe esta categoría registrada en esta área y olimpiada.';
    END IF;

    SELECT 1 INTO conflict_found
    FROM grado_categoria gc_nueva
    WHERE gc_nueva.id_categoria = idCategoria
      AND EXISTS (
        SELECT 1
        FROM catalogo_olimpiada co
                 JOIN grado_categoria gc_existente ON gc_existente.id_categoria = co.id_categoria
        WHERE co.id_olimpiada = idOlimpiada
          AND co.id_area = idArea
          AND co.id_categoria != idCategoria
          AND gc_nueva.id_grado = gc_existente.id_grado
    )
    LIMIT 1;

    IF conflict_found IS NOT NULL THEN
        RAISE EXCEPTION 'Conflicto: Esta categoría comparte grados con otra categoría ya registrada en esta área y olimpiada.';
    END IF;

    INSERT INTO catalogo_olimpiada (id_categoria, id_area, id_olimpiada)
    VALUES (idCategoria, idArea, idOlimpiada)
    RETURNING
        catalogo_olimpiada.id_categoria,
        catalogo_olimpiada.id_area,
        catalogo_olimpiada.id_catalogo,
        catalogo_olimpiada.id_olimpiada
        INTO
            v_id_categoria,
            v_id_area,
            v_id_catalogo,
            v_id_olimpiada;

    id_categoria := v_id_categoria;
    id_area := v_id_area;
    id_catalogo := v_id_catalogo;
    id_olimpiada := v_id_olimpiada;

    RETURN NEXT;
END;
$$ LANGUAGE plpgsql;



SELECT * FROM insertCatalogo(18, 100, 1);
-- -----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION obtener_catalogo_por_periodo()
    RETURNS TABLE (
                      nombre_olimpiada VARCHAR,
                      nombre_area VARCHAR,
                      nombre_categoria VARCHAR,
                      grados VARCHAR[]
                  ) AS $$
BEGIN
    RETURN QUERY
        SELECT
            o.nombre_olimpiada,
            a.nombre_area,
            cat.nombre_categoria,
            ARRAY(
                    SELECT g.nombre_grado
                    FROM grado_categoria gc
                             JOIN grado g ON g.id_grado = gc.id_grado
                    WHERE gc.id_categoria = cat.id_categoria
                    order by g.id_grado
            ) AS grados
        FROM catalogo_olimpiada co
                 JOIN olimpiada o ON o.id_olimpiada = co.id_olimpiada
                 JOIN area a ON a.id_area = co.id_area
                 JOIN categorias cat ON cat.id_categoria = co.id_categoria;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM obtener_catalogo_por_periodo();

select * from catalogo_olimpiada;


-- -----------------------------------------------------------------------------------------------------------------------
-- Validación de fechas superpuestas
CREATE OR REPLACE FUNCTION validar_fechas_periodo()
    RETURNS TRIGGER AS $$
DECLARE
    v_conflicting_start DATE;
    v_conflicting_end DATE;
BEGIN
    -- Skip overlap check if this is an update that doesn't change dates
    IF TG_OP = 'UPDATE' AND
       OLD.fecha_inicio = NEW.fecha_inicio AND
       OLD.fecha_fin = NEW.fecha_fin THEN
        RETURN NEW;
    END IF;

    -- Check for overlaps excluding the current record (for updates)
    SELECT fecha_inicio, fecha_fin INTO v_conflicting_start, v_conflicting_end
    FROM periodos_olimpiada
    WHERE id_olimpiada = NEW.id_olimpiada
      AND (NEW.fecha_inicio, NEW.fecha_fin) OVERLAPS (fecha_inicio, fecha_fin)
      AND (TG_OP = 'INSERT' OR periodos_olimpiada.id_fecha_olimpiada != NEW.id_fecha_olimpiada)
    LIMIT 1;

    IF FOUND THEN
        RAISE EXCEPTION 'No se puede % el periodo: las fechas se superponen con otro periodo existente (% a %)',
            TG_OP,
            v_conflicting_start,
            v_conflicting_end;
    END IF;

    -- Validate date order
    IF NEW.fecha_fin < NEW.fecha_inicio THEN
        RAISE EXCEPTION 'La fecha de fin (%) no puede ser anterior a la fecha de inicio (%)',
            NEW.fecha_fin,
            NEW.fecha_inicio;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

----------------------------------------------------------------------------------------------------------------------------

CREATE TRIGGER tg_validar_fechas
    BEFORE INSERT OR UPDATE ON periodos_olimpiada
    FOR EACH ROW EXECUTE FUNCTION validar_fechas_periodo();

-- Validación de catalogo solo en PLANIFICACION
CREATE OR REPLACE FUNCTION validar_catalogo()
    RETURNS TRIGGER AS $$
DECLARE
    v_estado_actual VARCHAR(50);
BEGIN
    SELECT nombre_estado INTO v_estado_actual
    FROM estado_olimpiada
    WHERE id_estado = (SELECT id_estado FROM olimpiada WHERE id_olimpiada = NEW.id_olimpiada);

    IF v_estado_actual NOT IN ('PLANIFICACION', 'HISTORICO') THEN
        RAISE EXCEPTION 'Solo se puede modificar el catálogo en estado PLANIFICACION o HISTORICO';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_validar_catalogo
    BEFORE INSERT OR UPDATE ON catalogo_olimpiada
    FOR EACH ROW EXECUTE FUNCTION validar_catalogo();