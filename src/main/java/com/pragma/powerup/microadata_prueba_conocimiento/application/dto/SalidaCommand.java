package com.pragma.powerup.microadata_prueba_conocimiento.application.dto;

import java.math.BigDecimal;

public record SalidaCommand(
        String qrData,
        BigDecimal precioVenta
) { }