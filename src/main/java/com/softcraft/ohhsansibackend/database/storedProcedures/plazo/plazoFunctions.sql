CREATE OR REPLACE FUNCTION savePlazoInscripcion(
    fechaInicio DATE DEFAULT NULL,
    fechaFin DATE DEFAULT NULL,
    fechaResultados DATE DEFAULT NULL,
    fechaPremiacion DATE DEFAULT NULL,
    fechaPlazoActivo BOOLEAN DEFAULT FALSE
)
    RETURNS TABLE (
                      id_plazo_inscripcion INTEGER,
                      fecha_inicio_inscripcion DATE,
                      fecha_fin_inscripcion DATE,
                      fecha_resultados DATE,
                      fecha_premiacion DATE,
                      fecha_plazo_inscripcion_activo BOOLEAN
                  ) AS $$
BEGIN
    RETURN QUERY
        INSERT INTO plazo_inscripcion (
                                       fecha_inicio_inscripcion,
                                       fecha_fin_inscripcion,
                                       fecha_resultados,
                                       fecha_premiacion,
                                       fecha_plazo_inscripcion_activo
            )
            VALUES (
                       fechaInicio,
                       fechaFin,
                       fechaResultados,
                       fechaPremiacion,
                       fechaPlazoActivo
                   )
            RETURNING *;
END;
$$ LANGUAGE plpgsql;


SELECT savePlazoInscripcion('2025-01-01', '2025-02-01', '2025-02-10', NULL, TRUE);
-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updatePlazoInscripcion(
    idPlazo INTEGER,
    fechaInicio DATE DEFAULT NULL,
    fechaFin DATE DEFAULT NULL,
    fechaResultados DATE DEFAULT NULL,
    fechaPremiacion DATE DEFAULT NULL,
    fechaPlazoActivo BOOLEAN DEFAULT NULL
)
    RETURNS TABLE (
                      id_plazo_inscripcion INTEGER,
                      fecha_inicio_inscripcion DATE,
                      fecha_fin_inscripcion DATE,
                      fecha_resultados DATE,
                      fecha_premiacion DATE,
                      fecha_plazo_inscripcion_activo BOOLEAN
                  )
AS $$
BEGIN
    RETURN QUERY
    UPDATE plazo_inscripcion
    SET
        fecha_inicio_inscripcion = COALESCE(fechaInicio, plazo_inscripcion.fecha_inicio_inscripcion),
        fecha_fin_inscripcion = COALESCE(fechaFin, plazo_inscripcion.fecha_fin_inscripcion),
        fecha_resultados = CASE WHEN fechaResultados IS NOT NULL THEN fechaResultados ELSE plazo_inscripcion.fecha_resultados END,
        fecha_premiacion = CASE WHEN fechaPremiacion IS NOT NULL THEN fechaPremiacion ELSE plazo_inscripcion.fecha_premiacion END,
        fecha_plazo_inscripcion_activo = COALESCE(fechaPlazoActivo, plazo_inscripcion.fecha_plazo_inscripcion_activo)
    WHERE plazo_inscripcion.id_plazo_inscripcion = idPlazo
    RETURNING
        plazo_inscripcion.id_plazo_inscripcion,
        plazo_inscripcion.fecha_inicio_inscripcion,
        plazo_inscripcion.fecha_fin_inscripcion,
        plazo_inscripcion.fecha_resultados,
        plazo_inscripcion.fecha_premiacion,
        plazo_inscripcion.fecha_plazo_inscripcion_activo;
END;
$$ LANGUAGE plpgsql;

SELECT updatePlazoInscripcion(24 ,NULL,NULL,'2025-02-10', '2025-04-14', TRUE);
-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION deletePlazoInscripcion(idPlazo INTEGER)
    RETURNS BOOLEAN AS $$
DECLARE
    deleted_rows INT;
BEGIN
    DELETE FROM plazo_inscripcion
    WHERE id_plazo_inscripcion = idPlazo;

    GET DIAGNOSTICS deleted_rows = ROW_COUNT;
    RETURN deleted_rows > 0;
END;
$$ LANGUAGE plpgsql;

SELECT deletePlazoInscripcion(18);
-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectPlazoInscripcionById(idPlazo INTEGER)
    RETURNS TABLE (
                      id_plazo_inscripcion INTEGER,
                      fecha_inicio_inscripcion DATE,
                      fecha_fin_inscripcion DATE,
                      fecha_resultados DATE,
                      fecha_premiacion DATE,
                      fecha_plazo_inscripcion_activo BOOLEAN
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM plazo_inscripcion WHERE plazo_inscripcion.id_plazo_inscripcion = idPlazo;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectPlazoInscripcionById(20);
-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectPlazoInscripcionActivo()
    RETURNS TABLE (
                      id_plazo_inscripcion INTEGER,
                      fecha_inicio_inscripcion DATE,
                      fecha_fin_inscripcion DATE,
                      fecha_resultados DATE,
                      fecha_premiacion DATE,
                      fecha_plazo_inscripcion_activo BOOLEAN
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM plazo_inscripcion
        WHERE plazo_inscripcion.fecha_plazo_inscripcion_activo = TRUE
        ORDER BY id_plazo_inscripcion DESC
        LIMIT 1;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectPlazoInscripcionActivo();
-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectPlazoInscripcionByDate(busquedaFecha DATE)
    RETURNS TABLE (
                      id_plazo_inscripcion INTEGER,
                      fecha_inicio_inscripcion DATE,
                      fecha_fin_inscripcion DATE,
                      fecha_resultados DATE,
                      fecha_premiacion DATE,
                      fecha_plazo_inscripcion_activo BOOLEAN
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM plazo_inscripcion
        WHERE busquedaFecha BETWEEN plazo_inscripcion.fecha_inicio_inscripcion AND plazo_inscripcion.fecha_fin_inscripcion;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectPlazoInscripcionByDate('2025-01-15');
-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION upsertPlazoInscripcion(
    p_fecha_inicio DATE,
    p_fecha_fin DATE,
    p_fecha_resultados DATE DEFAULT NULL,
    p_fecha_premiacion DATE DEFAULT NULL
) RETURNS TABLE (
                      id_plazo_inscripcion INTEGER,
                      fecha_inicio_inscripcion DATE,
                      fecha_fin_inscripcion DATE,
                      fecha_resultados DATE,
                      fecha_premiacion DATE,
                      fecha_plazo_inscripcion_activo BOOLEAN
) AS $$
DECLARE
    ultimo_id INTEGER;
BEGIN
    UPDATE plazo_inscripcion
    SET fecha_plazo_inscripcion_activo = FALSE;

    IF EXISTS (SELECT 1 FROM plazo_inscripcion WHERE EXTRACT(YEAR FROM plazo_inscripcion.fecha_inicio_inscripcion) = EXTRACT(YEAR FROM p_fecha_inicio)) THEN
        UPDATE plazo_inscripcion
        SET fecha_inicio_inscripcion = p_fecha_inicio,
            fecha_fin_inscripcion = p_fecha_fin,
            fecha_resultados = CASE WHEN p_fecha_resultados IS NOT NULL THEN p_fecha_resultados ELSE plazo_inscripcion.fecha_resultados END,
            fecha_premiacion = CASE WHEN p_fecha_premiacion IS NOT NULL THEN p_fecha_premiacion ELSE plazo_inscripcion.fecha_premiacion END
        WHERE EXTRACT(YEAR FROM plazo_inscripcion.fecha_inicio_inscripcion) = EXTRACT(YEAR FROM p_fecha_inicio)
        RETURNING plazo_inscripcion.id_plazo_inscripcion INTO ultimo_id;
    ELSE
        INSERT INTO plazo_inscripcion (fecha_inicio_inscripcion, fecha_fin_inscripcion, fecha_resultados, fecha_premiacion, fecha_plazo_inscripcion_activo)
        VALUES (p_fecha_inicio, p_fecha_fin, p_fecha_resultados, p_fecha_premiacion, FALSE)
        RETURNING plazo_inscripcion.id_plazo_inscripcion INTO ultimo_id;
    END IF;

    UPDATE plazo_inscripcion
    SET fecha_plazo_inscripcion_activo = TRUE
    WHERE plazo_inscripcion.id_plazo_inscripcion = ultimo_id
    AND CURRENT_DATE BETWEEN plazo_inscripcion.fecha_inicio_inscripcion AND plazo_inscripcion.fecha_fin_inscripcion;

    RETURN QUERY SELECT * FROM plazo_inscripcion WHERE plazo_inscripcion.id_plazo_inscripcion = ultimo_id;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM upsertPlazoInscripcion(NULL,NULL, NULL, '2025-04-01');

SELECT * FROM upsertPlazoInscripcion('2025-01-01', '2025-04-01', '2026-02-10', NULL);
-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectAllPlazoInscripcion()
    RETURNS TABLE (
                      id_plazo_inscripcion INTEGER,
                      fecha_inicio_inscripcion DATE,
                      fecha_fin_inscripcion DATE,
                      fecha_resultados DATE,
                      fecha_premiacion DATE,
                      fecha_plazo_inscripcion_activo BOOLEAN
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM plazo_inscripcion;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectAllPlazoInscripcion();
-------------------------------------------------------------------------------------------------------------------------------------------------------------