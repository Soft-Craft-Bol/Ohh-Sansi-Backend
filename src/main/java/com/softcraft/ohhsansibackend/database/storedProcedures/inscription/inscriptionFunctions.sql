CREATE OR REPLACE FUNCTION insertInscripcion(
    fechaInscripcion DATE,
    horaInscripcion TIME
)
RETURNS BOOLEAN AS $$
DECLARE
    newId INTEGER;
BEGIN
    INSERT INTO inscripcion (fecha_incripcion, hora_inscripcion)
    VALUES (fechaInscripcion, horaInscripcion)
    RETURNING id_inscripcion INTO newId;

    RETURN newId IS NOT NULL;
EXCEPTION
    WHEN OTHERS THEN
        RAISE NOTICE 'Error al insertar inscripcion: %', SQLERRM;
        RETURN FALSE;
END;
$$ LANGUAGE plpgsql;
SELECT insertInscripcion('2021-10-10', '10:00:00');
SELECT insertInscripcion('2025-03-21', '14:30:00');

-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updateInscripcion(
       idInscripcion INTEGER,
       fechaInscripcion DATE,
       horaInscripcion TIME
)
RETURNS BOOLEAN AS $$
BEGIN
    UPDATE inscripcion
    SET fecha_incripcion = fechaInscripcion, hora_inscripcion = horaInscripcion
    WHERE id_inscripcion = idInscripcion;
    IF FOUND THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RETURN FALSE;
END;
$$ LANGUAGE plpgsql;
SELECT updateInscripcion(1, '2021-10-10', '10:00:00');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION deleteInscripcion(idInscripcion INTEGER)
RETURNS BOOLEAN AS $$
BEGIN
    DELETE FROM inscripcion WHERE id_inscripcion = idInscripcion;
    IF FOUND THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RETURN FALSE;
END;
$$ LANGUAGE plpgsql;

SELECT deleteInscripcion(2);
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectInscripcionById(idInscripcion INTEGER)
RETURNS TABLE (id_inscripcion INTEGER, fecha_inscripcion DATE, hora_inscripcion TIME) AS $$
BEGIN
   RETURN QUERY
   SELECT inscripcion.id_inscripcion, inscripcion.fecha_incripcion, inscripcion.hora_inscripcion
   FROM inscripcion
   WHERE inscripcion.id_inscripcion = idInscripcion;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectInscripcionById(1);
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectAllInscripciones()
RETURNS TABLE (id_inscripcion INTEGER, fecha_inscripcion DATE, hora_inscripcion TIME) AS $$
BEGIN
    RETURN QUERY
    SELECT inscripcion.id_inscripcion, inscripcion.fecha_incripcion, inscripcion.hora_inscripcion
    FROM inscripcion;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectAllInscripciones();
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectInscripcionByDateAndTime(
    fechaInscripcion DATE,
    horaInscripcion TIME
)
RETURNS TABLE (id_inscripcion INTEGER, fecha_inscripcion DATE, hora_inscripcion TIME) AS $$
BEGIN
    RETURN QUERY
    SELECT i.id_inscripcion, i.fecha_incripcion, i.hora_inscripcion
    FROM inscripcion i
    WHERE i.fecha_incripcion = fechaInscripcion AND i.hora_inscripcion = horaInscripcion;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectInscripcionByDateAndTime('2021-10-10', '10:00:00');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectInscripcionesByDateRange(
    fechaInicio DATE,
    fechaFin DATE
)
    RETURNS TABLE (id_inscripcion INTEGER, fecha_inscripcion DATE, hora_inscripcion TIME) AS $$
BEGIN
    RETURN QUERY
        SELECT i.id_inscripcion, i.fecha_incripcion, i.hora_inscripcion
        FROM inscripcion i
        WHERE i.fecha_incripcion BETWEEN fechaInicio AND fechaFin
        ORDER BY i.fecha_incripcion ASC, i.hora_inscripcion ASC;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectInscripcionesByDateRange('2021-10-01', '2025-10-15');
-----------------------------------------------------------------------------------------------------------------------