package com.pragma.powerup.microadata_prueba_conocimiento.domain.exception;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String message) {
        super(message);
    }
}