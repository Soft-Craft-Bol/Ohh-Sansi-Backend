CREATE OR REPLACE FUNCTION insertLevelEscolar(
    nombreNivelEscolar VARCHAR
)
RETURNS BOOLEAN AS $$
DECLARE
    newId INTEGER;
    codigoNivel VARCHAR;
BEGIN
    codigoNivel :- regexp_replace(nombreNivelEscolar, '[^0-9A-Za-z]', '', 'g';
    codigoNivel :- left(codigoNivel, 2);

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

SELECT insertLevelEscolar('1ro Secundaria');
SELECT insertLevelEscolar('3ro Primaria');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updateLevelEscolar(
    idLevel INTEGER,
    nombreNivelEscolar VARCHAR
)
RETURNS BOOLEAN AS $$
DECLARE
    codigoNivel VARCHAR;
BEGIN
    codigoNivel :- regexp_replace(nombreNivelEscolar, '[^0-9A-Za-z]', '', 'g';
    codigoNivel :- left(codigoNivel, 2);

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

SELECT updateLevelEscolar(1, '2do Secundaria');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION deleteLevelEscolar(idLevel INTEGER)
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

SELECT deleteLevelEscolar(2);
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectLevelEscolarById(idLevel INTEGER)
RETURNS TABLE (id_nivel INTEGER, codigo_nivel VARCHAR, nombre_nivel_escolar VARCHAR) AS $$
BEGIN
    RETURN QUERY
    SELECT nivel_escolar.id_nivel, nivel_escolar.codigo_nivel, nivel_escolar.nombre_nivel_escolar
    FROM nivel_escolar
    WHERE nivel_escolar.id_nivel = idLevel;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectLevelEscolarById(1);
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectAllLevelEscolar()
RETURNS TABLE (id_nivel INTEGER, codigo_nivel VARCHAR, nombre_nivel_escolar VARCHAR) AS $$
BEGIN
    RETURN QUERY
    SELECT nivel_escolar.id_nivel, nivel_escolar.codigo_nivel, nivel_escolar.nombre_nivel_escolar
    FROM nivel_escolar;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectAllLevelEscolar();
-----------------------------------------------------------------------------------------------------------------------

