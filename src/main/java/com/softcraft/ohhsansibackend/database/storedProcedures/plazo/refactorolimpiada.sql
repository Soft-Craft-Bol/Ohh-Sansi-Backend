CREATE TABLE estado_olimpiada (
                                  id_estado SERIAL PRIMARY KEY,
                                  nombre_estado VARCHAR(50) NOT NULL UNIQUE,
                                  permite_modificaciones BOOLEAN DEFAULT true,
                                  descripcion VARCHAR(300)
);

ALTER TABLE olimpiada
    ADD COLUMN anio INTEGER NOT NULL;
ALTER TABLE fechas_olimpiadas RENAME TO periodos_olimpiada;

ALTER TABLE periodos_olimpiada
    DROP COLUMN anio


INSERT INTO estado_olimpiada (nombre_estado, descripcion) VALUES
                                                              ('PLANIFICACION', 'Fase de preparación inicial - se pueden modificar todos los datos'),
                                                              ('PRE_INSCRIPCION', 'Periodo de pre-inscripciones activo - bloquea modificaciones de precio'),
                                                              ('INSCRIPCION_ABIERTA', 'Inscripciones abiertas - bloquea cambios en catálogo'),
                                                              ('EN_PROGRESO', 'Evento en ejecución - solo consultas permitidas'),
                                                              ('FINALIZADO', 'Evento concluido - datos históricos'),
                                                              ('CANCELADO', 'Evento cancelado - registro inactivo');

ALTER TABLE catalogo_olimpiada
    ADD COLUMN permitido_desde_estado INTEGER REFERENCES estado_olimpiada(id_estado);