package com.pragma.powerup.microadata_prueba_conocimiento.application.service;

import com.pragma.powerup.microadata_prueba_conocimiento.application.dto.SalidaCommand;
import com.pragma.powerup.microadata_prueba_conocimiento.application.port.in.SalidaUseCase;
import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.MovimientoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.ProductoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.TipoMovimientoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.exception.StockInsuficienteException;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.MovimientoInventario;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.Producto;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.TipoMovimiento;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalidaService implements SalidaUseCase {

    private final ProductoRepositoryPort productoRepo;
    private final TipoMovimientoRepositoryPort tipoRepo;
    private final MovimientoRepositoryPort movimientoRepo;

    @Override
    public MovimientoInventario execute(SalidaCommand command) {
        // 1. Obtener producto por QR
        String codigo = command.qrData().split(",")[0];
        Producto producto = productoRepo.findByCodigoQr(codigo)
                .orElseThrow(() -> new StockInsuficienteException(
                        "Producto no existe o stock insuficiente: " + codigo));

        // 2. Disminuir stock
        producto.decrementarStock(1);
        productoRepo.save(producto);

        // 3. Registrar movimiento OUT
        TipoMovimiento tipo = tipoRepo.findByCodigo("OUT")
                .orElseThrow(() -> new IllegalStateException("Tipo OUT no encontrado"));
        MovimientoInventario mov = new MovimientoInventario(
                null,
                producto,
                tipo,
                1,
                command.precioVenta(),
                OffsetDateTime.now()
        );
        return movimientoRepo.save(mov);
    }
}