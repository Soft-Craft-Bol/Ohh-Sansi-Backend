alter table participante
    add constraint unique_carnet_identidad_participante unique (carnet_identidad_participante);

alter table participante
    add constraint unique_carnet_identidad_participante unique (correo_electronico_participante);
