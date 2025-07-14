package com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_qr", unique = true, nullable = false)
    private String codigoQr;

    @Column(nullable = false)
    private String descripcion;

    @Column(name = "precio_compra", nullable = false)
    private BigDecimal precioCompra;

    @Column(name = "cantidad_actual", nullable = false)
    private Integer cantidadActual;
}