CREATE OR REPLACE FUNCTION SelectUsuarioByCorreo(correoUsuario VARCHAR)
    RETURNS TABLE (
                      ID_USUARIO INT4,
                      ID_GENERAL VARCHAR(50),
                      NOMBRE_USUARIO VARCHAR(50),
                      APELLIDO_PATERNO_USUARIO VARCHAR(50),
                      APELLIDO_MATERNO_USUARIO VARCHAR(50),
                      CORREO_USUARIO VARCHAR(50),
                      CONTRASENA_USUARIO VARCHAR(50),
                      FECHA_NACIMIENTO DATE,
                      PASSWORD VARCHAR(1024)
                  ) AS $$
BEGIN
    RETURN QUERY SELECT
                     USUARIO.ID_USUARIO,
                     USUARIO.ID_GENERAL,
                     USUARIO.NOMBRE_USUARIO,
                     USUARIO.APELLIDO_PATERNO_USUARIO,
                     USUARIO.APELLIDO_MATERNO_USUARIO,
                     USUARIO.CORREO_USUARIO,
                     USUARIO.CONTRASENA_USUARIO,
                     USUARIO.FECHA_NACIMIENTO,
                     USUARIO.PASSWORD
                 FROM USUARIO
                 WHERE USUARIO.CORREO_USUARIO = correoUsuario;
END;
$$ LANGUAGE plpgsql;
SELECT * FROM SelectUsuarioByCorreo('123456alfredo@gmail.com');