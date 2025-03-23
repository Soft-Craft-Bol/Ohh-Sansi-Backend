ALTER TABLE plazo_inscripcion ADD CONSTRAINT unique_year_active
    UNIQUE (EXTRACT(YEAR FROM fecha_inicio))
    WHERE activo = true;
