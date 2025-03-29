select * from area;
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION insertarea(
    nombreArea VARCHAR,
    precioArea DECIMAL,
    nombreCortoArea VARCHAR
)
    RETURNS TABLE (id_area INT, nombre_area VARCHAR, precio_area DECIMAL, nombre_corto_area VARCHAR) AS $$
BEGIN
    RETURN QUERY
        INSERT INTO area (nombre_area, precio_area, nombre_corto_area)
            VALUES (nombreArea, precioArea, nombreCortoArea)
            RETURNING *;
END;
$$ LANGUAGE plpgsql;

select insertarea('humanidades',20.5,'hum');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updatearea(
    idArea INTEGER,
    nombreArea VARCHAR,
    precioArea DECIMAL,
    nombreCortoArea VARCHAR
) RETURNS BOOLEAN AS $$
DECLARE
    rows_updated INT;
BEGIN
    UPDATE area
    SET nombre_area = nombreArea,
        precio_area = precioArea,
        nombre_corto_area = nombreCortoArea
    WHERE id_area = idArea;

    GET DIAGNOSTICS rows_updated = ROW_COUNT;

    RETURN rows_updated > 0;
END;
$$ LANGUAGE plpgsql;


select * from area;
select updatearea(1,'ciencias',20.0,'cie');
-----------------------------------------------------------------------------------------------------------------------
create or replace function deletearea(idarea integer)
returns int as $$
declare
    affected_rows int;
begin
    delete from area where id_area = idarea returning 1 into affected_rows;
    return affected_rows;
end;
$$ language plpgsql;

select deletearea(11);
select * from area;
-----------------------------------------------------------------------------------------------------------------------
create or replace function selectareabyid(idarea integer)
    returns table (id_area integer, nombre_area varchar, precio_area decimal, nombre_corto_area varchar) as $$
begin
    return query select area.id_area, area.nombre_area, area.precio_area, area.nombre_corto_area
                 from area
                 where area.id_area = idarea;
end;
$$ language plpgsql;
select * from selectareabyid(1);

-----------------------------------------------------------------------------------------------------------------------
create or replace function selectallareas()
    returns table (id_area integer, nombre_area varchar, precio_area decimal, nombre_corto_area varchar) as $$
begin
    return query select area.id_area, area.nombre_area, area.precio_area, area.nombre_corto_area
                 from area;
end;
$$ language plpgsql;
select * from selectallareas();
-----------------------------------------------------------------------------------------------------------------------
