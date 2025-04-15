ALTER TABLE area
    add constraint nombre_area unique (nombre_area);

CREATE EXTENSION IF NOT EXISTS unaccent;

CREATE UNIQUE INDEX idx_unique_nombre_area
    ON area (unaccent_immutable(lower(nombre_area)));


CREATE OR REPLACE FUNCTION public.unaccent_immutable(input text)
    RETURNS text AS $func$
BEGIN
    RETURN unaccent(input);
END;
$func$ LANGUAGE plpgsql IMMUTABLE;


