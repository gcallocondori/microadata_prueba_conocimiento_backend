package com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.adapter;

import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.TipoMovimientoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.TipoMovimiento;
import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.mapper.DomainPersistenceMapper;
import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.repository.TipoMovimientoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TipoMovimientoRepositoryAdapter implements TipoMovimientoRepositoryPort {

    private final TipoMovimientoJpaRepository repository;
    private final DomainPersistenceMapper mapper;

    @Override
    public Optional<TipoMovimiento> findByCodigo(String codigo) {
        return repository.findByCodigo(codigo)
                .map(mapper::toDomain);
    }
}