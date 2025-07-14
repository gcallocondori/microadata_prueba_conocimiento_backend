package com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.web.controller;

import com.pragma.powerup.microadata_prueba_conocimiento.application.port.in.IngresoUseCase;
import com.pragma.powerup.microadata_prueba_conocimiento.application.dto.IngresoCommand;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.MovimientoInventario;
import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.web.dto.QRRequest;
import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.web.dto.MovimientoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final IngresoUseCase ingresoUseCase;

    @PostMapping("/ingreso")
    public ResponseEntity<MovimientoResponse> ingreso(@RequestBody QRRequest request) {
        MovimientoInventario mov = ingresoUseCase.execute(
                new IngresoCommand(request.qrData())
        );
        return ResponseEntity.ok(toResponse(mov));
    }

    private MovimientoResponse toResponse(MovimientoInventario mov) {
        return new MovimientoResponse(
                mov.getId(),
                mov.getProducto().getCodigoQr(),
                mov.getProducto().getCantidadActual(),
                mov.getPrecioVenta(),
                mov.getFechaHora()
        );
    }
}