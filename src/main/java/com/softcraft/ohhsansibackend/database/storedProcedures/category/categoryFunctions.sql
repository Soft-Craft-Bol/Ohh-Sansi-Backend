CREATE OR REPLACE FUNCTION insertCategory(
    nombreCategoria VARCHAR
)
RETURNS BOOLEAN AS $$
DECLARE
    newId INTEGER;
BEGIN
    INSERT INTO categorias (nombre_categoria)
    VALUES (nombreCategoria)
    RETURNING id_categoria INTO newId;

    IF newId IS NOT NULL THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RETURN FALSE;
END;

$$ LANGUAGE plpgsql;

SELECT insertCategory('Guacamayo');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updateCategory(idCategoria INTEGER, nombreCategoria VARCHAR)
RETURNS BOOLEAN AS $$
BEGIN
    UPDATE categorias
    SET nombre_categoria = nombreCategoria
    WHERE id_categoria = idCategoria;
    IF FOUND THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RETURN FALSE;
END;
$$ LANGUAGE plpgsql;
SELECT updateCategory(1,'cat2');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION deleteCategory(idCategoria INTEGER)
RETURNS BOOLEAN AS $$
BEGIN
    DELETE FROM categorias WHERE id_categoria = idCategoria;
    IF FOUND THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RETURN FALSE;
END;
$$ LANGUAGE plpgsql;
SELECT deleteCategory(16);

select * from categorias;
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectCategoryById(idCategoria INTEGER)
RETURNS TABLE (id_categoria INTEGER, nombre_categoria VARCHAR) AS $$
BEGIN
    RETURN QUERY
    SELECT categorias.id_categoria, categorias.nombre_categoria
    FROM categorias
    WHERE categorias.id_categoria = idCategoria;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectCategoryById(1);

SELECT * FROM grado_categoria;
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectAllCategories()
RETURNS TABLE (id_categoria INTEGER, nombre_categoria VARCHAR) AS $$
BEGIN
    RETURN QUERY
    SELECT categorias.id_categoria, categorias.nombre_categoria
    FROM categorias;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectAllCategories();
-----------------------------------------------------------------------------------------------------------------------