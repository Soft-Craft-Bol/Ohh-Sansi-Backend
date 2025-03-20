alter table usuario
add constraint unique_carnet_identidad unique (carnet_identidad);


alter table usuario
add constraint unique_correo_usuario unique (correo_usuario);

Select * from usuario