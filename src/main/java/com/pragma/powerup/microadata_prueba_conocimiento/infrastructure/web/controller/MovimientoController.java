package com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.web.controller;

import com.pragma.powerup.microadata_prueba_conocimiento.application.port.in.SalidaUseCase;
import com.pragma.powerup.microadata_prueba_conocimiento.application.dto.SalidaCommand;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.MovimientoInventario;
import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.web.dto.SalidaRequest;
import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.web.dto.MovimientoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class MovimientoController {

    private final SalidaUseCase salidaUseCase;

    @PostMapping("/salida")
    public ResponseEntity<MovimientoResponse> salida(@RequestBody SalidaRequest request) {
        MovimientoInventario mov = salidaUseCase.execute(
                new SalidaCommand(request.qrData(), request.precioVenta())
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