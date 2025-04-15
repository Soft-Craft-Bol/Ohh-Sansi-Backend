CREATE OR REPLACE FUNCTION insertCatalogo(
    idArea INTEGER,
    idCategoria INTEGER,
    idCatalogo INTEGER,
    idOlimpiada INTEGER
)
    RETURNS TABLE (
        id_area INTEGER,
        id_categoria INTEGER,
        id_catalogo INTEGER,
        id_olimpiada INTEGER
    ) AS $$
BEGIN
    INSERT INTO catalogo_olimpiada (id_area, id_categoria, id_catalogo, id_olimpiada)
    VALUES (idArea, idCategoria, idCatalogo, idOlimpiada)
    RETURNING id_area, id_categoria, id_catalogo, id_olimpiada INTO id_area, id_categoria, id_catalogo, id_olimpiada;

    RETURN;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM insertCatalogo(1, 1, 1, 1);
-- -----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION obtener_catalogo_por_periodo()
    RETURNS TABLE (
                      nombre_olimpiada VARCHAR,
                      nombre_area VARCHAR,
                      nombre_categoria VARCHAR,
                      grados VARCHAR[]
                  ) AS $$
BEGIN
    RETURN QUERY
        SELECT
            o.nombre_olimpiada,
            a.nombre_area,
            cat.nombre_categoria,
            ARRAY(
                    SELECT g.nombre_grado
                    FROM grado_categoria gc
                             JOIN grado g ON g.id_grado = gc.id_grado
                    WHERE gc.id_categoria = cat.id_categoria
                    order by g.id_grado
            ) AS grados
        FROM catalogo_olimpiada co
                 JOIN olimpiada o ON o.id_olimpiada = co.id_olimpiada
                 JOIN area a ON a.id_area = co.id_area
                 JOIN categorias cat ON cat.id_categoria = co.id_categoria;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM obtener_catalogo_por_periodo();

select * from catalogo_olimpiada;