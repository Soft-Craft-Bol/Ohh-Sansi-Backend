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

alter table departamento
    add constraint unique_nombre_departamento unique (nombre_departamento);
ALTER TABLE colegio ADD CONSTRAINT unique_nombre_colegio UNIQUE (nombre_colegio);

DELETE FROM participante WHERE id_departamento = 2432;
DELETE FROM departamento WHERE id_departamento = 2432;
delete from municipio where id_departamento = 2432;
delete from colegio where nombre_colegio = 'RIO BLANCO';
