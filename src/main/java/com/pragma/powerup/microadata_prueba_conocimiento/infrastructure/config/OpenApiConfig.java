package com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI inventoryApi() {
        return new OpenAPI()
                .openapi("3.0.3")
                .info(new Info()
                        .title("Inventario QR API")
                        .version("0.1.0")
                        .description("API REST para ingreso y salida de productos usando QR"));
    }
}