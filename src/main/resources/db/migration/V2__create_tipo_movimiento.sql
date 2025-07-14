CREATE TABLE tipo_movimiento (
                                 id           SERIAL PRIMARY KEY,
                                 codigo       VARCHAR(10)    NOT NULL UNIQUE,
                                 descripcion  VARCHAR(50)    NOT NULL
);

INSERT INTO tipo_movimiento (codigo, descripcion)
VALUES ('IN', 'Ingreso'),
       ('OUT','Salida');
