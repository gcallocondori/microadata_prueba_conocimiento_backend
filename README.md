
# Microadata Prueba Conocimiento Backend

## Descripción
Este proyecto implementa el backend de gestión de inventario usando Java, Spring Boot y PostgreSQL, con Docker para orquestación.

## Tecnologías
- Java 21
- Spring Boot 3.5.3
- PostgreSQL 17.2
- Docker & Docker Compose
- Flyway (opcional)
- Lombok, MapStruct

## Requisitos
- JDK 21 instalado
- Gradle 8.x
- Docker & Docker Compose
- Git

## Estructura del Proyecto
```
/src
  /main
    /java... (código Java según capas hexagonal/clean)
    /resources
      application.properties
      application-prod.properties
      /db/migration (Flyway scripts)
/docker/db-init   (Scripts SQL para initdb)
Dockerfile
docker-compose.yml
README.md
```

## Configuración

### Variables de Entorno
- `SPRING_DATASOURCE_URL` (por defecto `jdbc:postgresql://localhost:5432/inventario`)
- `SPRING_DATASOURCE_USERNAME` (por defecto `postgres`)
- `SPRING_DATASOURCE_PASSWORD` (por defecto `postgres`)
- `SERVER_PORT` (por defecto `8080`)
- `SPRING_PROFILES_ACTIVE` (`prod` para producción)

### application.properties (desarrollo)
```properties
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### application-prod.properties (producción)
```properties
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
```

## Inicialización de la Base de Datos

### Con Scripts SQL (recomendado)
Los scripts en `docker/db-init/` se ejecutan automáticamente al inicializar PostgreSQL:
- `V1__create_tables_and_inserts.sql`

### Flyway (opcional)
Si prefieres versionar con Flyway, coloca tus scripts en `src/main/resources/db/migration`.

## Ejecución Local

1. Clona el repositorio:
   ```bash
   git clone <repo-url>
   cd microadata_prueba_conocimiento
   ```
2. Construye el JAR:
   ```bash
   ./gradlew clean bootJar
   ```
3. Levanta servicios con Docker Compose:
   ```bash
   docker-compose up --build -d
   ```
4. Verifica contenedores:
   ```bash
   docker-compose ps
   ```
5. Prueba endpoint:
   ```bash
   curl -X POST http://localhost:8080/api/productos/ingreso \
     -H "Content-Type: application/json" \
     -d '{"qrData":"ABC123,Demo,10.00"}'
   ```

## Despliegue en Servidor Virtual

1. **Preparar servidor**
   ```bash
   sudo apt update
   sudo apt install openjdk-21-jdk docker.io docker-compose git -y
   sudo usermod -aG docker $USER
   ```
2. **Clonar y configurar**:
   ```bash
   git clone <repo-url>
   cd microadata_prueba_conocimiento
   export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/inventario
   export SPRING_DATASOURCE_USERNAME=postgres
   export SPRING_DATASOURCE_PASSWORD=postgres
   ```
3. **Construir y levantar**:
   ```bash
   ./gradlew clean bootJar
   docker-compose up --build -d
   ```
4. **Logs & estado**:
   ```bash
   docker-compose logs -f app
   docker-compose ps
   ```

## Documentación de API
Swagger UI en:
```
http://<host>:8080/swagger-ui/index.html
```

## Pruebas
- **Unitarias**: Services con Mockito
