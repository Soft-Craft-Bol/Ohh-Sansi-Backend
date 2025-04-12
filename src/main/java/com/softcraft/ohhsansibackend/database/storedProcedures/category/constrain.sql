alter table categorias
    add constraint unique_nombre_categoria unique (nombre_categoria);

select * from categorias;


ALTER TABLE nivel_escolar_categorias
ADD CONSTRAINT fk_nivel_es_r37_categori
FOREIGN KEY (id_area, id_categoria)
REFERENCES categorias (id_area, id_categoria)
ON DELETE CASCADE;


ALTER TABLE nivel_escolar_categorias
    DROP CONSTRAINT fk_nivel_es_r37_categori;