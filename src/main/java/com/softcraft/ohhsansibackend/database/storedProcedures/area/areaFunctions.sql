select * from area;
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION insertarea(
    nombreArea VARCHAR,
    precioArea DECIMAL DEFAULT NULL,
    descripcionArea VARCHAR DEFAULT '',
    areaStatus BOOLEAN DEFAULT TRUE
)
    RETURNS TABLE (
                      id_area INT,
                      nombre_area VARCHAR,
                      precio_area DECIMAL,
                      nombre_corto_area VARCHAR,
                      descripcion_area VARCHAR,
                      area_status BOOLEAN
                  ) AS $$
DECLARE
    short_name VARCHAR;
BEGIN
    short_name := LOWER(SUBSTRING(nombreArea FROM 1 FOR 3));

    RETURN QUERY
        INSERT INTO area (nombre_area, precio_area, nombre_corto_area, descripcion_area, area_status)
            VALUES (nombreArea, precioArea, short_name, descripcionArea, areaStatus)
            RETURNING *;
END;
$$ LANGUAGE plpgsql;



SELECT * FROM insertarea('Humanidades', NULL, 'Descripción de humanidades');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updatearea(
    idArea INTEGER,
    nombreArea VARCHAR,
    precioArea DECIMAL,
    descripcionArea VARCHAR,
    areaStatus BOOLEAN
) RETURNS BOOLEAN AS $$
DECLARE
    rows_updated INT;
    short_name VARCHAR;
BEGIN
    short_name := LOWER(SUBSTRING(nombreArea FROM 1 FOR 3));

    UPDATE area
    SET nombre_area = nombreArea,
        precio_area = precioArea,
        nombre_corto_area = short_name,
        descripcion_area = descripcionArea,
        area_status = areaStatus
    WHERE id_area = idArea;

    GET DIAGNOSTICS rows_updated = ROW_COUNT;

    RETURN rows_updated > 0;
END;
$$ LANGUAGE plpgsql;

SELECT updatearea(36, 'Biología', 60.0, 'Área de Biología', TRUE);
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION deletearea(idArea INTEGER)
    RETURNS BOOLEAN AS $$
DECLARE
    rows_updated INT;
BEGIN
    UPDATE area
    SET area_status = FALSE
    WHERE id_area = idArea;

    GET DIAGNOSTICS rows_updated = ROW_COUNT;

    RETURN rows_updated > 0;
END;
$$ LANGUAGE plpgsql;

select deletearea(36);
select * from area;
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectareabyid(idArea INTEGER)
    RETURNS TABLE (
                      id_area INTEGER,
                      nombre_area VARCHAR,
                      precio_area DECIMAL,
                      nombre_corto_area VARCHAR,
                      descripcion_area VARCHAR,
                      area_status BOOLEAN
                  ) AS $$
BEGIN
    RETURN QUERY
        SELECT a.id_area, a.nombre_area, a.precio_area, a.nombre_corto_area, a.descripcion_area, a.area_status
        FROM area a
        WHERE a.id_area = idArea;
END;
$$ LANGUAGE plpgsql;

select * from selectareabyid(36);

-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectallareas()
    RETURNS TABLE (
                      id_area INTEGER,
                      nombre_area VARCHAR,
                      precio_area DECIMAL,
                      nombre_corto_area VARCHAR,
                      descripcion_area VARCHAR,
                      area_status BOOLEAN
                  ) AS $$
BEGIN
    RETURN QUERY
        SELECT a.id_area, a.nombre_area, a.precio_area, a.nombre_corto_area, a.descripcion_area, a.area_status
        FROM area a;
END;
$$ LANGUAGE plpgsql;

select * from selectallareas();
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION selectactiveareas()
    RETURNS TABLE (
                      id_area INTEGER,
                      nombre_area VARCHAR,
                      precio_area DECIMAL,
                      nombre_corto_area VARCHAR,
                      descripcion_area VARCHAR,
                      area_status BOOLEAN
                  ) AS $$
BEGIN
    RETURN QUERY
        SELECT a.id_area, a.nombre_area, a.precio_area, a.nombre_corto_area, a.descripcion_area, a.area_status
        FROM area a
        WHERE a.area_status = TRUE;
END;
$$ LANGUAGE plpgsql;

select * from selectactiveareas();
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updatePrecioArea(
    idArea INTEGER,
    precioArea DECIMAL
) RETURNS BOOLEAN AS $$
DECLARE
    rows_updated INT;
BEGIN
    UPDATE area
    SET precio_area = precioArea
    WHERE id_area = idArea;

    GET DIAGNOSTICS rows_updated = ROW_COUNT;

    RETURN rows_updated > 0;
END;
$$ LANGUAGE plpgsql;

SELECT updatePrecioArea(36, 70.5);