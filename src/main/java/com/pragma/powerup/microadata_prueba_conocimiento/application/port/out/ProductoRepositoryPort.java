package com.pragma.powerup.microadata_prueba_conocimiento.application.port.out;

import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.Producto;

import java.util.Optional;

public interface ProductoRepositoryPort {
    Optional<Producto> findByCodigoQr(String codigoQr);
    Producto save(Producto producto);
}