package com.pragma.powerup.microadata_prueba_conocimiento.application.service;

import com.pragma.powerup.microadata_prueba_conocimiento.application.dto.IngresoCommand;
import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.MovimientoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.ProductoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.TipoMovimientoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.MovimientoInventario;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.Producto;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.TipoMovimiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngresoServiceTest {

    @Mock ProductoRepositoryPort productoRepo;
    @Mock TipoMovimientoRepositoryPort tipoRepo;
    @Mock MovimientoRepositoryPort movimientoRepo;

    @InjectMocks IngresoService service;

    private final String qrData = "ABC123,TestProduct,42.50";
    private final TipoMovimiento tipoIn = new TipoMovimiento(1L, "IN", "Ingreso");

    @BeforeEach
    void setup() {
        when(tipoRepo.findByCodigo("IN")).thenReturn(Optional.of(tipoIn));
    }

    @Test
    void execute_createsNewProductoAndMovement() {
        // producto no existe
        when(productoRepo.findByCodigoQr("ABC123")).thenReturn(Optional.empty());

        // al guardar el producto, simulamos que le asigna id=10 y stock=1
        Producto savedProd = new Producto(10L, "ABC123", "TestProduct",
                new BigDecimal("42.50"), 1);
        when(productoRepo.save(any(Producto.class))).thenReturn(savedProd);

        // simulamos el movimiento guardado
        MovimientoInventario savedMov = new MovimientoInventario(
                100L, savedProd, tipoIn, 1, null, OffsetDateTime.now()
        );
        when(movimientoRepo.save(any(MovimientoInventario.class)))
                .thenReturn(savedMov);

        MovimientoInventario result = service.execute(new IngresoCommand(qrData));
        assertEquals(100L, result.getId());
        assertEquals("ABC123", result.getProducto().getCodigoQr());
        assertEquals(1, result.getProducto().getCantidadActual());
        verify(productoRepo).save(argThat(p -> p.getCodigoQr().equals("ABC123")
                && p.getCantidadActual()==1));
        verify(movimientoRepo).save(any());
    }

    @Test
    void execute_incrementaStockSiProductoExiste() {
        // producto existe con stock 5
        Producto existing = new Producto(5L, "ABC123", "TestProduct",
                new BigDecimal("42.50"), 5);
        when(productoRepo.findByCodigoQr("ABC123"))
                .thenReturn(Optional.of(existing));

        // guardado incrementa stock a 6
        Producto updated = new Producto(5L, "ABC123", "TestProduct",
                new BigDecimal("42.50"), 6);
        when(productoRepo.save(any(Producto.class))).thenReturn(updated);

        MovimientoInventario savedMov = new MovimientoInventario(
                101L, updated, tipoIn, 1, null, OffsetDateTime.now()
        );
        when(movimientoRepo.save(any(MovimientoInventario.class)))
                .thenReturn(savedMov);

        MovimientoInventario result = service.execute(new IngresoCommand(qrData));
        assertEquals(6, result.getProducto().getCantidadActual());
        verify(productoRepo).save(argThat(p -> p.getCantidadActual()==6));
    }
}