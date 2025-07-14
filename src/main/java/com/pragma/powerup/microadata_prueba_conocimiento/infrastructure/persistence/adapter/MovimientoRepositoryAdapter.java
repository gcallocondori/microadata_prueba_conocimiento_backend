package com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.adapter;

import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.MovimientoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.MovimientoInventario;
import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.entity.MovimientoInventarioEntity;
import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.mapper.DomainPersistenceMapper;
import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.repository.MovimientoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovimientoRepositoryAdapter implements MovimientoRepositoryPort {

    private final MovimientoJpaRepository repository;
    private final DomainPersistenceMapper mapper;

    @Override
    public MovimientoInventario save(MovimientoInventario movimiento) {
        MovimientoInventarioEntity entity = mapper.toEntity(movimiento);
        MovimientoInventarioEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }
}