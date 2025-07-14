package com.pragma.powerup.microadata_prueba_conocimiento.domain.model;

import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MovimientoInventario {
    private Long id;
    private Producto producto;
    private TipoMovimiento tipoMovimiento;
    private int cantidad;
    private BigDecimal precioVenta;
    private OffsetDateTime fechaHora;
}