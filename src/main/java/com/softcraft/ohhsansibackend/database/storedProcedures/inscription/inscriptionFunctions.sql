CREATE OR REPLACE FUNCTION insertInscripcion(
    fechaInscripcion DATE,
    horaInscripcion TIME
)
    RETURNS TABLE (id_inscripcion INTEGER, fecha_inscripcion DATE, hora_inscripcion TIME) AS $$
DECLARE
    newId INTEGER;
BEGIN
    INSERT INTO inscripcion (fecha_inscripcion, hora_inscripcion)
    VALUES (fechaInscripcion, horaInscripcion)
    RETURNING inscripcion.id_inscripcion INTO newId;

    RETURN QUERY
        SELECT i.id_inscripcion, i.fecha_inscripcion, i.hora_inscripcion
        FROM inscripcion i
        WHERE i.id_inscripcion = newId;
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
    SET fecha_inscripcion = fechaInscripcion, hora_inscripcion = horaInscripcion
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
SELECT updateInscripcion(10, '2021-10-10', '10:00:00');
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
   SELECT inscripcion.id_inscripcion, inscripcion.fecha_inscripcion, inscripcion.hora_inscripcion
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
    SELECT inscripcion.id_inscripcion, inscripcion.fecha_inscripcion, inscripcion.hora_inscripcion
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
    SELECT i.id_inscripcion, i.fecha_inscripcion, i.hora_inscripcion
    FROM inscripcion i
    WHERE i.fecha_inscripcion = fechaInscripcion AND i.hora_inscripcion = horaInscripcion;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectInscripcionByDateAndTime('2025-11-14', '20:40:10');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectInscripcionesByDateRange(
    fechaInicio DATE,
    fechaFin DATE
)
    RETURNS TABLE (id_inscripcion INTEGER, fecha_inscripcion DATE, hora_inscripcion TIME) AS $$
BEGIN
    RETURN QUERY
        SELECT i.id_inscripcion, i.fecha_inscripcion, i.hora_inscripcion
        FROM inscripcion i
        WHERE i.fecha_inscripcion BETWEEN fechaInicio AND fechaFin
        ORDER BY i.fecha_inscripcion ASC, i.hora_inscripcion ASC;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectInscripcionesByDateRange('2021-10-01', '2025-10-15');
-----------------------------------------------------------------------------------------------------------------------