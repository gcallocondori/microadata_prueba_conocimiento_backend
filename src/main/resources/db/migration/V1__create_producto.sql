CREATE TABLE producto (
                          id               SERIAL PRIMARY KEY,
                          codigo_qr        VARCHAR(100) UNIQUE NOT NULL,
                          descripcion      VARCHAR(255)    NOT NULL,
                          precio_compra    NUMERIC(10,2)   NOT NULL,
                          cantidad_actual  INTEGER         NOT NULL DEFAULT 0
);
