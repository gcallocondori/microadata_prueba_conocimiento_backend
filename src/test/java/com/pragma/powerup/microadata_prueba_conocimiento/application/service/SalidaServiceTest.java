package com.pragma.powerup.microadata_prueba_conocimiento.application.service;

import com.pragma.powerup.microadata_prueba_conocimiento.application.dto.SalidaCommand;
import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.MovimientoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.ProductoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.TipoMovimientoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.exception.StockInsuficienteException;
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
class SalidaServiceTest {

    @Mock ProductoRepositoryPort productoRepo;
    @Mock TipoMovimientoRepositoryPort tipoRepo;
    @Mock MovimientoRepositoryPort movimientoRepo;

    @InjectMocks SalidaService service;

    private final String qrData = "XYZ999,OtherProduct,10.00";
    private final TipoMovimiento tipoOut = new TipoMovimiento(2L, "OUT", "Salida");

    @BeforeEach
    void setup() {
        lenient().when(tipoRepo.findByCodigo("OUT"))
                .thenReturn(Optional.of(tipoOut));
    }

    @Test
    void execute_decrementsStockAndCreatesMovement() {
        // producto con stock=3
        Producto existing = new Producto(7L, "XYZ999", "OtherProduct",
                new BigDecimal("10.00"), 3);
        when(productoRepo.findByCodigoQr("XYZ999"))
                .thenReturn(Optional.of(existing));

        // guardado reduce stock a 2
        Producto updated = new Producto(7L, "XYZ999", "OtherProduct",
                new BigDecimal("10.00"), 2);
        when(productoRepo.save(any(Producto.class))).thenReturn(updated);

        // movimiento OUT guardado
        MovimientoInventario savedMov = new MovimientoInventario(
                200L, updated, tipoOut, 1, new BigDecimal("15.00"), OffsetDateTime.now()
        );
        when(movimientoRepo.save(any(MovimientoInventario.class)))
                .thenReturn(savedMov);

        SalidaCommand cmd = new SalidaCommand(qrData, new BigDecimal("15.00"));
        MovimientoInventario result = service.execute(cmd);

        assertEquals(2, result.getProducto().getCantidadActual());
        assertEquals(new BigDecimal("15.00"), result.getPrecioVenta());
    }

    @Test
    void execute_throwsWhenProductoNotFoundOrStockInsuficiente() {
        // caso 1: producto no existe
        when(productoRepo.findByCodigoQr("XYZ999")).thenReturn(Optional.empty());
        assertThrows(StockInsuficienteException.class, () ->
                service.execute(new SalidaCommand(qrData, new BigDecimal("5.00")))
        );

        // caso 2: producto existe pero stock insuficiente
        Producto zeroStock = new Producto(7L, "XYZ999", "OtherProduct",
                new BigDecimal("10.00"), 0);
        when(productoRepo.findByCodigoQr("XYZ999"))
                .thenReturn(Optional.of(zeroStock));
        assertThrows(StockInsuficienteException.class, () ->
                service.execute(new SalidaCommand(qrData, new BigDecimal("5.00")))
        );
    }
}