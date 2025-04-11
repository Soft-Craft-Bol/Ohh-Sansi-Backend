CREATE OR REPLACE FUNCTION selectColegioByMunicipio(idMunicipio INTEGER)

    RETURNS TABLE (
       id_departamento INTEGER,
       id_municipio INTEGER,
       id_colegio INTEGER,
       nombre_colegio VARCHAR(200),
       direccion VARCHAR(200),
       coordenadas VARCHAR(200),
       cantidad_estudiantes_colegio INTEGER DEFAULT NOT NULL
       ) AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM colegio WHERE colegio.id_municipio = idMunicipio;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectColegioByMunicipio(2428);
-- ------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectAllColegios()
    RETURNS TABLE (
        id_departamento INTEGER,
        id_municipio INTEGER,
        id_colegio INTEGER,
        nombre_colegio VARCHAR(200),
        direccion VARCHAR(200),
        coordenadas VARCHAR(200),
        cantidad_estudiantes_colegio INTEGER DEFAULT NOT NULL
    ) AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM colegio;
END;
$$ LANGUAGE plpgsql;
SELECT * FROM selectAllColegios();
