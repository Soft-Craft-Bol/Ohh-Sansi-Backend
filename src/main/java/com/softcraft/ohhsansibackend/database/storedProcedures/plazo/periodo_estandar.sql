-- Estándar para nombres de períodos:
CREATE TABLE periodos_estandar (
                                   orden INTEGER PRIMARY KEY,
                                   nombre_evento VARCHAR(80) UNIQUE,
                                   descripcion TEXT
);

INSERT INTO periodos_estandar VALUES
                                  (1, 'CONFIGURACION', 'Configuración inicial de la olimpiada'),
                                  (2, 'PRE_INSCRIPCION', 'Período de pre-inscripciones'),
                                  (3, 'INSCRIPCION', 'Período de inscripciones formales'),
                                  (4, 'EVALUACION', 'Fase de evaluación de participantes'),
                                  (5, 'CLASIFICATORIA', 'Fase clasificatoria'),
                                  (6, 'FINAL', 'Fase final'),
                                  (7, 'RESULTADOS', 'Publicación de resultados'),
                                  (8, 'PREMIACION', 'Ceremonia de premiación');