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
                 (SELECT id_estado FROM public.estado_olimpiada WHERE nombre_estado = 'PLANIFICACION'),
                p_fecha_inicio,
                p_fecha_fin
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

SELECT * FROM public.actualizar_olimpiada(1, 2024, 'Olimpiada Nacional de Ciencias', 50.00, '2024-01-01', '2024-12-31');