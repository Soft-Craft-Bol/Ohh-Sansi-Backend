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
    DECLARE
        anio_actual INTEGER := EXTRACT(YEAR FROM CURRENT_DATE);
BEGIN
    -- 1. 🔍 Check for duplicates BEFORE inserting
    IF EXISTS (
        SELECT 1 FROM olimpiada WHERE olimpiada.nombre_olimpiada = CONCAT('Periodo Olímpico ', anio)
    ) THEN
        RAISE EXCEPTION 'El período % ya existe', anio;
    END IF;

    -- 2. 🔄 Deactivate existing active periods if new one is TRUE
    IF estadoOlimpiada AND anio < anio_actual THEN
        RAISE EXCEPTION 'No se pueden activar períodos de años anteriores (%).', anio;
    END IF;

    -- 2. Desactivar períodos activos si el nuevo período es del año en curso y está activo
    IF estadoOlimpiada AND anio = anio_actual THEN
        UPDATE olimpiada
        SET estado_olimpiada = FALSE
        WHERE estado_olimpiada = TRUE AND EXTRACT(YEAR FROM CURRENT_DATE) = anio_actual;
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

SELECT * FROM insertOlimpiada(2021, FALSE, 100.00);
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
----------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION validar_modificacion_olimpiada()
    RETURNS TRIGGER AS $$
DECLARE
    estado_actual VARCHAR(50);
BEGIN
    SELECT nombre_estado INTO estado_actual
    FROM estado_olimpiada
    WHERE id_estado = NEW.id_estado;

    IF TG_OP = 'UPDATE' THEN
        -- Bloquear modificación si es olimpiada pasada
        IF OLD.anio < EXTRACT(YEAR FROM CURRENT_DATE) THEN
            RAISE EXCEPTION 'No se pueden modificar olimpiadas de años anteriores';
        END IF;

        -- Bloquear cambios si está en estado finalizado/cancelado
        IF OLD.id_estado IN (
            SELECT id_estado
            FROM estado_olimpiada
            WHERE nombre_estado IN ('FINALIZADO','CANCELADO')
        ) THEN
            RAISE EXCEPTION 'Olimpiada en estado % - Modificaciones bloqueadas', estado_actual;
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------
-- Trigger para tabla olimpiada
CREATE TRIGGER trg_bloquear_modificaciones_olimpiada
    BEFORE UPDATE ON olimpiada
    FOR EACH ROW EXECUTE FUNCTION validar_modificacion_olimpiada();

-- Trigger para periodos
CREATE TRIGGER trg_bloquear_modificaciones_periodos
    BEFORE UPDATE ON periodos_olimpiada
    FOR EACH ROW EXECUTE FUNCTION validar_modificacion_olimpiada();

-- Trigger para catálogo
CREATE TRIGGER trg_bloquear_modificaciones_catalogo
    BEFORE UPDATE ON catalogo_olimpiada
    FOR EACH ROW EXECUTE FUNCTION validar_modificacion_olimpiada();

ALTER TABLE olimpiada
    ADD COLUMN fecha_creacion TIMESTAMP DEFAULT NOW(),
    ADD COLUMN fecha_actualizacion TIMESTAMP DEFAULT NOW();

-- Trigger automático de actualización
CREATE OR REPLACE FUNCTION actualizar_timestamp()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.fecha_actualizacion = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_actualizar_timestamp
    BEFORE UPDATE ON olimpiada
    FOR EACH ROW EXECUTE FUNCTION actualizar_timestamp();