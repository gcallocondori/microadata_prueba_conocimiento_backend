CREATE TABLE movimiento_inventario (
                                       id                   SERIAL PRIMARY KEY,
                                       producto_id          INTEGER NOT NULL
                                           REFERENCES producto(id)
                                               ON UPDATE CASCADE
                                               ON DELETE RESTRICT,
                                       tipo_movimiento_id   INTEGER NOT NULL
                                           REFERENCES tipo_movimiento(id),
                                       cantidad             INTEGER NOT NULL DEFAULT 1,
                                       precio_venta         NUMERIC(10,2),
                                       fecha_hora           TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_mov_producto
    ON movimiento_inventario(producto_id);
