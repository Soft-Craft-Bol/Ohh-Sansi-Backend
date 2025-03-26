CREATE OR REPLACE FUNCTION selectAllMunicipios()
    RETURNS TABLE (
                      id_departamento INTEGER,
                      id_municipio INTEGER,
                      nombre_municipio VARCHAR(100),
                      numero_colegios INTEGER
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM municipio;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectAllMunicipios();
------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectMunicipioById(idMunicipio INTEGER)
    RETURNS TABLE (
                      id_departamento INTEGER,
                      id_municipio INTEGER,
                      nombre_municipio VARCHAR(100),
                      numero_colegios INTEGER
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM municipio WHERE municipio.id_municipio = idMunicipio;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectMunicipioById(2428);
-----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectMunicipioByDepartamento(idDepartamento INTEGER)
    RETURNS TABLE (
                      id_departamento INTEGER,
                      id_municipio INTEGER,
                      nombre_municipio VARCHAR(100),
                      numero_colegios INTEGER
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM municipio WHERE municipio.id_departamento = idDepartamento;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectMunicipioByDepartamento(2541);
------------------------------------------------------------------------------------------------------------

