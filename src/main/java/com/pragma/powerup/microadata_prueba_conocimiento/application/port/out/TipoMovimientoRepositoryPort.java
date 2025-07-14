package com.pragma.powerup.microadata_prueba_conocimiento.application.port.out;

import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.TipoMovimiento;

import java.util.Optional;

public interface TipoMovimientoRepositoryPort {
    Optional<TipoMovimiento> findByCodigo(String codigo);
}