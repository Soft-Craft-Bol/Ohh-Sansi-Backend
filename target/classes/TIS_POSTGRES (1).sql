create table AREA (
   ID_AREA              SERIAL               not null,
   NOMBRE_AREA          VARCHAR(300)         not null,
   constraint PK_AREA primary key (ID_AREA)
);

create unique index AREA_PK on AREA (
ID_AREA
);

create table AREA_INSCRIPCION (
   ID_AREA              INT4                 not null,
   ID_PARTICIPANTE      INT4                 not null,
   ID_INSCRIPCION       INT4                 not null,
   MONTO_POR_AREA       DECIMAL              not null,
   constraint PK_AREA_INSCRIPCION primary key (ID_AREA, ID_PARTICIPANTE, ID_INSCRIPCION)
);

create unique index AREA_INSCRIPCION_PK on AREA_INSCRIPCION (
ID_AREA,
ID_PARTICIPANTE,
ID_INSCRIPCION
);

create  index RELATIONSHIP_25_FK on AREA_INSCRIPCION (
ID_PARTICIPANTE,
ID_INSCRIPCION
);

create  index RELATIONSHIP_26_FK on AREA_INSCRIPCION (
ID_AREA
);

/*==============================================================*/
/* Table: CATEGORIAS                                            */
/*==============================================================*/
create table CATEGORIAS (
   ID_AREA              INT4                 not null,
   ID_CATEGORIA         SERIAL               not null,
   ID_TIPO_MAT_CAT      INT4                 null,
   COD_CATEGORIA        VARCHAR(10)          not null,
   constraint PK_CATEGORIAS primary key (ID_AREA, ID_CATEGORIA)
);

/*==============================================================*/
/* Index: CATEGORIAS_PK                                         */
/*==============================================================*/
create unique index CATEGORIAS_PK on CATEGORIAS (
ID_AREA,
ID_CATEGORIA
);

/*==============================================================*/
/* Index: RELATIONSHIP_35_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_35_FK on CATEGORIAS (
ID_AREA
);

/*==============================================================*/
/* Index: RELATIONSHIP_36_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_36_FK on CATEGORIAS (
ID_TIPO_MAT_CAT
);

/*==============================================================*/
/* Table: COLEGIO                                               */
/*==============================================================*/
create table COLEGIO (
   ID_COLEGIO           INT4                 not null,
   ID_TIPO_COLEGIO      CHAR(10)             null,
   ID_MUNICIPIO         INT4                 null,
   NOMBRE_COLEGIO       CHAR(10)             not null,
   LOGO                 VARCHAR(50)          not null,
   CODIGO_SIS           INT4                 not null,
   constraint PK_COLEGIO primary key (ID_COLEGIO)
);

/*==============================================================*/
/* Index: COLEGIO_PK                                            */
/*==============================================================*/
create unique index COLEGIO_PK on COLEGIO (
ID_COLEGIO
);

/*==============================================================*/
/* Index: RELATIONSHIP_9_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_9_FK on COLEGIO (
ID_TIPO_COLEGIO
);

/*==============================================================*/
/* Index: RELATIONSHIP_12_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_12_FK on COLEGIO (
ID_MUNICIPIO
);

/*==============================================================*/
/* Table: COMPROBANTE_PAGO                                      */
/*==============================================================*/
create table COMPROBANTE_PAGO (
   ID_ORDEN_PAGO        INT4                 not null,
   MONTO_PAGADO         DECIMAL              not null,
   FECHA_PAGO           DATE                 not null,
   COD_TRANSACCION      VARCHAR(400)         not null,
   IMAGEN_COMPROBANTE   VARCHAR(1024)        not null,
   constraint PK_COMPROBANTE_PAGO primary key (ID_ORDEN_PAGO)
);

/*==============================================================*/
/* Index: COMPROBANTE_PAGO_PK                                   */
/*==============================================================*/
create unique index COMPROBANTE_PAGO_PK on COMPROBANTE_PAGO (
ID_ORDEN_PAGO
);

/*==============================================================*/
/* Table: DEPARTAMENTO                                          */
/*==============================================================*/
create table DEPARTAMENTO (
   ID_DEPARTAMENTO      SERIAL               not null,
   NOMBRE_DEPARTAMENTO  VARCHAR(100)         not null,
   constraint PK_DEPARTAMENTO primary key (ID_DEPARTAMENTO)
);

/*==============================================================*/
/* Index: DEPARTAMENTO_PK                                       */
/*==============================================================*/
create unique index DEPARTAMENTO_PK on DEPARTAMENTO (
ID_DEPARTAMENTO
);

/*==============================================================*/
/* Table: DEPARTAMENTO_MUNICIPIO                                */
/*==============================================================*/
create table DEPARTAMENTO_MUNICIPIO (
   ID_MUNICIPIO         INT4                 not null,
   ID_DEPARTAMENTO      INT4                 not null,
   constraint PK_DEPARTAMENTO_MUNICIPIO primary key (ID_MUNICIPIO, ID_DEPARTAMENTO)
);

/*==============================================================*/
/* Index: DEPARTAMENTO_MUNICIPIO_PK                             */
/*==============================================================*/
create unique index DEPARTAMENTO_MUNICIPIO_PK on DEPARTAMENTO_MUNICIPIO (
ID_MUNICIPIO,
ID_DEPARTAMENTO
);

/*==============================================================*/
/* Index: RELATIONSHIP_38_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_38_FK on DEPARTAMENTO_MUNICIPIO (
ID_DEPARTAMENTO
);

/*==============================================================*/
/* Index: RELATIONSHIP_39_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_39_FK on DEPARTAMENTO_MUNICIPIO (
ID_MUNICIPIO
);

/*==============================================================*/
/* Table: ENTITY_30                                             */
/*==============================================================*/
create table ENTITY_30 (
   ID_AREA              INT4                 not null,
   ID_CATEGORIA         INT4                 not null,
   ID_GRADO             INT4                 not null,
   constraint PK_ENTITY_30 primary key (ID_AREA, ID_CATEGORIA, ID_GRADO)
);

/*==============================================================*/
/* Index: ENTITY_30_PK                                          */
/*==============================================================*/
create unique index ENTITY_30_PK on ENTITY_30 (
ID_AREA,
ID_CATEGORIA,
ID_GRADO
);

/*==============================================================*/
/* Index: RELATIONSHIP_33_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_33_FK on ENTITY_30 (
ID_AREA,
ID_CATEGORIA
);

/*==============================================================*/
/* Index: RELATIONSHIP_41_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_41_FK on ENTITY_30 (
ID_GRADO
);

/*==============================================================*/
/* Table: ESTADO                                                */
/*==============================================================*/
create table ESTADO (
   ID_ESTADO            SERIAL               not null,
   ESTADO               VARCHAR(100)         not null,
   constraint PK_ESTADO primary key (ID_ESTADO)
);

/*==============================================================*/
/* Index: ESTADO_PK                                             */
/*==============================================================*/
create unique index ESTADO_PK on ESTADO (
ID_ESTADO
);

/*==============================================================*/
/* Table: EVENTO                                                */
/*==============================================================*/
create table EVENTO (
   ID_EVENTO            SERIAL               not null,
   EVENTO               VARCHAR(300)         not null,
   constraint PK_EVENTO primary key (ID_EVENTO)
);

/*==============================================================*/
/* Index: EVENTO_PK                                             */
/*==============================================================*/
create unique index EVENTO_PK on EVENTO (
ID_EVENTO
);

/*==============================================================*/
/* Table: GRADO                                                 */
/*==============================================================*/
create table GRADO (
   ID_GRADO             INT4                 not null,
   NOMBRE_GRADO         VARCHAR(50)          not null,
   constraint PK_GRADO primary key (ID_GRADO)
);

/*==============================================================*/
/* Index: GRADO_PK                                              */
/*==============================================================*/
create unique index GRADO_PK on GRADO (
ID_GRADO
);

/*==============================================================*/
/* Table: INSCRIPCION                                           */
/*==============================================================*/
create table INSCRIPCION (
   ID_PARTICIPANTE      INT4                 not null,
   ID_INSCRIPCION       SERIAL               not null,
   FECHA_INCRIPCION     DATE                 not null,
   constraint PK_INSCRIPCION primary key (ID_PARTICIPANTE, ID_INSCRIPCION)
);

/*==============================================================*/
/* Index: INSCRIPCION_PK                                        */
/*==============================================================*/
create unique index INSCRIPCION_PK on INSCRIPCION (
ID_PARTICIPANTE,
ID_INSCRIPCION
);

/*==============================================================*/
/* Index: RELATIONSHIP_24_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_24_FK on INSCRIPCION (
ID_PARTICIPANTE
);

/*==============================================================*/
/* Table: INSCRIPCION_ORDEN_PAGO                                */
/*==============================================================*/
create table INSCRIPCION_ORDEN_PAGO (
   ID_PARTICIPANTE      INT4                 not null,
   ID_INSCRIPCION       INT4                 not null,
   ID_ORDEN_PAGO        INT4                 not null,
   COSTO_INSCRIPCION_ESTUDIANTE DECIMAL              not null,
   constraint PK_INSCRIPCION_ORDEN_PAGO primary key (ID_PARTICIPANTE, ID_INSCRIPCION, ID_ORDEN_PAGO)
);

/*==============================================================*/
/* Index: INSCRIPCION_ORDEN_PAGO_PK                             */
/*==============================================================*/
create unique index INSCRIPCION_ORDEN_PAGO_PK on INSCRIPCION_ORDEN_PAGO (
ID_PARTICIPANTE,
ID_INSCRIPCION,
ID_ORDEN_PAGO
);

/*==============================================================*/
/* Index: RELATIONSHIP_28_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_28_FK on INSCRIPCION_ORDEN_PAGO (
ID_PARTICIPANTE,
ID_INSCRIPCION
);

/*==============================================================*/
/* Index: RELATIONSHIP_40_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_40_FK on INSCRIPCION_ORDEN_PAGO (
ID_ORDEN_PAGO
);

/*==============================================================*/
/* Table: LOG                                                   */
/*==============================================================*/
create table LOG (
   ID_LOG               SERIAL               not null,
   ID_EVENTO            INT4                 null,
   FECHA_EVENTO         DATE                 not null,
   HORA_EVENTO          TIME                 not null,
   DATO_VIEJO           VARCHAR(1024)        null,
   DATO_NUEVO           VARCHAR(1024)        null,
   CONSULTA             VARCHAR(1024)        null,
   TABLA                VARCHAR(200)         null,
   PID_USER             INT4                 not null,
   constraint PK_LOG primary key (ID_LOG)
);

/*==============================================================*/
/* Index: LOG_PK                                                */
/*==============================================================*/
create unique index LOG_PK on LOG (
ID_LOG
);

/*==============================================================*/
/* Index: RELATIONSHIP_42_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_42_FK on LOG (
ID_EVENTO
);

/*==============================================================*/
/* Table: METODO_PAGO                                           */
/*==============================================================*/
create table METODO_PAGO (
   ID_METODO_PAGO       SERIAL               not null,
   METODO_PAGO          VARCHAR(100)         not null,
   constraint PK_METODO_PAGO primary key (ID_METODO_PAGO)
);

/*==============================================================*/
/* Index: METODO_PAGO_PK                                        */
/*==============================================================*/
create unique index METODO_PAGO_PK on METODO_PAGO (
ID_METODO_PAGO
);

/*==============================================================*/
/* Table: MUNICIPIO                                             */
/*==============================================================*/
create table MUNICIPIO (
   ID_MUNICIPIO         INT4                 not null,
   NOMBRE_MUNICIPIO     VARCHAR(100)         not null,
   DISTANCIA            VARCHAR(50)          null,
   constraint PK_MUNICIPIO primary key (ID_MUNICIPIO)
);

/*==============================================================*/
/* Index: MUNICIPIO_PK                                          */
/*==============================================================*/
create unique index MUNICIPIO_PK on MUNICIPIO (
ID_MUNICIPIO
);

/*==============================================================*/
/* Table: NIVEL                                                 */
/*==============================================================*/
create table NIVEL (
   ID_NIVEL             SERIAL               not null,
   ID_AREA              INT4                 not null,
   ID_GRADO             INT4                 null,
   COD_NIVEL            VARCHAR(10)          not null,
   constraint PK_NIVEL primary key (ID_AREA, ID_NIVEL)
);

/*==============================================================*/
/* Index: NIVEL_PK                                              */
/*==============================================================*/
create unique index NIVEL_PK on NIVEL (
ID_AREA,
ID_NIVEL
);

/*==============================================================*/
/* Index: RELATIONSHIP_27_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_27_FK on NIVEL (
ID_AREA
);

/*==============================================================*/
/* Index: RELATIONSHIP_37_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_37_FK on NIVEL (
ID_GRADO
);

/*==============================================================*/
/* Table: ORDEN_DE_PAGO                                         */
/*==============================================================*/
create table ORDEN_DE_PAGO (
   ID_ORDEN_PAGO        SERIAL               not null,
   ID_METODO_PAGO       INT4                 null,
   ID_ESTADO            INT4                 null,
   FECHA_EMISION_ORDEN_PAGO DATE                 not null,
   FECHA_VENCIMIENTO    DATE                 not null,
   MONTO_TOTAL_PAGO     DECIMAL              not null,
   constraint PK_ORDEN_DE_PAGO primary key (ID_ORDEN_PAGO)
);

/*==============================================================*/
/* Index: ORDEN_DE_PAGO_PK                                      */
/*==============================================================*/
create unique index ORDEN_DE_PAGO_PK on ORDEN_DE_PAGO (
ID_ORDEN_PAGO
);

/*==============================================================*/
/* Index: RELATIONSHIP_32_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_32_FK on ORDEN_DE_PAGO (
ID_METODO_PAGO
);

/*==============================================================*/
/* Index: RELATIONSHIP_34_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_34_FK on ORDEN_DE_PAGO (
ID_ESTADO
);

/*==============================================================*/
/* Table: PARTICIPANTE                                          */
/*==============================================================*/
create table PARTICIPANTE (
   ID_PARTICIPANTE      SERIAL               not null,
   ID_COLEGIO           INT4                 null,
   ID_GENERAL           VARCHAR(50)          null,
   APELLIDO_PATERNO     VARCHAR(50)          not null,
   APELLIDO_MATERNO     VARCHAR(50)          not null,
   NOMBRE_PARTICIPANTE  VARCHAR(50)          not null,
   FECHA_NACIMIENTO     DATE                 not null,
   constraint PK_PARTICIPANTE primary key (ID_PARTICIPANTE)
);

/*==============================================================*/
/* Index: PARTICIPANTE_PK                                       */
/*==============================================================*/
create unique index PARTICIPANTE_PK on PARTICIPANTE (
ID_PARTICIPANTE
);

/*==============================================================*/
/* Index: RELATIONSHIP_21_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_21_FK on PARTICIPANTE (
ID_COLEGIO
);

/*==============================================================*/
/* Table: PARTICIPANTE_TUTOR                                    */
/*==============================================================*/
create table PARTICIPANTE_TUTOR (
   ID_PARTICIPANTE      INT4                 not null,
   ID_TUTOR             CHAR(10)             not null,
   constraint PK_PARTICIPANTE_TUTOR primary key (ID_PARTICIPANTE, ID_TUTOR)
);

/*==============================================================*/
/* Index: PARTICIPANTE_TUTOR_PK                                 */
/*==============================================================*/
create unique index PARTICIPANTE_TUTOR_PK on PARTICIPANTE_TUTOR (
ID_PARTICIPANTE,
ID_TUTOR
);

/*==============================================================*/
/* Index: RELATIONSHIP_30_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_30_FK on PARTICIPANTE_TUTOR (
ID_PARTICIPANTE
);

/*==============================================================*/
/* Index: RELATIONSHIP_31_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_31_FK on PARTICIPANTE_TUTOR (
ID_TUTOR
);

/*==============================================================*/
/* Table: PERMISOS                                              */
/*==============================================================*/
create table PERMISOS (
   ID_PERMISSOS         INT4                 not null,
   NOM_PERMISOS         VARCHAR(50)          not null,
   constraint PK_PERMISOS primary key (ID_PERMISSOS)
);

/*==============================================================*/
/* Index: PERMISOS_PK                                           */
/*==============================================================*/
create unique index PERMISOS_PK on PERMISOS (
ID_PERMISSOS
);

/*==============================================================*/
/* Table: ROLES                                                 */
/*==============================================================*/
create table ROLES (
   ID_ROL               INT4                 not null,
   NOMBRE_ROL           VARCHAR(50)          not null,
   constraint PK_ROLES primary key (ID_ROL)
);

/*==============================================================*/
/* Index: ROLES_PK                                              */
/*==============================================================*/
create unique index ROLES_PK on ROLES (
ID_ROL
);

/*==============================================================*/
/* Table: ROLES_PERMISOS                                        */
/*==============================================================*/
create table ROLES_PERMISOS (
   ID_ROL               INT4                 not null,
   ID_PERMISSOS         INT4                 not null,
   constraint PK_ROLES_PERMISOS primary key (ID_ROL, ID_PERMISSOS)
);

/*==============================================================*/
/* Index: ROLES_PERMISOS_PK                                     */
/*==============================================================*/
create unique index ROLES_PERMISOS_PK on ROLES_PERMISOS (
ID_ROL,
ID_PERMISSOS
);

/*==============================================================*/
/* Index: RELATIONSHIP_22_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_22_FK on ROLES_PERMISOS (
ID_ROL
);

/*==============================================================*/
/* Index: RELATIONSHIP_23_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_23_FK on ROLES_PERMISOS (
ID_PERMISSOS
);

/*==============================================================*/
/* Table: SESIONES                                              */
/*==============================================================*/
create table SESIONES (
   ID_USUARIO           INT4                 not null,
   ID_SESION            INT4                 not null,
   FECHA_SESION         DATE                 not null,
   NOMBRE_USUARIO_SESION VARCHAR(50)          not null,
   TIEMPO_SESION        DATE                 not null,
   DIRECCION_IP         VARCHAR(50)          not null,
   TIEMPOS_SALIDA       DATE                 not null,
   constraint PK_SESIONES primary key (ID_USUARIO, ID_SESION)
);

/*==============================================================*/
/* Index: SESIONES_PK                                           */
/*==============================================================*/
create unique index SESIONES_PK on SESIONES (
ID_USUARIO,
ID_SESION
);

/*==============================================================*/
/* Index: RELATIONSHIP_17_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_17_FK on SESIONES (
ID_USUARIO
);

/*==============================================================*/
/* Table: TIPO_COLEGIO                                          */
/*==============================================================*/
create table TIPO_COLEGIO (
   ID_TIPO_COLEGIO      CHAR(10)             not null,
   NOMBRE               CHAR(10)             not null,
   constraint PK_TIPO_COLEGIO primary key (ID_TIPO_COLEGIO)
);

/*==============================================================*/
/* Index: TIPO_COLEGIO_PK                                       */
/*==============================================================*/
create unique index TIPO_COLEGIO_PK on TIPO_COLEGIO (
ID_TIPO_COLEGIO
);

/*==============================================================*/
/* Table: TIPO_MATERIA_CATEGORIA                                */
/*==============================================================*/
create table TIPO_MATERIA_CATEGORIA (
   ID_TIPO_MAT_CAT      SERIAL               not null,
   NOMBRE_MATERIA       VARCHAR(200)         not null,
   constraint PK_TIPO_MATERIA_CATEGORIA primary key (ID_TIPO_MAT_CAT)
);

/*==============================================================*/
/* Index: TIPO_MATERIA_CATEGORIA_PK                             */
/*==============================================================*/
create unique index TIPO_MATERIA_CATEGORIA_PK on TIPO_MATERIA_CATEGORIA (
ID_TIPO_MAT_CAT
);

/*==============================================================*/
/* Table: TIPO_TUTOR                                            */
/*==============================================================*/
create table TIPO_TUTOR (
   ID_TIPO_TUTOR        SERIAL               not null,
   NOMBRE_TIPO_TUTOR    VARCHAR(100)         not null,
   constraint PK_TIPO_TUTOR primary key (ID_TIPO_TUTOR)
);

/*==============================================================*/
/* Index: TIPO_TUTOR_PK                                         */
/*==============================================================*/
create unique index TIPO_TUTOR_PK on TIPO_TUTOR (
ID_TIPO_TUTOR
);

/*==============================================================*/
/* Table: TUTOR                                                 */
/*==============================================================*/
create table TUTOR (
   TELEFONO             INT4                 not null,
   ID_TUTOR             CHAR(10)             not null,
   ID_TIPO_TUTOR        INT4                 null,
   constraint PK_TUTOR primary key (ID_TUTOR)
);

/*==============================================================*/
/* Index: TUTOR_PK                                              */
/*==============================================================*/
create unique index TUTOR_PK on TUTOR (
ID_TUTOR
);

/*==============================================================*/
/* Index: RELATIONSHIP_20_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_20_FK on TUTOR (
ID_TIPO_TUTOR
);

/*==============================================================*/
/* Table: USER_ROL                                              */
/*==============================================================*/
create table USER_ROL (
   ID_ROL               INT4                 not null,
   ID_USUARIO           INT4                 not null,
   constraint PK_USER_ROL primary key (ID_ROL, ID_USUARIO)
);

/*==============================================================*/
/* Index: USER_ROL_PK                                           */
/*==============================================================*/
create unique index USER_ROL_PK on USER_ROL (
ID_ROL,
ID_USUARIO
);

/*==============================================================*/
/* Index: RELATIONSHIP_18_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_18_FK on USER_ROL (
ID_USUARIO
);

/*==============================================================*/
/* Index: RELATIONSHIP_19_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_19_FK on USER_ROL (
ID_ROL
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   ID_USUARIO           INT4                 not null,
   ID_GENERAL           VARCHAR(50)          null,
   NOMBRE_USUARIO       VARCHAR(50)          not null,
   APELLIDO_PATERNO_USUARIO VARCHAR(50)          not null,
   APELLIDO_MATERNO_USUARIO VARCHAR(50)          not null,
   CORREO_USUARIO       VARCHAR(50)          not null,
   CONTRASENA_USUARIO   VARCHAR(50)          not null,
   FECHA_NACIMIENTO     DATE                 not null,
   PASSWORD 		VARCHAR(1024)        not null,
   constraint PK_USUARIO primary key (ID_USUARIO)
);

/*==============================================================*/
/* Index: USUARIO_PK                                            */
/*==============================================================*/
create unique index USUARIO_PK on USUARIO (
ID_USUARIO
);

alter table AREA_INSCRIPCION
   add constraint FK_AREA_INS_RELATIONS_INSCRIPC foreign key (ID_PARTICIPANTE, ID_INSCRIPCION)
      references INSCRIPCION (ID_PARTICIPANTE, ID_INSCRIPCION)
      on delete restrict on update restrict;

alter table AREA_INSCRIPCION
   add constraint FK_AREA_INS_RELATIONS_AREA foreign key (ID_AREA)
      references AREA (ID_AREA)
      on delete restrict on update restrict;

alter table CATEGORIAS
   add constraint FK_CATEGORI_RELATIONS_AREA foreign key (ID_AREA)
      references AREA (ID_AREA)
      on delete restrict on update restrict;

alter table CATEGORIAS
   add constraint FK_CATEGORI_RELATIONS_TIPO_MAT foreign key (ID_TIPO_MAT_CAT)
      references TIPO_MATERIA_CATEGORIA (ID_TIPO_MAT_CAT)
      on delete restrict on update restrict;

alter table COLEGIO
   add constraint FK_COLEGIO_RELATIONS_MUNICIPI foreign key (ID_MUNICIPIO)
      references MUNICIPIO (ID_MUNICIPIO)
      on delete restrict on update restrict;

alter table COLEGIO
   add constraint FK_COLEGIO_RELATIONS_TIPO_COL foreign key (ID_TIPO_COLEGIO)
      references TIPO_COLEGIO (ID_TIPO_COLEGIO)
      on delete restrict on update restrict;

alter table COMPROBANTE_PAGO
   add constraint FK_COMPROBA_RELATIONS_ORDEN_DE foreign key (ID_ORDEN_PAGO)
      references ORDEN_DE_PAGO (ID_ORDEN_PAGO)
      on delete restrict on update restrict;

alter table DEPARTAMENTO_MUNICIPIO
   add constraint FK_DEPARTAM_RELATIONS_DEPARTAM foreign key (ID_DEPARTAMENTO)
      references DEPARTAMENTO (ID_DEPARTAMENTO)
      on delete restrict on update restrict;

alter table DEPARTAMENTO_MUNICIPIO
   add constraint FK_DEPARTAM_RELATIONS_MUNICIPI foreign key (ID_MUNICIPIO)
      references MUNICIPIO (ID_MUNICIPIO)
      on delete restrict on update restrict;

alter table ENTITY_30
   add constraint FK_ENTITY_3_RELATIONS_CATEGORI foreign key (ID_AREA, ID_CATEGORIA)
      references CATEGORIAS (ID_AREA, ID_CATEGORIA)
      on delete restrict on update restrict;

alter table ENTITY_30
   add constraint FK_ENTITY_3_RELATIONS_GRADO foreign key (ID_GRADO)
      references GRADO (ID_GRADO)
      on delete restrict on update restrict;

alter table INSCRIPCION
   add constraint FK_INSCRIPC_RELATIONS_PARTICIP foreign key (ID_PARTICIPANTE)
      references PARTICIPANTE (ID_PARTICIPANTE)
      on delete restrict on update restrict;

alter table INSCRIPCION_ORDEN_PAGO
   add constraint FK_INSCRIPC_RELATIONS_INSCRIPC foreign key (ID_PARTICIPANTE, ID_INSCRIPCION)
      references INSCRIPCION (ID_PARTICIPANTE, ID_INSCRIPCION)
      on delete restrict on update restrict;

alter table INSCRIPCION_ORDEN_PAGO
   add constraint FK_INSCRIPC_RELATIONS_ORDEN_DE foreign key (ID_ORDEN_PAGO)
      references ORDEN_DE_PAGO (ID_ORDEN_PAGO)
      on delete restrict on update restrict;

alter table LOG
   add constraint FK_LOG_RELATIONS_EVENTO foreign key (ID_EVENTO)
      references EVENTO (ID_EVENTO)
      on delete restrict on update restrict;

alter table NIVEL
   add constraint FK_NIVEL_RELATIONS_AREA foreign key (ID_AREA)
      references AREA (ID_AREA)
      on delete restrict on update restrict;

alter table NIVEL
   add constraint FK_NIVEL_RELATIONS_GRADO foreign key (ID_GRADO)
      references GRADO (ID_GRADO)
      on delete restrict on update restrict;

alter table ORDEN_DE_PAGO
   add constraint FK_ORDEN_DE_RELATIONS_METODO_P foreign key (ID_METODO_PAGO)
      references METODO_PAGO (ID_METODO_PAGO)
      on delete restrict on update restrict;

alter table ORDEN_DE_PAGO
   add constraint FK_ORDEN_DE_RELATIONS_ESTADO foreign key (ID_ESTADO)
      references ESTADO (ID_ESTADO)
      on delete restrict on update restrict;

alter table PARTICIPANTE
   add constraint FK_PARTICIP_RELATIONS_COLEGIO foreign key (ID_COLEGIO)
      references COLEGIO (ID_COLEGIO)
      on delete restrict on update restrict;

alter table PARTICIPANTE_TUTOR
   add constraint FK_PARTICIP_RELATIONS_PARTICIP foreign key (ID_PARTICIPANTE)
      references PARTICIPANTE (ID_PARTICIPANTE)
      on delete restrict on update restrict;

alter table PARTICIPANTE_TUTOR
   add constraint FK_PARTICIP_RELATIONS_TUTOR foreign key (ID_TUTOR)
      references TUTOR (ID_TUTOR)
      on delete restrict on update restrict;

alter table ROLES_PERMISOS
   add constraint FK_ROLES_PE_RELATIONS_ROLES foreign key (ID_ROL)
      references ROLES (ID_ROL)
      on delete restrict on update restrict;

alter table ROLES_PERMISOS
   add constraint FK_ROLES_PE_RELATIONS_PERMISOS foreign key (ID_PERMISSOS)
      references PERMISOS (ID_PERMISSOS)
      on delete restrict on update restrict;

alter table SESIONES
   add constraint FK_SESIONES_RELATIONS_USUARIO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO)
      on delete restrict on update restrict;

alter table TUTOR
   add constraint FK_TUTOR_RELATIONS_TIPO_TUT foreign key (ID_TIPO_TUTOR)
      references TIPO_TUTOR (ID_TIPO_TUTOR)
      on delete restrict on update restrict;

alter table USER_ROL
   add constraint FK_USER_ROL_RELATIONS_USUARIO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO)
      on delete restrict on update restrict;

alter table USER_ROL
   add constraint FK_USER_ROL_RELATIONS_ROLES foreign key (ID_ROL)
      references ROLES (ID_ROL)
      on delete restrict on update restrict;

