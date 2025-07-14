package com.pragma.powerup.microadata_prueba_conocimiento.application.service;

import com.pragma.powerup.microadata_prueba_conocimiento.application.dto.IngresoCommand;
import com.pragma.powerup.microadata_prueba_conocimiento.application.port.in.IngresoUseCase;
import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.MovimientoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.ProductoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.TipoMovimientoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.MovimientoInventario;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.Producto;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.TipoMovimiento;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngresoService implements IngresoUseCase {

    private final ProductoRepositoryPort productoRepo;
    private final TipoMovimientoRepositoryPort tipoRepo;
    private final MovimientoRepositoryPort movimientoRepo;

    @Override
    public MovimientoInventario execute(IngresoCommand command) {
        // 1. Parsear datos del QR (formato: codigo,descripcion,precioCompra)
        String[] parts = command.qrData().split(",");
        String codigo   = parts[0];
        String descripcion = parts[1];
        BigDecimal precioCompra = new BigDecimal(parts[2]);

        // 2. Obtener o crear producto
        Producto producto = productoRepo.findByCodigoQr(codigo)
                .map(p -> {
                    p.incrementarStock(1);
                    return p;
                })
                .orElseGet(() -> {
                    Producto nuevo = new Producto(codigo, descripcion, precioCompra);
                    nuevo.incrementarStock(1);
                    return nuevo;
                });
        producto = productoRepo.save(producto);

        // 3. Registrar movimiento IN
        TipoMovimiento tipo = tipoRepo.findByCodigo("IN")
                .orElseThrow(() -> new IllegalStateException("Tipo IN no encontrado"));
        MovimientoInventario mov = new MovimientoInventario(
                null,
                producto,
                tipo,
                1,
                null,
                OffsetDateTime.now()
        );
        return movimientoRepo.save(mov);
    }
}