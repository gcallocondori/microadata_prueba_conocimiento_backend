package com.pragma.powerup.microadata_prueba_conocimiento.application.port.out;

import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.MovimientoInventario;

public interface MovimientoRepositoryPort {
    MovimientoInventario save(MovimientoInventario movimientoInventario);
}