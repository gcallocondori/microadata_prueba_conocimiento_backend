package com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.adapter;

import com.pragma.powerup.microadata_prueba_conocimiento.application.port.out.ProductoRepositoryPort;
import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.Producto;
import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.entity.ProductoEntity;
import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.mapper.DomainPersistenceMapper;
import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.repository.ProductoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductoRepositoryAdapter implements ProductoRepositoryPort {

    private final ProductoJpaRepository repository;
    private final DomainPersistenceMapper mapper;

    @Override
    public Optional<Producto> findByCodigoQr(String codigoQr) {
        return repository.findByCodigoQr(codigoQr)
                .map(mapper::toDomain);
    }

    @Override
    public Producto save(Producto producto) {
        ProductoEntity entity = mapper.toEntity(producto);
        ProductoEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }
}