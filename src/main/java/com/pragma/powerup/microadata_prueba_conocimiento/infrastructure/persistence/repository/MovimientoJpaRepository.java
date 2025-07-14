package com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.repository;

import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.entity.MovimientoInventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoJpaRepository extends JpaRepository<MovimientoInventarioEntity, Long> { }