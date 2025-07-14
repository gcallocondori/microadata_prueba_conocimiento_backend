package com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.mapper;

import com.pragma.powerup.microadata_prueba_conocimiento.domain.model.*;
import com.pragma.powerup.microadata_prueba_conocimiento.infrastructure.persistence.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DomainPersistenceMapper {

    // Entity → Domain
    Producto toDomain(ProductoEntity e);
    TipoMovimiento toDomain(TipoMovimientoEntity e);
    MovimientoInventario toDomain(MovimientoInventarioEntity e);

    // Domain → Entity
    ProductoEntity toEntity(Producto d);
    TipoMovimientoEntity toEntity(TipoMovimiento d);
    MovimientoInventarioEntity toEntity(MovimientoInventario d);
}