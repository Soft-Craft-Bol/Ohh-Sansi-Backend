CREATE OR REPLACE FUNCTION insertGrado(
    nombreGrado VARCHAR
)
    RETURNS BOOLEAN AS $$
DECLARE
    newId INTEGER;
BEGIN
    INSERT INTO grado (nombre_grado)
    VALUES (nombreGrado)
    RETURNING id_grado INTO newId;

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

SELECT insertGrado('1ro Secundaria');
SELECT insertGrado('3ro Primaria');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updateGrado(
    idGrado INTEGER,
    nombreGrado VARCHAR
)
RETURNS BOOLEAN AS $$
DECLARE
    codigoNivel VARCHAR;
BEGIN
    codigoNivel := (
        SELECT STRING_AGG(LEFT(word, 1), '')
        FROM unnest(string_to_array(nombreNivelEscolar, ' ')) AS word
    );

    UPDATE nivel_escolar
    SET codigo_nivel = codigoNivel,
        nombre_nivel_escolar = nombreNivelEscolar
    WHERE id_nivel = idLevel;

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

SELECT updateNivelEscolar(1, '2do Secundaria');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION deleteGrado(idGrado INTEGER)
RETURNS BOOLEAN AS $$
BEGIN
    DELETE FROM grado WHERE id_grado = idGrado;
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

SELECT deleteNivelEscolar(2);
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectGradoById(idGrado INTEGER)
RETURNS TABLE (id_grado INTEGER, nombre_grado VARCHAR) AS $$
BEGIN
    RETURN QUERY
    SELECT g.id_grado, g.nombre_grado
    FROM grado g
    WHERE g.id_grado = idGrado;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectNivelEscolarById(1);
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectAllGrados()
RETURNS TABLE (id_grado INTEGER, nombre_grado VARCHAR) AS $$
BEGIN
    RETURN QUERY
    SELECT g.id_grado, g.nombre_grado
    FROM grado g;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectAllGrados();
-----------------------------------------------------------------------------------------------------------------------

