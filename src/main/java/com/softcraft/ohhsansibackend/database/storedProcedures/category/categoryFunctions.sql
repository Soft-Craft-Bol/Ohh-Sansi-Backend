CREATE OR REPLACE FUNCTION insertCategory(codCategory VARVCHAR)
RETURNS BOOLEAN AS $$
DECLARE
    newId INTEGER;
BEGIN
    INSERT INTO category (codCategory)
    VALUES (codCategory)
    RETURNING id_category INTO newId;

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
CREATE OR REPLACE FUNCTION updateCategory(idCategory INTEGER, codCategory VARCHAR)
RETURNS BOOLEAN AS $$
BEGIN
    UPDATE     category
    SET cod_Category = codCategory
    WHERE id_category = idCategory;
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
CREATE OR REPLACE FUNCTION deleteCategory(idCategory INTEGER)
RETURNS BOOLEAN AS $$
BEGIN
    DELETE FROM category WHERE id_category = idCategory;
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
SELECT deleteCategory(2);
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectCategoryById(idCategory INTEGER)
RETURNS TABLE (id_category INTEGER, cod_category VARCHAR) AS $$
BEGIN
RETURN QUERY
SELECT category.id_category, category.cod_category
FROM category
WHERE category.id_category = idCategory;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectCategoryById(1);
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectAllCategories()
RETURNS TABLE (id_category INTEGER, cod_category VARCHAR) AS $$
BEGIN
RETURN QUERY
SELECT category.id_category, category.cod_category
FROM category;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM selectAllCategories();
-----------------------------------------------------------------------------------------------------------------------