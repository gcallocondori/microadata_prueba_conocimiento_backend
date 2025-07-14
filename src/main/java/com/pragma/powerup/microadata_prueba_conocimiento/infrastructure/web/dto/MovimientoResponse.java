package com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.web.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record MovimientoResponse(
        Long movimientoId,
        String codigoQr,
        int cantidadRestante,
        BigDecimal precioVenta,
        OffsetDateTime fechaHora
) { }