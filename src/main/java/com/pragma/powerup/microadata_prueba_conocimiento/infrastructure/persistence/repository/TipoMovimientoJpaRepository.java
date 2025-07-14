package com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.repository;

import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.entity.TipoMovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TipoMovimientoJpaRepository extends JpaRepository<TipoMovimientoEntity, Long> {
    Optional<TipoMovimientoEntity> findByCodigo(String codigo);
}