CREATE OR REPLACE FUNCTION insertInscripcion(
    fechaInscripcion DATE,
    horaInscripcion TIME,
)
RETURNS BOOLEAN AS $$
DECLARE
    newId INTEGER;
BEGIN
    INSERT INTO inscripcion (fecha_inscripcion, hora_inscripcion)
    VALUES (fechaInscripcion, horaInscripcion)
    RETURNING id_inscripcion INTO newId;

    IF newId IS NOT NULL THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RETURN FALSE;
END;
$$ LANGUAGE plpgsql;
SELECT insertInscripcion('2021-10-10', '10:00:00');
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
   SELECT inscripcion.id_inscripcion, inscripcion.fecha_inscripcion, inscripcion.hora_inscripcion
   FROM inscripcion
   WHERE inscripcion.id_inscripcion = idInscripcion;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectInscripcionById(1);
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectAllInscription()
RETURNS TABLE (id_inscripcion INTEGER, fecha_inscripcion DATE, hora_inscripcion TIME) AS $$
BEGIN
    RETURN QUERY
    SELECT inscripcion.id_inscripcion, inscripcion.fecha_inscripcion, inscripcion.hora_inscripcion
    FROM inscripcion;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectAllInscription();
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectInscriptionByDateAndTime(
    fechaInscripcion DATE,
    horaInscripcion TIME
)
RETURNS TABLE (id_inscripcion INTEGER, fecha_inscripcion DATE, hora_inscripcion TIME) AS $$
BEGIN
    RETURN QUERY
    SELECT inscripcion.id_inscripcion, inscripcion.fecha_inscripcion, inscripcion.hora_inscripcion
    FROM inscripcion
    WHERE inscripcion.fecha_inscripcion = fechaInscripcion AND inscripcion.hora_inscripcion = horaInscripcion;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectInscriptionByDateAndTime('2021-10-10', '10:00:00');
-----------------------------------------------------------------------------------------------------------------------
