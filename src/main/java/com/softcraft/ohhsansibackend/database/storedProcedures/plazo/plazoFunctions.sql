CREATE OR REPLACE FUNCTION upsertFechasOlimpiadas(
    idOlimpiada INTEGER,
    nombreEvento VARCHAR,
    fechaInicio DATE,
    fechaFin DATE DEFAULT NULL,
    esPublica BOOLEAN DEFAULT TRUE
)
    RETURNS TABLE (
                      id_fecha_olimpiada INTEGER,
                      id_olimpiada INTEGER,
                      nombre_evento VARCHAR,
                      fecha_inicio DATE,
                      fecha_fin DATE,
                      es_publica BOOLEAN
                  ) AS $$
DECLARE
    max_eventos INTEGER := 3;
    eventos_existentes INTEGER;
    nuevo_id INTEGER;
BEGIN
    -- ✅ Validar fechas
    IF fechaFin IS NOT NULL AND fechaFin < fechaInicio THEN
        RAISE EXCEPTION 'La fecha de fin no puede ser menor que la fecha de inicio.';
    END IF;

    -- ✅ Validar máximo de eventos por olimpiada
    SELECT COUNT(*) INTO eventos_existentes
    FROM fechas_olimpiadas f
    WHERE f.id_olimpiada = idOlimpiada;

    IF eventos_existentes >= max_eventos THEN
        RAISE EXCEPTION 'Solo se permiten % eventos por olimpiada. Ya existen %.', max_eventos, eventos_existentes;
    END IF;

    -- ✅ Verificar solapamiento de fechas
    IF EXISTS (
        SELECT 1
        FROM fechas_olimpiadas f
        WHERE f.id_olimpiada = idOlimpiada
          AND (
            (f.fecha_inicio, COALESCE(f.fecha_fin, f.fecha_inicio))
                OVERLAPS
            (fechaInicio, COALESCE(fechaFin, fechaInicio))
            )
    ) THEN
        RAISE EXCEPTION 'Las fechas del evento se superponen con otro evento ya registrado.';
    END IF;

    -- ✅ Insertar nuevo evento
    INSERT INTO fechas_olimpiadas (
        id_olimpiada, nombre_evento, fecha_inicio, fecha_fin, es_publica
    ) VALUES (
                 idOlimpiada, nombreEvento, fechaInicio, fechaFin, esPublica
             )
    RETURNING fechas_olimpiadas.id_fecha_olimpiada INTO nuevo_id;

    -- ✅ Retornar el registro creado
    RETURN QUERY
        SELECT f.id_fecha_olimpiada, f.id_olimpiada, f.nombre_evento, f.fecha_inicio, f.fecha_fin, f.es_publica
        FROM fechas_olimpiadas f
        WHERE f.id_fecha_olimpiada = nuevo_id;

END;
$$ LANGUAGE plpgsql;


SELECT * FROM fechas_olimpiadas;
SELECT * FROM upsertFechasOlimpiadas(1, 'Competencia Final', '2025-05-02', '2025-05-05', FALSE);
SELECT * FROM upsertFechasOlimpiadas(1, 'Apertura', '2025-05-01', NULL, TRUE);
SELECT * FROM upsertFechasOlimpiadas(1, NULL, NULL, NULL, TRUE, TRUE);

-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updateEsPublicaFecha(
    idFechaOlimpiada INTEGER,
    esPublica BOOLEAN
)
    RETURNS TABLE (
                      id_fecha_olimpiada INTEGER,
                      id_olimpiada INTEGER,
                      nombre_evento VARCHAR,
                      fecha_inicio DATE,
                      fecha_fin DATE,
                      es_publica BOOLEAN
                  ) AS $$
BEGIN
    UPDATE fechas_olimpiadas f
    SET es_publica = esPublica
    WHERE f.id_fecha_olimpiada = idFechaOlimpiada;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'No se encontró una fecha con ID %', idFechaOlimpiada;
    END IF;

    RETURN QUERY
        SELECT f.id_fecha_olimpiada, f.id_olimpiada, f.nombre_evento, f.fecha_inicio, f.fecha_fin, f.es_publica
        FROM fechas_olimpiadas f
        WHERE f.id_fecha_olimpiada = idFechaOlimpiada;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM updateEsPublicaFecha(1, FALSE);
SELECT * FROM updateEsPublicaFecha(1, TRUE);
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
CREATE OR REPLACE FUNCTION selectAllFechasOlimpiadas()
    RETURNS TABLE (
                      id_fecha_olimpiada INTEGER,
                      id_olimpiada INTEGER,
                      nombre_evento VARCHAR,
                      fecha_inicio DATE,
                      fecha_fin DATE,
                      es_publica BOOLEAN
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM fechas_olimpiadas;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectAllFechasOlimpiadas();
-------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectAllFechasOlimpiadas()
    RETURNS TABLE (
                      id_fecha_olimpiada INTEGER,
                      id_olimpiada INTEGER,
                      nombre_evento VARCHAR,
                      fecha_inicio DATE,
                      fecha_fin DATE,
                      es_publica BOOLEAN,
                      nombre_olimpiada VARCHAR,
                      estado_olimpiada BOOLEAN
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT f.id_fecha_olimpiada, f.id_olimpiada, f.nombre_evento, f.fecha_inicio, f.fecha_fin, f.es_publica,
               o.nombre_olimpiada, o.estado_olimpiada
        FROM fechas_olimpiadas f
        JOIN olimpiada o ON f.id_olimpiada = o.id_olimpiada;
END;
$$ LANGUAGE plpgsql;


