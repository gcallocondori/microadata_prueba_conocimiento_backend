package com.pragma.powerup.microadata_prueba_conocimiento.application.port.in;

import com.pragma.powerup.microadata_prueba_conocimiento.application.dto.IngresoCommand;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.MovimientoInventario;

public interface IngresoUseCase {
    MovimientoInventario execute(IngresoCommand command);
}