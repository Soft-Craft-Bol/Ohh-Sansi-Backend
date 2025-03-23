alter table tutor
    add constraint unique_carnet_identidad_tutor unique (carnet_identidad_tutor);

alter table tutor
    add constraint unique_correo_tutor unique (email_tutor);