CREATE OR REPLACE FUNCTION upsertPlazoInscripcion(
    p_nombre_periodo_inscripcion VARCHAR,
    p_fecha_inicio_inscripcion DATE,
    p_fecha_fin_inscripcion DATE,
    p_fecha_inicio_olimpiadas DATE DEFAULT NULL,
    p_fecha_fin_olimpiadas DATE DEFAULT NULL,
    p_fecha_resultados DATE DEFAULT NULL,
    p_fecha_premiacion DATE DEFAULT NULL,
    p_precio_periodo NUMERIC DEFAULT NULL
)
    RETURNS TABLE (
                      idPeriodoInscripcion INTEGER,
                      nombrePeriodoInscripcion VARCHAR,
                      fechaInicioInscripcion DATE,
                      fechaFinInscripcion DATE,
                      fechaInicioOlimpiadas DATE,
                      fechaFinOlimpiadas DATE,
                      fechaResultados DATE,
                      fechaPremiacion DATE,
                      fechaPlazoInscripcionActivo BOOLEAN,
                      precioPeriodo NUMERIC
                  ) AS $$
DECLARE
    ultimo_id INTEGER;
BEGIN
    UPDATE periodos_inscripcion SET fecha_plazo_inscripcion_activo = FALSE;

    IF EXISTS (
        SELECT 1 FROM periodos_inscripcion
        WHERE EXTRACT(YEAR FROM fecha_inicio_inscripcion) = EXTRACT(YEAR FROM p_fecha_inicio_inscripcion)
    ) THEN
        UPDATE periodos_inscripcion
        SET
            nombre_periodo_inscripcion = p_nombre_periodo_inscripcion,
            fecha_inicio_inscripcion = p_fecha_inicio_inscripcion,
            fecha_fin_inscripcion = p_fecha_fin_inscripcion,
            fecha_inicio_olimpiadas = p_fecha_inicio_olimpiadas,
            fecha_fin_olimpiadas = p_fecha_fin_olimpiadas,
            fecha_resultados = p_fecha_resultados,
            fecha_premiacion = p_fecha_premiacion,
            precio_periodo = p_precio_periodo
        WHERE EXTRACT(YEAR FROM fecha_inicio_inscripcion) = EXTRACT(YEAR FROM p_fecha_inicio_inscripcion)
        RETURNING id_periodo_inscripcion INTO ultimo_id;
    ELSE
        INSERT INTO periodos_inscripcion (
            nombre_periodo_inscripcion,
            fecha_inicio_inscripcion,
            fecha_fin_inscripcion,
            fecha_inicio_olimpiadas,
            fecha_fin_olimpiadas,
            fecha_resultados,
            fecha_premiacion,
            fecha_plazo_inscripcion_activo,
            precio_periodo
        )
        VALUES (
                   p_nombre_periodo_inscripcion,
                   p_fecha_inicio_inscripcion,
                   p_fecha_fin_inscripcion,
                   p_fecha_inicio_olimpiadas,
                   p_fecha_fin_olimpiadas,
                   p_fecha_resultados,
                   p_fecha_premiacion,
                   FALSE,
                   p_precio_periodo
               )
        RETURNING id_periodo_inscripcion INTO ultimo_id;
    END IF;

    UPDATE periodos_inscripcion
    SET fecha_plazo_inscripcion_activo = TRUE
    WHERE id_periodo_inscripcion = ultimo_id
      AND CURRENT_DATE BETWEEN fecha_inicio_inscripcion AND fecha_fin_inscripcion;

    RETURN QUERY
        SELECT
            id_periodo_inscripcion AS "idPeriodoInscripcion",
            nombre_periodo_inscripcion AS "nombrePeriodoInscripcion",
            fecha_inicio_inscripcion AS "fechaInicioInscripcion",
            fecha_fin_inscripcion AS "fechaFinInscripcion",
            fecha_inicio_olimpiadas AS "fechaInicioOlimpiadas",
            fecha_fin_olimpiadas AS "fechaFinOlimpiadas",
            fecha_resultados AS "fechaResultados",
            fecha_premiacion AS "fechaPremiacion",
            fecha_plazo_inscripcion_activo AS "fechaPlazoInscripcionActivo",
            precio_periodo AS "precioPeriodo"
        FROM periodos_inscripcion
        WHERE id_periodo_inscripcion = ultimo_id;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM upsertPlazoInscripcion(
    'Periodo de Inscripción 2025',
    '2025-01-01',
    '2025-01-31',
    '2025-02-01',
    '2025-02-15',
    '2025-03-01',
    '2025-03-15',
    100.00
);
-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updatePrecioPeriodo(
    p_id_periodo INTEGER,
    p_precio NUMERIC
) RETURNS BOOLEAN AS $$
BEGIN
    UPDATE periodos_inscripcion
    SET precio_periodo = p_precio
    WHERE id_periodo_inscripcion = p_id_periodo;

    RETURN FOUND;
END;
$$ LANGUAGE plpgsql;

SELECT updatePrecioPeriodo(2, 150.00);

-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION deletePlazoInscripcion(idPeriodoInscripcion INTEGER)
    RETURNS BOOLEAN AS $$
DECLARE
    deleted_rows INT;
BEGIN
    DELETE FROM periodos_inscripcion
    WHERE id_periodo_inscripcion = idPeriodoInscripcion;

    GET DIAGNOSTICS deleted_rows = ROW_COUNT;
    RETURN deleted_rows > 0;
END;
$$ LANGUAGE plpgsql;

SELECT deletePlazoInscripcion(18);
-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectPlazoInscripcionActivo()
    RETURNS TABLE (
                      id_periodo_inscripcion INTEGER,
                        nombre_periodo_inscripcion VARCHAR,
                      fecha_inicio_inscripcion DATE,
                      fecha_fin_inscripcion DATE,
                        fecha_inicio_olimpiadas DATE,
                        fecha_fin_olimpiadas DATE,
                      fecha_resultados DATE,
                      fecha_premiacion DATE,
                      fecha_plazo_inscripcion_activo BOOLEAN
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM periodos_inscripcion
        WHERE periodos_inscripcion.fecha_plazo_inscripcion_activo = TRUE
        ORDER BY id_periodo_inscripcion DESC
        LIMIT 1;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectPlazoInscripcionActivo();
-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectPlazoInscripcionByDate(busquedaFecha DATE)
    RETURNS TABLE (
                      id_periodo_inscripcion INTEGER,
                        nombre_periodo_inscripcion VARCHAR,
                      fecha_inicio_inscripcion DATE,
                      fecha_fin_inscripcion DATE,
                        fecha_inicio_olimpiadas DATE,
                        fecha_fin_olimpiadas DATE,
                      fecha_resultados DATE,
                      fecha_premiacion DATE,
                      fecha_plazo_inscripcion_activo BOOLEAN
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM periodos_inscripcion
        WHERE busquedaFecha BETWEEN periodos_inscripcion.fecha_inicio_inscripcion AND periodos_inscripcion.fecha_fin_inscripcion;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectPlazoInscripcionByDate('2025-01-15');
-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectAllPlazoInscripcion()
    RETURNS TABLE (
                      id_periodo_inscripcion INTEGER,
                        nombre_periodo_inscripcion VARCHAR,
                      fecha_inicio_inscripcion DATE,
                      fecha_fin_inscripcion DATE,
                        fecha_inicio_olimpiadas DATE,
                        fecha_fin_olimpiadas DATE,
                      fecha_resultados DATE,
                      fecha_premiacion DATE,
                      fecha_plazo_inscripcion_activo BOOLEAN,
                  precio_periodo NUMERIC
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM periodos_inscripcion;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectAllPlazoInscripcion();
-------------------------------------------------------------------------------------------------------------------------------------------------------------

