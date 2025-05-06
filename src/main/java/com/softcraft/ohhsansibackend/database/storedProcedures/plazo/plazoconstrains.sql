ALTER TABLE plazo_inscripcion ADD CONSTRAINT unique_year_active
    UNIQUE (EXTRACT(YEAR FROM fecha_inicio))
    WHERE activo = true;

ALTER TABLE olimpiada ADD CONSTRAINT unique_nombre_olimpiada
    UNIQUE (nombre_olimpiada);
ALTER TABLE olimpiada DROP CONSTRAINT IF EXISTS pk_olimpiada CASCADE;
ALTER TABLE olimpiada DROP CONSTRAINT IF EXISTS unique_nombre_olimpiada CASCADE;
ALTER TABLE participante_catalogo
    DROP CONSTRAINT fk_particip_r22_catalogo;

ALTER TABLE participante_catalogo
    ADD CONSTRAINT fk_particip_r22_catalogo
        FOREIGN KEY (id_categoria, id_area, id_catalogo, id_olimpiada)
            REFERENCES catalogo_olimpiada (id_categoria, id_area, id_catalogo, id_olimpiada)
            ON DELETE CASCADE;
ALTER TABLE participante_tutor
    DROP CONSTRAINT fk_particip_r25_particip;

ALTER TABLE participante_tutor
    ADD CONSTRAINT fk_particip_r25_particip
        FOREIGN KEY (id_inscripcion, id_participante)
            REFERENCES participante (id_inscripcion, id_participante)
            ON DELETE CASCADE;
ALTER TABLE tutor_area_participante
    DROP CONSTRAINT tutor_area_participante_id_participante_tutor_fkey;

ALTER TABLE tutor_area_participante
    ADD CONSTRAINT tutor_area_participante_id_participante_tutor_fkey
        FOREIGN KEY (id_participante_tutor)
            REFERENCES participante_tutor (id_participante_tutor)
            ON DELETE CASCADE;
