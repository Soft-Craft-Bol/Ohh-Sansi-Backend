ALTER TABLE plazo_inscripcion ADD CONSTRAINT unique_year_active
    UNIQUE (EXTRACT(YEAR FROM fecha_inicio))
    WHERE activo = true;

ALTER TABLE olimpiada ADD CONSTRAINT unique_nombre_olimpiada
    UNIQUE (nombre_olimpiada);


-- Para periodos_olimpiada
ALTER TABLE periodos_olimpiada
    DROP CONSTRAINT periodos_olimpiada_id_olimpiada_fkey;

-- Para catalogo_olimpiada
ALTER TABLE catalogo_olimpiada
    DROP CONSTRAINT fk_catalogo_relations_olimpiada;


-- Periodos
ALTER TABLE periodos_olimpiada
    ADD CONSTRAINT fk_periodos_olimpiada
        FOREIGN KEY (id_olimpiada)
            REFERENCES olimpiada(id_olimpiada)
            ON DELETE CASCADE;

-- Catalogo
ALTER TABLE catalogo_olimpiada
    ADD CONSTRAINT fk_catalogo_olimpiada
        FOREIGN KEY (id_olimpiada)
            REFERENCES olimpiada(id_olimpiada)
            ON DELETE CASCADE;