package com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.web.dto;

import java.math.BigDecimal;

public record SalidaRequest(String qrData, BigDecimal precioVenta) { }