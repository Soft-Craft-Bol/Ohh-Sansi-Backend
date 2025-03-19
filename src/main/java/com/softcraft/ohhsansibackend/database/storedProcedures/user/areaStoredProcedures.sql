select * from area;
--funcon para inseratr un area
CREATE OR REPLACE FUNCTION InsertArea(nombreArea VARCHAR)
    RETURNS BOOLEAN AS $$
DECLARE
    newId INTEGER;
BEGIN
    INSERT INTO AREA (NOMBRE_AREA) VALUES (nombreArea) RETURNING ID_AREA INTO newId;
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
select InsertArea('HUmanidades');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION UpdateArea(nombreArea VARCHAR, idArea INTEGER)
    RETURNS BOOLEAN AS $$
BEGIN
    UPDATE AREA SET NOMBRE_AREA = nombreArea WHERE ID_AREA = idArea;
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
--parametro el id y se cambia el nombre del area
select UpdateArea('Mecanica Cuantica', 5);
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

select DeleteArea(5);
select * from area;
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION SelectAreaById(idArea INTEGER)
    RETURNS TABLE (ID_AREA INTEGER, NOMBRE_AREA VARCHAR) AS $$
BEGIN
    RETURN QUERY SELECT AREA.ID_AREA, AREA.NOMBRE_AREA FROM AREA WHERE AREA.ID_AREA = idArea;
END;
$$ LANGUAGE plpgsql;
select * from SelectAreaById(1);

-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION SelectAllAreas()
    RETURNS TABLE (ID_AREA INTEGER, NOMBRE_AREA VARCHAR) AS $$
BEGIN
    RETURN QUERY SELECT * FROM AREA;
END;
$$ LANGUAGE plpgsql;
select * from SelectAllAreas();
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION SelectAreaByNombre(nombreArea VARCHAR)
    RETURNS TABLE (ID_AREA INTEGER, NOMBRE_AREA VARCHAR) AS $$
BEGIN
    RETURN QUERY SELECT * FROM AREA WHERE NOMBRE_AREA = nombreArea;
END;
$$ LANGUAGE plpgsql;