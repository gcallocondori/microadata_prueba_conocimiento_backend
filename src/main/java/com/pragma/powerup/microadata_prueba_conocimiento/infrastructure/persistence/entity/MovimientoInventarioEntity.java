package com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.*;

@Entity
@Table(
        name = "movimiento_inventario",
        indexes = @Index(name = "idx_mov_producto", columnList = "producto_id")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoInventarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private ProductoEntity producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_movimiento_id", nullable = false)
    private TipoMovimientoEntity tipoMovimiento;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_venta")
    private BigDecimal precioVenta;

    @Column(name = "fecha_hora", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    private OffsetDateTime fechaHora;
}