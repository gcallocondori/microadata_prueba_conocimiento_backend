package com.pragma.powerup.microadata_prueba_conocimiento.domain.model;

import lombok.*;
import java.math.BigDecimal;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.exception.StockInsuficienteException;

@Getter
@Setter
@NoArgsConstructor            // <-- constructor sin args
@AllArgsConstructor           // <-- constructor con TODOS los campos
public class Producto {

    private Long id;
    private String codigoQr;
    private String descripcion;
    private BigDecimal precioCompra;
    private int cantidadActual;

    /** Constructor de conveniencia para creación inicial */
    public Producto(String codigoQr, String descripcion, BigDecimal precioCompra) {
        this.codigoQr       = codigoQr;
        this.descripcion    = descripcion;
        this.precioCompra   = precioCompra;
        this.cantidadActual = 0;
    }

    public void incrementarStock(int cantidad) {
        this.cantidadActual += cantidad;
    }

    public void decrementarStock(int cantidad) {
        if (cantidad > this.cantidadActual) {
            throw new StockInsuficienteException(
                    "No hay suficiente stock para el producto " + codigoQr +
                            " (stock=" + cantidadActual + ", solicitado=" + cantidad + ")"
            );
        }
        this.cantidadActual -= cantidad;
    }
}