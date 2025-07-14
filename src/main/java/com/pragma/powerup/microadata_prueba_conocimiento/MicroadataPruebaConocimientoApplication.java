package com.pragma.powerup.microadata_prueba_conocimiento;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;

@OpenAPIDefinition(
        info = @Info(
                title       = "Inventario QR API",
                version     = "0.1.0",
                description = "API REST para ingreso y salida de productos"
        )
)
@SpringBootApplication(
        exclude = FlywayAutoConfiguration.class
)
public class MicroadataPruebaConocimientoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroadataPruebaConocimientoApplication.class, args);
    }

}
