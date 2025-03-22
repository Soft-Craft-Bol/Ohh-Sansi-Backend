select * from area;
-----------------------------------------------------------------------------------------------------------------------
create or replace function insertarea(nombreArea varchar, precioArea decimal, nombreCortoArea varchar)
    returns boolean as $$
declare
    newid integer;
begin
    insert into area (nombre_area, precio_area, nombre_corto_area)
    values (nombreArea, precioArea, nombreCortoArea)
    returning id_area into newid;
    if newid is not null then
        return true;
    else
        return false;
    end if;
exception
    when others then
        return false;
end;
$$ language plpgsql;
select insertarea('humanidades',20.5,'hum');
-----------------------------------------------------------------------------------------------------------------------
create or replace function updatearea(idarea integer, nombrearea varchar, precioarea decimal, nombrecortoarea varchar)
    returns boolean as $$
begin
    update area
    set nombre_area = nombrearea, precio_area = precioarea, nombre_corto_area = nombrecortoarea
    where id_area = idarea;
    if found then
        return true;
    else
        return false;
    end if;
exception
    when others then
        return false;
end;
$$ language plpgsql;

select * from area;
select updatearea(1,'ciencias',20.0,'cie');
-----------------------------------------------------------------------------------------------------------------------
--se recibe como parametro
create or replace function deletearea(idarea integer)
    returns boolean as $$
begin
    delete from area where id_area = idarea;
    if found then
        return true;
    else
        return false;
    end if;
exception
    when others then
        return false;
end;
$$ language plpgsql;

select deletearea(2);
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
