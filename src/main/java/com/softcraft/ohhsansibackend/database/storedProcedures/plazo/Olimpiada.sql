CREATE OR REPLACE FUNCTION insertOlimpiada(
    anio INTEGER,
    estadoOlimpiada BOOLEAN DEFAULT FALSE,
    precioOlimpiada DECIMAL DEFAULT NULL
)
    RETURNS TABLE (
                      id_olimpiada INT,
                      nombre_olimpiada VARCHAR,
                      estado_olimpiada BOOLEAN,
                      precio_olimpiada DECIMAL
                  ) AS $$
BEGIN
    -- 1. 🔍 Check for duplicates BEFORE inserting
    IF EXISTS (
        SELECT 1 FROM olimpiada WHERE olimpiada.nombre_olimpiada = CONCAT('Periodo Olímpico ', anio)
    ) THEN
        RAISE EXCEPTION 'El período % ya existe', anio;
    END IF;

    -- 2. 🔄 Deactivate existing active periods if new one is TRUE
    IF estadoOlimpiada THEN
        UPDATE olimpiada SET estado_olimpiada = FALSE WHERE olimpiada.estado_olimpiada = TRUE;
    END IF;

    -- 3. ✅ Insert new period
    RETURN QUERY
        INSERT INTO olimpiada (
                               nombre_olimpiada,
                               estado_olimpiada,
                               precio_olimpiada
            )
            VALUES (
                       CONCAT('Periodo Olímpico ', anio),
                       estadoOlimpiada,
                       precioOlimpiada
                   )
            RETURNING *;
END;
$$ LANGUAGE plpgsql;


SELECT * FROM insertOlimpiada(2025, TRUE, NULL);
---------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updateOlimpiada(
    idOlimpiada INTEGER,
    nombreOlimpiada VARCHAR,
    estadoOlimpiada BOOLEAN,
    precioOlimpiada DECIMAL
) RETURNS BOOLEAN AS $$
DECLARE
    rows_updated INT;
BEGIN
    UPDATE olimpiada
    SET nombre_olimpiada = nombreOlimpiada,
        estado_olimpiada = estadoOlimpiada,
        precio_olimpiada = precioOlimpiada
    WHERE id_olimpiada = idOlimpiada;

    GET DIAGNOSTICS rows_updated = ROW_COUNT;

    RETURN rows_updated > 0;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM olimpiada;
SELECT updateOlimpiada(1, 'Olimpiada de Ciencias', TRUE, 150.00);
---------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION deleteOlimpiada(idOlimpiada INTEGER)
    RETURNS INT AS $$
DECLARE
    affected_rows INT;
BEGIN
    DELETE FROM olimpiada WHERE id_olimpiada = idOlimpiada RETURNING 1 INTO affected_rows;
    RETURN affected_rows;
END;
$$ LANGUAGE plpgsql;
SELECT deleteOlimpiada(11);
SELECT * FROM olimpiada;
---------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectOlimpiadaById(idOlimpiada INTEGER)
    RETURNS TABLE (id_olimpiada INTEGER, nombre_olimpiada VARCHAR, estado_olimpiada BOOLEAN, precio_olimpiada DECIMAL) AS $$
BEGIN
    RETURN QUERY SELECT olimpiada.id_olimpiada, olimpiada.nombre_olimpiada, olimpiada.estado_olimpiada, olimpiada.precio_olimpiada
    FROM olimpiada
    WHERE id_olimpiada = idOlimpiada;
END;
$$ LANGUAGE plpgsql;
SELECT * FROM selectOlimpiadaById(1);

---------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectOlimpiada()
    RETURNS TABLE (id_olimpiada INTEGER, nombre_olimpiada VARCHAR, estado_olimpiada BOOLEAN, precio_olimpiada DECIMAL) AS $$
BEGIN
    RETURN QUERY SELECT olimpiada.id_olimpiada, olimpiada.nombre_olimpiada, olimpiada.estado_olimpiada, olimpiada.precio_olimpiada
    FROM olimpiada;
END;
$$ LANGUAGE plpgsql;
SELECT * FROM selectOlimpiada();
---------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectOlimpiadaByEstado(estadoOlimpiada BOOLEAN)
    RETURNS TABLE (id_olimpiada INTEGER, nombre_olimpiada VARCHAR, estado_olimpiada BOOLEAN, precio_olimpiada DECIMAL) AS $$
BEGIN
    RETURN QUERY SELECT olimpiada.id_olimpiada, olimpiada.nombre_olimpiada, olimpiada.estado_olimpiada, olimpiada.precio_olimpiada
    FROM olimpiada
    WHERE estado_olimpiada = estadoOlimpiada;
END;
$$ LANGUAGE plpgsql;
SELECT * FROM selectOlimpiadaByEstado(TRUE);
---------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updatePrecioOlimpiada(
    idOlimpiada INT,
    nuevoPrecio DECIMAL
)
    RETURNS BOOLEAN AS
$$
DECLARE
    rows_updated INT;
BEGIN
    UPDATE olimpiada
    SET precio_olimpiada = nuevoPrecio
    WHERE id_olimpiada = idOlimpiada;

    GET DIAGNOSTICS rows_updated = ROW_COUNT;

    IF rows_updated = 0 THEN
        RAISE EXCEPTION 'Olimpiada con ID % no encontrada', idOlimpiada;
    END IF;

    RETURN rows_updated > 0;
END;
$$ LANGUAGE plpgsql;

SELECT updatePrecioOlimpiada(1, 200.00);
---------------------------------------------------------------------------------------------------------------------------
