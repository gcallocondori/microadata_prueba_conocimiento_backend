package com.pragma.powerup.microadata_prueba_conocimiento.application.port.in;

import com.pragma.powerup.microadata_prueba_conocimiento.application.dto.SalidaCommand;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.MovimientoInventario;

public interface SalidaUseCase {
    MovimientoInventario execute(SalidaCommand command);
}