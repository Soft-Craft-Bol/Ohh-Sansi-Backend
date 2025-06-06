CREATE OR REPLACE FUNCTION public.crear_olimpiada(
    p_anio INTEGER,
    p_nombre VARCHAR(100),
    p_precio NUMERIC(10,2)
) RETURNS public.olimpiada AS $$
DECLARE
    v_olimpiada public.olimpiada;
BEGIN
    -- Validar año futuro
    IF p_anio < EXTRACT(YEAR FROM CURRENT_DATE) THEN
        RAISE EXCEPTION 'Solo se pueden crear olimpiadas para el año actual o futuros';
    END IF;

    INSERT INTO public.olimpiada (
        nombre_olimpiada,
        precio_olimpiada,
        anio,
        id_estado
    ) VALUES (
                 p_nombre,
                 p_precio,
                 p_anio,
                 (SELECT id_estado FROM public.estado_olimpiada WHERE nombre_estado = 'PLANIFICACION')
             ) RETURNING * INTO v_olimpiada;

    RETURN v_olimpiada;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM public.crear_olimpiada(2024, 'Olimpiada Nacional de Ciencias', 50.00);
---------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectOlimpiada()
    RETURNS TABLE (
                      id_olimpiada INTEGER,
                      anio INTEGER,
                      nombre_olimpiada VARCHAR,
                      nombre_estado VARCHAR,
                      precio_olimpiada DECIMAL,
                        fecha_inicio DATE,
                        fecha_fin DATE
                  ) AS $$
BEGIN
    RETURN QUERY
        SELECT
            o.id_olimpiada,
            o.anio,
            o.nombre_olimpiada,
            e.nombre_estado,
            o.precio_olimpiada
        FROM
            olimpiada o
                JOIN
            estado_olimpiada e ON o.id_estado = e.id_estado;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectOlimpiada();
---------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION actualizar_olimpiada(
    p_id_olimpiada INTEGER,
    p_anio INTEGER,
    p_nombre VARCHAR(100),
    p_precio NUMERIC(10,2)
) RETURNS public.olimpiada AS $$
DECLARE
    v_olimpiada public.olimpiada;
BEGIN
    -- Validar año futuro
    IF p_anio < EXTRACT(YEAR FROM CURRENT_DATE) THEN
        RAISE EXCEPTION 'Solo se pueden actualizar olimpiadas para el año actual o futuros';
    END IF;

    UPDATE public.olimpiada
    SET anio = p_anio,
        nombre_olimpiada = p_nombre,
        precio_olimpiada = p_precio
    WHERE id_olimpiada = p_id_olimpiada
    RETURNING * INTO v_olimpiada;

    RETURN v_olimpiada;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM public.actualizar_olimpiada(102, 2025, 'Olimpiada Nacional de Ciencias', 50.00);


SELECT DISTINCT
    p.apellido_paterno,
    p.apellido_materno,
    p.nombre_participante,
    p.id_inscripcion,
    c.nombre_colegio,
    m.nombre_municipio,
    d.nombre_departamento,
    g.nombre_grado
FROM orden_de_pago op
         JOIN estado_orden_de_pago eop ON eop.id_estado = op.id_estado
         JOIN inscripcion i ON op.id_inscripcion = i.id_inscripcion
         JOIN participante p ON p.id_inscripcion = i.id_inscripcion
         JOIN participante_catalogo pc ON p.id_participante = pc.id_participante
         JOIN catalogo_olimpiada co ON pc.id_catalogo = co.id_catalogo
         JOIN olimpiada o ON co.id_olimpiada = o.id_olimpiada  -- Esta conexión faltaba
         JOIN area a ON co.id_area = a.id_area  -- Conectar área através del catálogo
         JOIN colegio c ON p.id_colegio = c.id_colegio
         JOIN municipio m ON c.id_municipio = m.id_municipio
         JOIN departamento d ON m.id_departamento = d.id_departamento
         JOIN grado g ON g.id_grado = p.id_grado
WHERE eop.estado = 'PAGADO'
  AND a.id_area = 6  -- Filtro por área
  AND o.id_olimpiada = 102  -- Filtro por olimpiada
ORDER BY g.nombre_grado;



CREATE EXTENSION IF NOT EXISTS pg_cron