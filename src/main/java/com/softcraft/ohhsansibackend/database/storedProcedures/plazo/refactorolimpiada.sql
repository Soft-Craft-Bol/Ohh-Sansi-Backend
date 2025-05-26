
SELECT nombre_area, descripcion_area, nombre_grado, nombre_olimpiada, a.id_area
FROM catalogo_olimpiada co, olimpiada o, grado_categoria gc, grado g, area a, categorias c
WHERE co.id_olimpiada = o.id_olimpiada
    AND co.id_area = a.id_area
    AND co.id_categoria=c.id_categoria
  AND co.id_categoria = gc.id_categoria
  AND gc.id_grado = g.id_grado
AND co.id_olimpiada = ?
order by id_catalogo



SELECT        o.id_olimpiada,
                 o.anio,
                  o.nombre_olimpiada,
                    e.nombre_estado,
                       o.precio_olimpiada
                    FROM    olimpiada o         JOIN     estado_olimpiada e ON o.id_estado = e.id_estado;