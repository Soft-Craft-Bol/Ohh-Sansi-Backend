CREATE OR REPLACE FUNCTION insertNivelEscolar(
    nombreNivelEscolar VARCHAR
)
RETURNS BOOLEAN AS $$
DECLARE
    newId INTEGER;
    codigoNivel VARCHAR;
BEGIN
    codigoNivel := (
        SELECT STRING_AGG(LEFT(word, 1), '')
        FROM unnest(string_to_array(nombreNivelEscolar, ' ')) AS word
    );

    INSERT INTO nivel_escolar (codigo_nivel, nombre_nivel_escolar)
    VALUES (codigoNivel, nombreNivelEscolar)
    RETURNING id_nivel INTO newId;

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

SELECT insertNivelEscolar('1ro Secundaria');
SELECT insertNivelEscolar('3ro Primaria');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updateNivelEscolar(
    idLevel INTEGER,
    nombreNivelEscolar VARCHAR
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
CREATE OR REPLACE FUNCTION deleteNivelEscolar(idLevel INTEGER)
RETURNS BOOLEAN AS $$
BEGIN
    DELETE FROM nivel_escolar WHERE id_nivel = idLevel;
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
CREATE OR REPLACE FUNCTION selectNivelEscolarById(idLevel INTEGER)
RETURNS TABLE (id_nivel INTEGER, codigo_nivel VARCHAR, nombre_nivel_escolar VARCHAR) AS $$
BEGIN
    RETURN QUERY
    SELECT nivel_escolar.id_nivel, nivel_escolar.codigo_nivel, nivel_escolar.nombre_nivel_escolar
    FROM nivel_escolar
    WHERE nivel_escolar.id_nivel = idLevel;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectNivelEscolarById(1);
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectAllNivelEscolars()
RETURNS TABLE (id_nivel INTEGER, codigo_nivel VARCHAR, nombre_nivel_escolar VARCHAR) AS $$
BEGIN
    RETURN QUERY
    SELECT nivel_escolar.id_nivel, nivel_escolar.codigo_nivel, nivel_escolar.nombre_nivel_escolar
    FROM nivel_escolar;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectAllNivelEscolars();
-----------------------------------------------------------------------------------------------------------------------

