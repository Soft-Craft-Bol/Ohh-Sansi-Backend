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



SELECT * FROM insertCatalogo(18, 100, 69);
-- -----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION obtener_catalogo_por_periodo()
    RETURNS TABLE (
        id_olimpiada INTEGER,
                      nombre_olimpiada VARCHAR,
                        id_area INTEGER,
                      nombre_area VARCHAR,
                      nombre_categoria VARCHAR,
                      grados VARCHAR[]
                  ) AS $$
BEGIN
    RETURN QUERY
        SELECT
            o.id_olimpiada,
            o.nombre_olimpiada,
            a.id_area,
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
select po.*
from olimpiada o, estado_olimpiada eo, periodos_olimpiada po
where o.id_estado = eo.id_estado
  and eo.nombre_estado = 'EN INSCRIPCION'
  and CURRENT_DATE between DATE(po.fecha_inicio) and DATE(po.fecha_fin);

CREATE OR REPLACE FUNCTION upsertCatalogo(
    p_id_catalogo INTEGER DEFAULT NULL,
    p_id_area INTEGER DEFAULT NULL,
    p_id_categoria INTEGER DEFAULT NULL,
    p_id_olimpiada INTEGER DEFAULT NULL
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
    v_id_catalogo INTEGER;
    en_inscripcion BOOLEAN;
    is_update BOOLEAN := FALSE;
    current_area INTEGER;
    current_categoria INTEGER;
BEGIN
    -- Validar parámetros requeridos
    IF p_id_area IS NULL THEN
        RAISE EXCEPTION 'El parámetro p_id_area es requerido';
    END IF;

    IF p_id_categoria IS NULL THEN
        RAISE EXCEPTION 'El parámetro p_id_categoria es requerido';
    END IF;

    IF p_id_olimpiada IS NULL THEN
        RAISE EXCEPTION 'El parámetro p_id_olimpiada es requerido';
    END IF;

    -- Validación 0: Verificar que la olimpiada no esté en estado "EN INSCRIPCION"
    SELECT EXISTS (
        SELECT 1
        FROM olimpiada o
                 JOIN estado_olimpiada eo ON o.id_estado = eo.id_estado
        WHERE o.id_olimpiada = p_id_olimpiada
          AND eo.nombre_estado = 'EN INSCRIPCION'
    ) INTO en_inscripcion;

    IF en_inscripcion THEN
        RAISE EXCEPTION 'No se puede modificar el catálogo mientras la olimpiada está en estado "EN INSCRIPCION"';
    END IF;

    -- Validación 1: Verificar que la categoría tenga grados asociados
    SELECT COUNT(*) INTO exists_grado_categoria
    FROM grado_categoria gc
    WHERE gc.id_categoria = p_id_categoria;

    IF exists_grado_categoria = 0 THEN
        RAISE EXCEPTION 'No se puede registrar: la categoría % no tiene grados asociados.', p_id_categoria;
    END IF;

    -- Determinar si es UPDATE y obtener valores actuales
    IF p_id_catalogo IS NOT NULL THEN
        SELECT co.id_area, co.id_categoria
        INTO current_area, current_categoria
        FROM catalogo_olimpiada co
        WHERE co.id_catalogo = p_id_catalogo;

        IF FOUND THEN
            is_update := TRUE;
        ELSE
            RAISE EXCEPTION 'No se encontró el catálogo con ID %', p_id_catalogo;
        END IF;
    END IF;

    -- Validación 2: Verificar duplicados de área-categoría-olimpiada
    -- Solo aplicar si:
    -- - Es INSERT (p_id_catalogo IS NULL), O
    -- - Es UPDATE y cambió el área o la categoría
    IF NOT is_update OR (is_update AND (current_area != p_id_area OR current_categoria != p_id_categoria)) THEN
        IF EXISTS (
            SELECT 1
            FROM catalogo_olimpiada co
            WHERE co.id_categoria = p_id_categoria
              AND co.id_area = p_id_area
              AND co.id_olimpiada = p_id_olimpiada
              AND (NOT is_update OR co.id_catalogo != p_id_catalogo)
        ) THEN
            RAISE EXCEPTION 'Ya existe esta categoría registrada en esta área y olimpiada.';
        END IF;
    END IF;

    -- Validación 3: Verificar conflictos de grados solapados
    -- Solo aplicar si:
    -- - Es INSERT, O
    -- - Es UPDATE y cambió la categoría
    IF NOT is_update OR (is_update AND current_categoria != p_id_categoria) THEN
        -- Buscar si algún grado de la nueva categoría ya existe en otra categoría del mismo área y olimpiada
        SELECT 1 INTO conflict_found
        FROM grado_categoria gc_nueva
                 INNER JOIN grado_categoria gc_existente ON gc_nueva.id_grado = gc_existente.id_grado
                 INNER JOIN catalogo_olimpiada co ON gc_existente.id_categoria = co.id_categoria
        WHERE gc_nueva.id_categoria = p_id_categoria
          AND co.id_olimpiada = p_id_olimpiada
          AND co.id_area = p_id_area
          AND co.id_categoria != p_id_categoria
          AND (NOT is_update OR co.id_catalogo != p_id_catalogo)
        LIMIT 1;

        IF conflict_found IS NOT NULL THEN
            RAISE EXCEPTION 'Conflicto: Esta categoría comparte grados con otra categoría ya registrada en esta área y olimpiada.';
        END IF;
    END IF;

    -- Operación UPSERT
    IF is_update THEN
        -- UPDATE
        UPDATE catalogo_olimpiada
        SET
            id_categoria = p_id_categoria,
            id_area = p_id_area,
            id_olimpiada = p_id_olimpiada
        WHERE catalogo_olimpiada.id_catalogo = p_id_catalogo
        RETURNING
            catalogo_olimpiada.id_categoria,
            catalogo_olimpiada.id_area,
            catalogo_olimpiada.id_catalogo,
            catalogo_olimpiada.id_olimpiada
            INTO
                id_categoria,
                id_area,
                id_catalogo,
                id_olimpiada;

        RETURN NEXT;
    ELSE
        -- INSERT
        INSERT INTO catalogo_olimpiada (id_categoria, id_area, id_olimpiada)
        VALUES (p_id_categoria, p_id_area, p_id_olimpiada)
        RETURNING
            catalogo_olimpiada.id_categoria,
            catalogo_olimpiada.id_area,
            catalogo_olimpiada.id_catalogo,
            catalogo_olimpiada.id_olimpiada
            INTO
                id_categoria,
                id_area,
                id_catalogo,
                id_olimpiada;

        RETURN NEXT;
    END IF;
END;
$$ LANGUAGE plpgsql;
SELECT * FROM upsertCatalogo(NULL, 1, 124, 135);

select * from catalogo_olimpiada;
SELECT * FROM grado_categoria
select * from area
