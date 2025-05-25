-- Primero renombramos la columna
ALTER TABLE periodos_olimpiada
    Drop COLUMN nombre_evento

-- Luego modificamos la columna para que sea SERIAL y PRIMARY KEY
ALTER TABLE periodos_olimpiada
    ALTER COLUMN id_periodo SET DATA TYPE INTEGER,
    ALTER COLUMN id_periodo SET DEFAULT nextval('fechas_olimpiadas_id_fecha_olimpiada_seq'),
    ALTER COLUMN id_periodo SET NOT NULL;

-- Actualizamos la restricción de llave primaria
ALTER TABLE periodos_olimpiada
    DROP CONSTRAINT IF EXISTS pk_fechas_olimpiadas,
    ADD CONSTRAINT pk_periodos_olimpiada PRIMARY KEY (id_periodo, id_olimpiada);
-- 1. Agregar las columnas nuevas permitiendo NULL inicialmente
ALTER TABLE periodos_olimpiada
    ADD COLUMN tipo_periodo VARCHAR(50),
    ADD COLUMN nombre_periodo VARCHAR(100),
    ADD COLUMN obligatorio BOOLEAN DEFAULT FALSE,
    ADD COLUMN orden INTEGER;

-- 2. Modificar el tipo de las columnas de fecha
ALTER TABLE periodos_olimpiada
    ALTER COLUMN fecha_inicio TYPE TIMESTAMP USING fecha_inicio::TIMESTAMP,
    ALTER COLUMN fecha_fin TYPE TIMESTAMP USING fecha_fin::TIMESTAMP;

-- 3. Actualizar datos existentes según sea necesario
-- (Aquí deberías insertar la lógica para poblar las nuevas columnas)

-- 4. Agregar las restricciones
ALTER TABLE periodos_olimpiada
    ALTER COLUMN tipo_periodo SET NOT NULL,
    ALTER COLUMN nombre_periodo SET NOT NULL,
    ALTER COLUMN fecha_inicio SET NOT NULL,
    ALTER COLUMN fecha_fin SET NOT NULL,
    ALTER COLUMN orden SET NOT NULL,
    ADD CONSTRAINT chk_tipo_periodo CHECK (tipo_periodo IN ('CONFIGURACION', 'PRE_INSCRIPCION', 'INSCRIPCION', 'EVALUACION', 'CLASIFICATORIA', 'FINAL', 'RESULTADOS', 'PREMIACION')),
    ADD CONSTRAINT chk_fechas_validas CHECK (fecha_fin >= fecha_inicio),
    ADD CONSTRAINT uq_periodo_olimpiada UNIQUE (id_olimpiada, tipo_periodo);

COMMIT;MP NOT NULL,
          ADD COLUMN obligatorio BOOLEAN DEFAULT FALSE,
    ADD COLUMN orden INTEGER NOT NULL,
          ADD CONSTRAINT chk_fechas_validas CHECK (fecha_fin >= fecha_inicio),
    ADD CONSTRAINT uq_periodo_olimpiada UNIQUE (id_olimpiada, tipo_periodo),