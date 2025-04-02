create or replace function insert_usuario(
    p_carnet_identidad varchar,
    p_nombre_usuario varchar,
    p_apellido_paterno_usuario varchar,
    p_apellido_materno_usuario varchar,
    p_correo_usuario varchar,
    p_password varchar,
    p_fecha_nacimiento date
)
    returns integer as $$
declare
    v_id_usuario integer;
begin
    insert into usuario (carnet_identidad, nombre_usuario, apellido_paterno_usuario, apellido_materno_usuario, correo_usuario, password, fecha_nacimiento)
    values (p_carnet_identidad, p_nombre_usuario, p_apellido_paterno_usuario, p_apellido_materno_usuario, p_correo_usuario, p_password, p_fecha_nacimiento)
    returning id_usuario into v_id_usuario;

    return v_id_usuario;
end;
$$ language plpgsql;