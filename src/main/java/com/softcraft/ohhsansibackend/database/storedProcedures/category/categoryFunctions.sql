CREATE OR REPLACE FUNCTION insertCategory(
    codCategoria VARCHAR,
    idArea INTEGER
)
RETURNS BOOLEAN AS $$
DECLARE
    newId INTEGER;
BEGIN
    INSERT INTO categorias (codigo_categoria, id_area)
    VALUES (codCategoria, idArea)
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

SELECT insertCategory('Guacamayo', 1);
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updateCategory(idCategoria INTEGER, codCategoria VARCHAR, idArea INTEGER)
RETURNS BOOLEAN AS $$
BEGIN
    UPDATE categorias
    SET codigo_categoria = codCategoria,
        id_area = idArea
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
SELECT updateCategory(1,'cat2', 1);
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
select * from nivel_escolar_categorias;
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectCategoryById(idCategoria INTEGER)
RETURNS TABLE (id_categoria INTEGER, codigo_categoria VARCHAR, id_area INTEGER) AS $$
BEGIN
    RETURN QUERY
    SELECT categorias.id_categoria, categorias.codigo_categoria, categorias.id_area
    FROM categorias
    WHERE categorias.id_categoria = idCategoria;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectCategoryById(1);
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectAllCategories()
RETURNS TABLE (id_categoria INTEGER, codigo_categoria VARCHAR, id_area INTEGER) AS $$
BEGIN
    RETURN QUERY
    SELECT  categorias.id_area, categorias.id_categoria, categorias.codigo_categoria,
    FROM categorias;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectAllCategories();
-----------------------------------------------------------------------------------------------------------------------