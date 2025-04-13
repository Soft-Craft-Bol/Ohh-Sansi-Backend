CREATE OR REPLACE FUNCTION selectColegioByMunicipio(idMunicipio INTEGER)

    RETURNS TABLE (
       id_departamento INTEGER,
       id_municipio INTEGER,
       id_colegio INTEGER,
       nombre_colegio VARCHAR(200),
       direccion VARCHAR(200),
       coordenadas VARCHAR(200),
       cantidad_estudiantes_colegio INTEGER
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
        cantidad_estudiantes_colegio INTEGER
    ) AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM colegio;
END;
$$ LANGUAGE plpgsql;
SELECT * FROM selectAllColegios();
-- ------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION saveColegio(
    idMunicipio INTEGER,
    nombreColegio VARCHAR,
    direccion VARCHAR,
    coordenadas VARCHAR,
    cantidadEstudiantesColegio INTEGER
)
    RETURNS TABLE (
        id_departamento INTEGER,
        id_municipio INTEGER,
        id_colegio INTEGER,
        nombre_colegio VARCHAR(200),
        direccion VARCHAR(200),
        coordenadas VARCHAR(200),
        cantidad_estudiantes_colegio INTEGER
    ) AS $$
BEGIN
    RETURN QUERY
        INSERT INTO colegio (id_municipio, nombre_colegio, direccion, coordenadas, cantidad_estudiantes_colegio)
            VALUES (idMunicipio, nombreColegio, direccion, coordenadas, cantidadEstudiantesColegio)
            RETURNING *;
END;
$$ LANGUAGE plpgsql;
SELECT * FROM saveColegio(2428, 'colegio 1', 'direccion 1', 'coordenadas 1', 100);
-- ------------------------------------------------------------------------------------------------------------------------------

