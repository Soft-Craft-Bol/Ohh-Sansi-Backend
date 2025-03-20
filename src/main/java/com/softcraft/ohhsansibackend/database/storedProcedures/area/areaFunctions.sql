select * from area;
-----------------------------------------------------------------------------------------------------------------------
    CREATE OR REPLACE FUNCTION InsertArea(nombreArea VARCHAR, precioArea DECIMAL, nombreCortoArea VARCHAR)
        RETURNS BOOLEAN AS $$
    DECLARE
        newId INTEGER;
    BEGIN
        INSERT INTO AREA (NOMBRE_AREA, PRECIO_AREA, NOMBRE_CORTO_AREA)
        VALUES (nombreArea, precioArea, nombreCortoArea)
        RETURNING ID_AREA INTO newId;
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
select InsertArea('HUmanidades',20.0,'HUM');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION UpdateArea(idArea INTEGER, nombreArea VARCHAR, precioArea DECIMAL, nombreCortoArea VARCHAR)
    RETURNS BOOLEAN AS $$
BEGIN
    UPDATE AREA
    SET NOMBRE_AREA = nombreArea, PRECIO_AREA = precioArea, NOMBRE_CORTO_AREA = nombreCortoArea
    WHERE ID_AREA = idArea;
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

select * from area;
select UpdateArea(1,'Ciencias',20.0,'CIE');
-----------------------------------------------------------------------------------------------------------------------
--se recibe como parametro
CREATE OR REPLACE FUNCTION DeleteArea(idArea INTEGER)
    RETURNS BOOLEAN AS $$
BEGIN
    DELETE FROM AREA WHERE ID_AREA = idArea;
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

select DeleteArea(2);
select * from area;
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION SelectAreaById(idArea INTEGER)
    RETURNS TABLE (ID_AREA INTEGER, NOMBRE_AREA VARCHAR, PRECIO_AREA DECIMAL, NOMBRE_CORTO_AREA VARCHAR) AS $$
BEGIN
    RETURN QUERY SELECT AREA.ID_AREA, AREA.NOMBRE_AREA, AREA.PRECIO_AREA, AREA.NOMBRE_CORTO_AREA
                 FROM AREA
                 WHERE AREA.ID_AREA = idArea;
END;
$$ LANGUAGE plpgsql;
select * from SelectAreaById(1);

-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION SelectAllAreas()
    RETURNS TABLE (ID_AREA INTEGER, NOMBRE_AREA VARCHAR, PRECIO_AREA DECIMAL, NOMBRE_CORTO_AREA VARCHAR) AS $$
BEGIN
    RETURN QUERY SELECT AREA.ID_AREA, AREA.NOMBRE_AREA, AREA.PRECIO_AREA, AREA.NOMBRE_CORTO_AREA
                 FROM AREA;
END;
$$ LANGUAGE plpgsql;
select * from SelectAllAreas();
-----------------------------------------------------------------------------------------------------------------------
