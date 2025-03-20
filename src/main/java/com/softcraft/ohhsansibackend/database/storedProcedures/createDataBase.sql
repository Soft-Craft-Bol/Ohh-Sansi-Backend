/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     19/03/2025 19:48:09                          */
/*==============================================================*/


/*==============================================================*/
/* Table: area                                                  */
/*==============================================================*/
create table area (
                      id_area              serial               not null,
                      nombre_area          varchar(300)         not null,
                      precio_area          decimal              not null,
                      nombre_corto_area    varchar(10)          not null,
                      constraint pk_area primary key (id_area)
);

/*==============================================================*/
/* Index: area_pk                                               */
/*==============================================================*/
create unique index area_pk on area (
                                     id_area
    );

/*==============================================================*/
/* Table: area_nivel_escolar                                    */
/*==============================================================*/
create table area_nivel_escolar (
                                    id_nivel             int4                 not null,
                                    id_area              int4                 not null,
                                    id_area_nivel_escolar serial               not null,
                                    constraint pk_area_nivel_escolar primary key (id_nivel, id_area, id_area_nivel_escolar)
);

/*==============================================================*/
/* Index: area_nivel_escolar_pk                                 */
/*==============================================================*/
create unique index area_nivel_escolar_pk on area_nivel_escolar (
                                                                 id_nivel,
                                                                 id_area,
                                                                 id_area_nivel_escolar
    );

/*==============================================================*/
/* Index: r9_fk                                                 */
/*==============================================================*/
create  index r9_fk on area_nivel_escolar (
                                           id_area
    );

/*==============================================================*/
/* Index: r33_fk                                                */
/*==============================================================*/
create  index r33_fk on area_nivel_escolar (
                                            id_nivel
    );

/*==============================================================*/
/* Table: categorias                                            */
/*==============================================================*/
create table categorias (
                            id_area              int4                 not null,
                            id_categoria         serial               not null,
                            codigo_categoria     varchar(10)          not null,
                            constraint pk_categorias primary key (id_area, id_categoria)
);

/*==============================================================*/
/* Index: categorias_pk                                         */
/*==============================================================*/
create unique index categorias_pk on categorias (
                                                 id_area,
                                                 id_categoria
    );

/*==============================================================*/
/* Index: r35_fk                                                */
/*==============================================================*/
create  index r35_fk on categorias (
                                    id_area
    );

/*==============================================================*/
/* Table: colegio                                               */
/*==============================================================*/
create table colegio (
                         id_departamento      int4                 not null,
                         id_municipio         int4                 not null,
                         id_colegio           serial               not null,
                         id_tipo_colegio      int4                 null,
                         nombre_colegio       varchar(200)         not null,
                         direccion            varchar(200)         not null,
                         coordenadas          varchar(200)         not null,
                         cantidad_estudiantes_colegio int4                 null,
                         constraint pk_colegio primary key (id_departamento, id_municipio, id_colegio)
);

/*==============================================================*/
/* Index: colegio_pk                                            */
/*==============================================================*/
create unique index colegio_pk on colegio (
                                           id_departamento,
                                           id_municipio,
                                           id_colegio
    );

/*==============================================================*/
/* Index: r6_fk                                                 */
/*==============================================================*/
create  index r6_fk on colegio (
                                id_tipo_colegio
    );

/*==============================================================*/
/* Index: r3_fk                                                 */
/*==============================================================*/
create  index r3_fk on colegio (
                                id_departamento,
                                id_municipio
    );

/*==============================================================*/
/* Table: comprobante_pago                                      */
/*==============================================================*/
create table comprobante_pago (
                                  id_inscripcion       int4                 not null,
                                  id_orden_pago        int4                 not null,
                                  id_comprobante_pago  serial               not null,
                                  monto_pagado         decimal              not null,
                                  fecha_pago           date                 not null,
                                  cod_transaccion      varchar(400)         not null,
                                  imagen_comprobante   varchar(1024)        not null,
                                  nombre_receptor      varchar(1024)        not null,
                                  notas_adicionales    varchar(1024)        not null,
                                  constraint pk_comprobante_pago primary key (id_inscripcion, id_orden_pago, id_comprobante_pago)
);

/*==============================================================*/
/* Index: comprobante_pago_pk                                   */
/*==============================================================*/
create unique index comprobante_pago_pk on comprobante_pago (
                                                             id_inscripcion,
                                                             id_orden_pago,
                                                             id_comprobante_pago
    );

/*==============================================================*/
/* Index: r30_fk                                                */
/*==============================================================*/
create  index r30_fk on comprobante_pago (
                                          id_inscripcion,
                                          id_orden_pago
    );

/*==============================================================*/
/* Table: departamento                                          */
/*==============================================================*/
create table departamento (
                              id_departamento      serial               not null,
                              nombre_departamento  varchar(100)         not null,
                              nombre_corto         varchar(20)          not null,
                              constraint pk_departamento primary key (id_departamento)
);

/*==============================================================*/
/* Index: departamento_pk                                       */
/*==============================================================*/
create unique index departamento_pk on departamento (
                                                     id_departamento
    );

/*==============================================================*/
/* Table: estado_orden_de_pago                                  */
/*==============================================================*/
create table estado_orden_de_pago (
                                      id_estado            serial               not null,
                                      estado               varchar(100)         not null,
                                      constraint pk_estado_orden_de_pago primary key (id_estado)
);

/*==============================================================*/
/* Index: estado_orden_de_pago_pk                               */
/*==============================================================*/
create unique index estado_orden_de_pago_pk on estado_orden_de_pago (
                                                                     id_estado
    );

/*==============================================================*/
/* Table: evento                                                */
/*==============================================================*/
create table evento (
                        id_evento            serial               not null,
                        nombre_evento        varchar(300)         not null,
                        descripcion_evento   varchar(300)         not null,
                        constraint pk_evento primary key (id_evento)
);

/*==============================================================*/
/* Index: evento_pk                                             */
/*==============================================================*/
create unique index evento_pk on evento (
                                         id_evento
    );

/*==============================================================*/
/* Table: inscripcion                                           */
/*==============================================================*/
create table inscripcion (
                             id_inscripcion       serial               not null,
                             fecha_incripcion     date                 not null,
                             hora_inscripcion     time                 not null,
                             constraint pk_inscripcion primary key (id_inscripcion)
);

/*==============================================================*/
/* Index: inscripcion_pk                                        */
/*==============================================================*/
create unique index inscripcion_pk on inscripcion (
                                                   id_inscripcion
    );

/*==============================================================*/
/* Table: inscripcion_area                                      */
/*==============================================================*/
create table inscripcion_area (
                                  id_area              int4                 not null,
                                  id_inscripcion       int4                 not null,
                                  id_inscripcion_area  serial               not null,
                                  constraint pk_inscripcion_area primary key (id_area, id_inscripcion, id_inscripcion_area)
);

/*==============================================================*/
/* Index: inscripcion_area_pk                                   */
/*==============================================================*/
create unique index inscripcion_area_pk on inscripcion_area (
                                                             id_area,
                                                             id_inscripcion,
                                                             id_inscripcion_area
    );

/*==============================================================*/
/* Index: r21_fk                                                */
/*==============================================================*/
create  index r21_fk on inscripcion_area (
                                          id_inscripcion
    );

/*==============================================================*/
/* Index: r22_fk                                                */
/*==============================================================*/
create  index r22_fk on inscripcion_area (
                                          id_area
    );

/*==============================================================*/
/* Table: log                                                   */
/*==============================================================*/
create table log (
                     id_log               serial               not null,
                     id_evento            int4                 not null,
                     fecha_evento         date                 not null,
                     hora_evento          time                 not null,
                     dato_viejo           varchar(1024)        null,
                     dato_nuevo           varchar(1024)        null,
                     consulta             varchar(1024)        null,
                     tabla                varchar(200)         null,
                     pid_usuario          int4                 not null,
                     id_usuario           int4                 not null,
                     nombre_completo_usuario varchar(200)         null,
                     constraint pk_log primary key (id_log)
);

/*==============================================================*/
/* Index: log_pk                                                */
/*==============================================================*/
create unique index log_pk on log (
                                   id_log
    );

/*==============================================================*/
/* Index: r42_fk                                                */
/*==============================================================*/
create  index r42_fk on log (
                             id_evento
    );

/*==============================================================*/
/* Table: metodo_pago                                           */
/*==============================================================*/
create table metodo_pago (
                             id_metodo_pago       serial               not null,
                             metodo_pago          varchar(100)         not null,
                             constraint pk_metodo_pago primary key (id_metodo_pago)
);

/*==============================================================*/
/* Index: metodo_pago_pk                                        */
/*==============================================================*/
create unique index metodo_pago_pk on metodo_pago (
                                                   id_metodo_pago
    );

/*==============================================================*/
/* Table: municipio                                             */
/*==============================================================*/
create table municipio (
                           id_departamento      int4                 not null,
                           id_municipio         serial               not null,
                           nombre_municipio     varchar(100)         not null,
                           numero_colegios      int4                 not null,
                           constraint pk_municipio primary key (id_departamento, id_municipio)
);

/*==============================================================*/
/* Index: municipio_pk                                          */
/*==============================================================*/
create unique index municipio_pk on municipio (
                                               id_departamento,
                                               id_municipio
    );

/*==============================================================*/
/* Index: r1_fk                                                 */
/*==============================================================*/
create  index r1_fk on municipio (
                                  id_departamento
    );

/*==============================================================*/
/* Table: nivel_escolar                                         */
/*==============================================================*/
create table nivel_escolar (
                               id_nivel             serial               not null,
                               codigo_nivel         varchar(10)          not null,
                               nombre_nivel_escolar varchar(50)          not null,
                               constraint pk_nivel_escolar primary key (id_nivel)
);

/*==============================================================*/
/* Index: nivel_escolar_pk                                      */
/*==============================================================*/
create unique index nivel_escolar_pk on nivel_escolar (
                                                       id_nivel
    );

/*==============================================================*/
/* Table: nivel_escolar_categorias                              */
/*==============================================================*/
create table nivel_escolar_categorias (
                                          id_nivel             int4                 not null,
                                          id_area              int4                 not null,
                                          id_categoria         int4                 not null,
                                          id_nivel_escolar_categorias serial               not null,
                                          constraint pk_nivel_escolar_categorias primary key (id_nivel, id_area, id_categoria, id_nivel_escolar_categorias)
);

/*==============================================================*/
/* Index: nivel_escolar_categorias_pk                           */
/*==============================================================*/
create unique index nivel_escolar_categorias_pk on nivel_escolar_categorias (
                                                                             id_nivel,
                                                                             id_area,
                                                                             id_categoria,
                                                                             id_nivel_escolar_categorias
    );

/*==============================================================*/
/* Index: r37_fk                                                */
/*==============================================================*/
create  index r37_fk on nivel_escolar_categorias (
                                                  id_area,
                                                  id_categoria
    );

/*==============================================================*/
/* Index: r38_fk                                                */
/*==============================================================*/
create  index r38_fk on nivel_escolar_categorias (
                                                  id_nivel
    );

/*==============================================================*/
/* Table: orden_de_pago                                         */
/*==============================================================*/
create table orden_de_pago (
                               id_inscripcion       int4                 not null,
                               id_orden_pago        serial               not null,
                               id_metodo_pago       int4                 null,
                               id_estado            int4                 null,
                               fecha_emision_orden_pago date                 not null,
                               fecha_vencimiento    date                 not null,
                               monto_total_pago     decimal              not null,
                               cod_orden_pago       varchar(1024)        not null,
                               constraint pk_orden_de_pago primary key (id_inscripcion, id_orden_pago)
);

/*==============================================================*/
/* Index: orden_de_pago_pk                                      */
/*==============================================================*/
create unique index orden_de_pago_pk on orden_de_pago (
                                                       id_inscripcion,
                                                       id_orden_pago
    );

/*==============================================================*/
/* Index: r32_fk                                                */
/*==============================================================*/
create  index r32_fk on orden_de_pago (
                                       id_metodo_pago
    );

/*==============================================================*/
/* Index: r34_fk                                                */
/*==============================================================*/
create  index r34_fk on orden_de_pago (
                                       id_estado
    );

/*==============================================================*/
/* Index: relationship_24_fk                                    */
/*==============================================================*/
create  index relationship_24_fk on orden_de_pago (
                                                   id_inscripcion
    );

/*==============================================================*/
/* Table: participante                                          */
/*==============================================================*/
create table participante (
                              id_inscripcion       int4                 not null,
                              id_participante      serial               not null,
                              id_departamento      int4                 not null,
                              id_municipio         int4                 not null,
                              id_colegio           int4                 not null,
                              id_general           varchar(50)          null,
                              apellido_paterno     varchar(50)          not null,
                              apellido_materno     varchar(50)          not null,
                              nombre_participante  varchar(50)          not null,
                              fecha_nacimiento     date                 not null,
                              codigo_unico_participante varchar(1024)        not null,
                              constraint pk_participante primary key (id_inscripcion, id_participante)
);

/*==============================================================*/
/* Index: participante_pk                                       */
/*==============================================================*/
create unique index participante_pk on participante (
                                                     id_inscripcion,
                                                     id_participante
    );

/*==============================================================*/
/* Index: r24_fk                                                */
/*==============================================================*/
create  index r24_fk on participante (
                                      id_inscripcion
    );

/*==============================================================*/
/* Index: r7_fk                                                 */
/*==============================================================*/
create  index r7_fk on participante (
                                     id_departamento,
                                     id_municipio,
                                     id_colegio
    );

/*==============================================================*/
/* Table: participante_tutor                                    */
/*==============================================================*/
create table participante_tutor (
                                    id_tutor             int4                 not null,
                                    id_inscripcion       int4                 not null,
                                    id_participante      int4                 not null,
                                    id_participante_tutor serial               not null,
                                    constraint pk_participante_tutor primary key (id_tutor, id_inscripcion, id_participante, id_participante_tutor)
);

/*==============================================================*/
/* Index: participante_tutor_pk                                 */
/*==============================================================*/
create unique index participante_tutor_pk on participante_tutor (
                                                                 id_tutor,
                                                                 id_inscripcion,
                                                                 id_participante,
                                                                 id_participante_tutor
    );

/*==============================================================*/
/* Index: r25_fk                                                */
/*==============================================================*/
create  index r25_fk on participante_tutor (
                                            id_inscripcion,
                                            id_participante
    );

/*==============================================================*/
/* Index: r4_fk                                                 */
/*==============================================================*/
create  index r4_fk on participante_tutor (
                                           id_tutor
    );

/*==============================================================*/
/* Table: permiso                                               */
/*==============================================================*/
create table permiso (
                         id_permiso           serial               not null,
                         nombre_permiso       varchar(50)          not null,
                         descripcion_permiso  varchar(200)         not null,
                         constraint pk_permiso primary key (id_permiso)
);

/*==============================================================*/
/* Index: permiso_pk                                            */
/*==============================================================*/
create unique index permiso_pk on permiso (
                                           id_permiso
    );

/*==============================================================*/
/* Table: rol                                                   */
/*==============================================================*/
create table rol (
                     id_rol               serial               not null,
                     nombre_rol           varchar(50)          not null,
                     constraint pk_rol primary key (id_rol)
);

/*==============================================================*/
/* Index: rol_pk                                                */
/*==============================================================*/
create unique index rol_pk on rol (
                                   id_rol
    );

/*==============================================================*/
/* Table: rol_permiso                                           */
/*==============================================================*/
create table rol_permiso (
                             id_rol               int4                 not null,
                             id_permiso           int4                 not null,
                             id_rol_permiso       serial               not null,
                             rol_permiso_activo   bool                 not null,
                             constraint pk_rol_permiso primary key (id_rol, id_permiso, id_rol_permiso)
);

/*==============================================================*/
/* Index: rol_permiso_pk                                        */
/*==============================================================*/
create unique index rol_permiso_pk on rol_permiso (
                                                   id_rol,
                                                   id_permiso,
                                                   id_rol_permiso
    );

/*==============================================================*/
/* Index: r45_fk                                                */
/*==============================================================*/
create  index r45_fk on rol_permiso (
                                     id_permiso
    );

/*==============================================================*/
/* Index: r46_fk                                                */
/*==============================================================*/
create  index r46_fk on rol_permiso (
                                     id_rol
    );

/*==============================================================*/
/* Table: sesion                                                */
/*==============================================================*/
create table sesion (
                        id_usuario           int4                 not null,
                        id_sesion            serial               not null,
                        nombre_usuario_sesion varchar(50)          not null,
                        direccion_ip         varchar(50)          not null,
                        fecha_hora_inicio_sesion date                 not null,
                        fecha_hora_cierre_sesion date                 not null,
                        tiempo_sesion        time                 not null,
                        constraint pk_sesion primary key (id_usuario, id_sesion)
);

/*==============================================================*/
/* Index: sesion_pk                                             */
/*==============================================================*/
create unique index sesion_pk on sesion (
                                         id_usuario,
                                         id_sesion
    );

/*==============================================================*/
/* Index: r43_fk                                                */
/*==============================================================*/
create  index r43_fk on sesion (
                                id_usuario
    );

/*==============================================================*/
/* Table: tipo_colegio                                          */
/*==============================================================*/
create table tipo_colegio (
                              id_tipo_colegio      serial               not null,
                              nombre_tipo_colegio  varchar(100)         not null,
                              constraint pk_tipo_colegio primary key (id_tipo_colegio)
);

/*==============================================================*/
/* Index: tipo_colegio_pk                                       */
/*==============================================================*/
create unique index tipo_colegio_pk on tipo_colegio (
                                                     id_tipo_colegio
    );

/*==============================================================*/
/* Table: tipo_tutor                                            */
/*==============================================================*/
create table tipo_tutor (
                            id_tipo_tutor        serial               not null,
                            nombre_tipo_tutor    varchar(100)         not null,
                            constraint pk_tipo_tutor primary key (id_tipo_tutor)
);

/*==============================================================*/
/* Index: tipo_tutor_pk                                         */
/*==============================================================*/
create unique index tipo_tutor_pk on tipo_tutor (
                                                 id_tipo_tutor
    );

/*==============================================================*/
/* Table: tutor                                                 */
/*==============================================================*/
create table tutor (
                       id_tutor             serial               not null,
                       id_tipo_tutor        int4                 null,
                       email_tutor          varchar(100)         not null,
                       nombres_tutor        varchar(100)         not null,
                       apellidos_tutor      varchar(100)         not null,
                       telefono             int4                 not null,
                       constraint pk_tutor primary key (id_tutor)
);

/*==============================================================*/
/* Index: tutor_pk                                              */
/*==============================================================*/
create unique index tutor_pk on tutor (
                                       id_tutor
    );

/*==============================================================*/
/* Index: r5_fk                                                 */
/*==============================================================*/
create  index r5_fk on tutor (
                              id_tipo_tutor
    );

/*==============================================================*/
/* Table: tutor_area                                            */
/*==============================================================*/
create table tutor_area (
                            id_area              int4                 not null,
                            id_tutor             int4                 not null,
                            id_tutor_area        serial               not null,
                            constraint pk_tutor_area primary key (id_area, id_tutor, id_tutor_area)
);

/*==============================================================*/
/* Index: tutor_area_pk                                         */
/*==============================================================*/
create unique index tutor_area_pk on tutor_area (
                                                 id_area,
                                                 id_tutor,
                                                 id_tutor_area
    );

/*==============================================================*/
/* Index: r39_fk                                                */
/*==============================================================*/
create  index r39_fk on tutor_area (
                                    id_tutor
    );

/*==============================================================*/
/* Index: r8_fk                                                 */
/*==============================================================*/
create  index r8_fk on tutor_area (
                                   id_area
    );

/*==============================================================*/
/* Table: usuario                                               */
/*==============================================================*/
create table usuario (
                         id_usuario           serial               not null,
                         id_general           varchar(50)          null,
                         nombre_usuario       varchar(50)          not null,
                         apellido_paterno_usuario varchar(50)          not null,
                         apellido_materno_usuario varchar(50)          not null,
                         correo_usuario       varchar(50)          not null,
                         fecha_nacimiento     date                 not null,
                         password             varchar(1024)        not null,
                         constraint pk_usuario primary key (id_usuario)
);

/*==============================================================*/
/* Index: usuario_pk                                            */
/*==============================================================*/
create unique index usuario_pk on usuario (
                                           id_usuario
    );

/*==============================================================*/
/* Table: usuario_rol                                           */
/*==============================================================*/
create table usuario_rol (
                             id_rol               int4                 not null,
                             id_usuario           int4                 not null,
                             id_usuario_rol       serial               not null,
                             fecha_designacion_rol date                 not null,
                             usuario_rol_activo   bool                 not null,
                             constraint pk_usuario_rol primary key (id_rol, id_usuario, id_usuario_rol)
);

/*==============================================================*/
/* Index: usuario_rol_pk                                        */
/*==============================================================*/
create unique index usuario_rol_pk on usuario_rol (
                                                   id_rol,
                                                   id_usuario,
                                                   id_usuario_rol
    );

/*==============================================================*/
/* Index: r41_fk                                                */
/*==============================================================*/
create  index r41_fk on usuario_rol (
                                     id_usuario
    );

/*==============================================================*/
/* Index: r49_fk                                                */
/*==============================================================*/
create  index r49_fk on usuario_rol (
                                     id_rol
    );

alter table area_nivel_escolar
    add constraint fk_area_niv_r33_nivel_es foreign key (id_nivel)
        references nivel_escolar (id_nivel)
        on delete restrict on update restrict;

alter table area_nivel_escolar
    add constraint fk_area_niv_r9_area foreign key (id_area)
        references area (id_area)
        on delete restrict on update restrict;

alter table categorias
    add constraint fk_categori_r35_area foreign key (id_area)
        references area (id_area)
        on delete restrict on update restrict;

alter table colegio
    add constraint fk_colegio_r3_municipi foreign key (id_departamento, id_municipio)
        references municipio (id_departamento, id_municipio)
        on delete restrict on update restrict;

alter table colegio
    add constraint fk_colegio_r6_tipo_col foreign key (id_tipo_colegio)
        references tipo_colegio (id_tipo_colegio)
        on delete restrict on update restrict;

alter table comprobante_pago
    add constraint fk_comproba_r30_orden_de foreign key (id_inscripcion, id_orden_pago)
        references orden_de_pago (id_inscripcion, id_orden_pago)
        on delete restrict on update restrict;

alter table inscripcion_area
    add constraint fk_inscripc_r21_inscripc foreign key (id_inscripcion)
        references inscripcion (id_inscripcion)
        on delete restrict on update restrict;

alter table inscripcion_area
    add constraint fk_inscripc_r22_area foreign key (id_area)
        references area (id_area)
        on delete restrict on update restrict;

alter table log
    add constraint fk_log_r42_evento foreign key (id_evento)
        references evento (id_evento)
        on delete restrict on update restrict;

alter table municipio
    add constraint fk_municipi_r1_departam foreign key (id_departamento)
        references departamento (id_departamento)
        on delete restrict on update restrict;

alter table nivel_escolar_categorias
    add constraint fk_nivel_es_r37_categori foreign key (id_area, id_categoria)
        references categorias (id_area, id_categoria)
        on delete restrict on update restrict;

alter table nivel_escolar_categorias
    add constraint fk_nivel_es_r38_nivel_es foreign key (id_nivel)
        references nivel_escolar (id_nivel)
        on delete restrict on update restrict;

alter table orden_de_pago
    add constraint fk_orden_de_r32_metodo_p foreign key (id_metodo_pago)
        references metodo_pago (id_metodo_pago)
        on delete restrict on update restrict;

alter table orden_de_pago
    add constraint fk_orden_de_r34_estado_o foreign key (id_estado)
        references estado_orden_de_pago (id_estado)
        on delete restrict on update restrict;

alter table orden_de_pago
    add constraint fk_orden_de_relations_inscripc foreign key (id_inscripcion)
        references inscripcion (id_inscripcion)
        on delete restrict on update restrict;

alter table participante
    add constraint fk_particip_r24_inscripc foreign key (id_inscripcion)
        references inscripcion (id_inscripcion)
        on delete restrict on update restrict;

alter table participante
    add constraint fk_particip_r7_colegio foreign key (id_departamento, id_municipio, id_colegio)
        references colegio (id_departamento, id_municipio, id_colegio)
        on delete restrict on update restrict;

alter table participante_tutor
    add constraint fk_particip_r25_particip foreign key (id_inscripcion, id_participante)
        references participante (id_inscripcion, id_participante)
        on delete restrict on update restrict;

alter table participante_tutor
    add constraint fk_particip_r4_tutor foreign key (id_tutor)
        references tutor (id_tutor)
        on delete restrict on update restrict;

alter table rol_permiso
    add constraint fk_rol_perm_r45_permiso foreign key (id_permiso)
        references permiso (id_permiso)
        on delete restrict on update restrict;

alter table rol_permiso
    add constraint fk_rol_perm_r46_rol foreign key (id_rol)
        references rol (id_rol)
        on delete restrict on update restrict;

alter table sesion
    add constraint fk_sesion_r43_usuario foreign key (id_usuario)
        references usuario (id_usuario)
        on delete restrict on update restrict;

alter table tutor
    add constraint fk_tutor_r5_tipo_tut foreign key (id_tipo_tutor)
        references tipo_tutor (id_tipo_tutor)
        on delete restrict on update restrict;

alter table tutor_area
    add constraint fk_tutor_ar_r39_tutor foreign key (id_tutor)
        references tutor (id_tutor)
        on delete restrict on update restrict;

alter table tutor_area
    add constraint fk_tutor_ar_r8_area foreign key (id_area)
        references area (id_area)
        on delete restrict on update restrict;

alter table usuario_rol
    add constraint fk_usuario__r41_usuario foreign key (id_usuario)
        references usuario (id_usuario)
        on delete restrict on update restrict;

alter table usuario_rol
    add constraint fk_usuario__r49_rol foreign key (id_rol)
        references rol (id_rol)
        on delete restrict on update restrict;
