CREATE OR REPLACE FUNCTION insertOlimpiada(
    anio INTEGER,
    precioOlimpiada DECIMAL DEFAULT NULL
)
    RETURNS TABLE (
                      id_olimpiada INT,
                      nombre_olimpiada VARCHAR,
                      estado_olimpiada BOOLEAN,
                      precio_olimpiada DECIMAL
                  ) AS $$
    DECLARE
        anio_actual INTEGER := EXTRACT(YEAR FROM CURRENT_DATE);
        estadoOlimpiada BOOLEAN := (anio = anio_actual);
BEGIN
    IF EXISTS (
        SELECT 1 FROM olimpiada WHERE olimpiada.nombre_olimpiada = CONCAT('Periodo Olímpico ', anio)
    ) THEN
        RAISE EXCEPTION 'El período % ya existe', anio;
    END IF;

    IF estadoOlimpiada THEN
        UPDATE olimpiada
        SET estado_olimpiada = FALSE
        WHERE olimpiada.estado_olimpiada = TRUE;
    END IF;

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

SELECT * FROM insertOlimpiada(2023, 100.00);
SELECT * FROM insertOlimpiada(2025, NULL);
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
    -- Primero verificamos si la olimpiada existe
    IF NOT EXISTS (SELECT 1 FROM olimpiada WHERE id_olimpiada = idOlimpiada) THEN
        RAISE EXCEPTION 'Olimpiada con ID % no encontrada', idOlimpiada;
    END IF;

    -- Verificamos si la olimpiada está activa
    IF EXISTS (SELECT 1 FROM olimpiada WHERE id_olimpiada = idOlimpiada AND estado_olimpiada = true) THEN
        RAISE EXCEPTION 'No se puede actualizar el precio de una olimpiada activa (ID: %)', idOlimpiada;
    END IF;

    -- Realizamos la actualización
    UPDATE olimpiada
    SET precio_olimpiada = nuevoPrecio
    WHERE id_olimpiada = idOlimpiada
      AND estado_olimpiada = false;

    GET DIAGNOSTICS rows_updated = ROW_COUNT;
    RETURN rows_updated > 0;
END;
$$ LANGUAGE plpgsql;

SELECT updatePrecioOlimpiada(1, 200.00);
---------------------------------------------------------------------------------------------------------------------------
-- Función para insertar un nuevo período
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
    -- Desactivar períodos activos si el nuevo período está activo
    IF estadoOlimpiada THEN
        UPDATE olimpiada
        SET estado_olimpiada = FALSE
        WHERE estado_olimpiada = TRUE;
    END IF;

    -- Insertar el nuevo período
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

-- Función para actualizar un período existente
CREATE OR REPLACE FUNCTION updateOlimpiada(
    idOlimpiada INTEGER,
    nombreOlimpiada VARCHAR,
    estadoOlimpiada BOOLEAN,
    precioOlimpiada DECIMAL
) RETURNS BOOLEAN AS $$
DECLARE
    rows_updated INT;
BEGIN
    -- Desactivar períodos activos si el período actualizado está activo
    IF estadoOlimpiada THEN
        UPDATE olimpiada
        SET estado_olimpiada = FALSE
        WHERE estado_olimpiada = TRUE AND id_olimpiada != idOlimpiada;
    END IF;

    -- Actualizar el período
    UPDATE olimpiada
    SET nombre_olimpiada = nombreOlimpiada,
        estado_olimpiada = estadoOlimpiada,
        precio_olimpiada = precioOlimpiada
    WHERE id_olimpiada = idOlimpiada;

    GET DIAGNOSTICS rows_updated = ROW_COUNT;

    RETURN rows_updated > 0;
END;
$$ LANGUAGE plpgsql;