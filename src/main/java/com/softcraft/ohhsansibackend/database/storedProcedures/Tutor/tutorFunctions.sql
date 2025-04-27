--Para ver los tutores legales--
CREATE OR REPLACE FUNCTION get_tutor_legal_por_participante_ci(p_ci INTEGER)
RETURNS TABLE (
    correo_tut VARCHAR,
    nombre_tut VARCHAR,
    apellido_tut VARCHAR,
    telf INTEGER,
    ci_tut BIGINT,
    complemento VARCHAR
)
AS $$
BEGIN
    RETURN QUERY
    SELECT T.email_tutor, nombres_tutor, apellidos_tutor, telefono, carnet_identidad_tutor, complemento_ci_tutor
	FROM	(SELECT id_tutor, email_tutor, nombres_tutor, apellidos_tutor, telefono, carnet_identidad_tutor, complemento_ci_tutor
    		  FROM public.tutor WHERE id_tipo_tutor = 2) T, public.participante_tutor Pa,
			  (SELECT id_participante, carnet_identidad_participante
   			 FROM public.participante WHERE carnet_identidad_participante = p_ci) P
    WHERE T.id_tutor = Pa.id_tutor and Pa.id_participante = P.id_participante;
END;
$$ LANGUAGE plpgsql;
