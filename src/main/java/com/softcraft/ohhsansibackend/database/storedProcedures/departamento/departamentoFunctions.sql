CREATE OR REPLACE FUNCTION selectAllDepartamentos()
    RETURNS TABLE (
                      id_departamento INTEGER,
                      nombre_departamento VARCHAR(100),
                      nombre_corto VARCHAR(20)
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM departamento;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectAllDepartamentos();
-- -------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectDepartamentoById(idDepartamento INTEGER)
    RETURNS TABLE (
                      id_departamento INTEGER,
                      nombre_departamento VARCHAR(100),
                      nombre_corto VARCHAR(20)
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT * FROM departamento WHERE departamento.id_departamento = idDepartamento;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectDepartamentoById(1);