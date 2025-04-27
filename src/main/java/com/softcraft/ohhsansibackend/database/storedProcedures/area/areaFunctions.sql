select * from area;
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION insertarea(
    nombreArea VARCHAR,
    nombreCortoArea VARCHAR,
    descripcionArea VARCHAR
)
    RETURNS TABLE (id_area INT, nombre_area VARCHAR, nombre_corto_area VARCHAR,descripcion_area VARCHAR) AS $$
BEGIN
    RETURN QUERY
        INSERT INTO area (nombre_area, nombre_corto_area, descripcion_area)
            VALUES (nombreArea, nombreCortoArea, descripcionArea)
            RETURNING *;
END;
$$ LANGUAGE plpgsql;

select insertarea('ciencias', 'cie', 'ciencias naturales');
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION updatearea(
    idArea INTEGER,
    nombreArea VARCHAR,
    nombreCortoArea VARCHAR,
    descripcionArea VARCHAR
) RETURNS BOOLEAN AS $$
DECLARE
    rows_updated INT;
BEGIN
    UPDATE area
    SET nombre_area = nombreArea,
        nombre_corto_area = nombreCortoArea,
        descripcion_area = descripcionArea
    WHERE id_area = idArea;

    GET DIAGNOSTICS rows_updated = ROW_COUNT;

    RETURN rows_updated > 0;
END;
$$ LANGUAGE plpgsql;


select * from area;
select updatearea(1,'ciencias','cie', 'ciencias naturales');
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
    returns table (id_area integer, nombre_area varchar, nombre_corto_area varchar,descripcion_area varchar) as $$
begin
    return query select area.id_area, area.nombre_area, area.nombre_corto_area, area.descripcion_area
                 from area
                 where area.id_area = idarea;
end;
$$ language plpgsql;
select * from selectareabyid(1);

-----------------------------------------------------------------------------------------------------------------------
create or replace function selectallareas()
    returns table (id_area integer, nombre_area varchar, nombre_corto_area varchar, descripcion_area varchar) as $$
begin
    return query select area.id_area, area.nombre_area, area.nombre_corto_area, area.descripcion_area
                 from area;
end;
$$ language plpgsql;
select * from selectallareas();
-----------------------------------------------------------------------------------------------------------------------
