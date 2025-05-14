CREATE OR REPLACE FUNCTION saveDepartamento(
    idDepartamento INTEGER,
    nombreDepartamento VARCHAR,
    nombreCorto VARCHAR
)
    RETURNS TABLE (
        id_departamento INTEGER,
        nombre_departamento VARCHAR(100),
        nombre_corto VARCHAR(20)
    )
AS $$
BEGIN
    RETURN QUERY
        INSERT INTO departamento (id_departamento,nombre_departamento, nombre_corto)
            VALUES (idDepartamento,nombreDepartamento, nombreCorto)
            RETURNING *;
END;
$$ LANGUAGE plpgsql;




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

ALTER TABLE participante
DROP CONSTRAINT IF EXISTS fk_participante_departamento,
ADD CONSTRAINT fk_participante_departamento FOREIGN KEY (id_departamento)
REFERENCES departamento (id_departamento) ON DELETE CASCADE;

ALTER TABLE municipio
DROP CONSTRAINT IF EXISTS fk_municipio_departamento,
ADD CONSTRAINT fk_municipio_departamento FOREIGN KEY (id_departamento)
REFERENCES departamento (id_departamento) ON DELETE CASCADE;

ALTER TABLE colegio
DROP CONSTRAINT IF EXISTS fk_colegio_departamento,
ADD CONSTRAINT fk_colegio_departamento FOREIGN KEY (id_departamento)
REFERENCES departamento (id_departamento) ON DELETE CASCADE;

ALTER TABLE participante_tutor
    DROP CONSTRAINT IF EXISTS fk_participante_tutor,
    ADD CONSTRAINT fk_participante_tutor FOREIGN KEY (id_departamento)
        REFERENCES departamento (id_departamento) ON DELETE CASCADE;