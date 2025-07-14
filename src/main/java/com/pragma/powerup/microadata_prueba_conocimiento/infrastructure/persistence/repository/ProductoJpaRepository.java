package com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.repository;

import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, Long> {
    Optional<ProductoEntity> findByCodigoQr(String codigoQr);
}