#JDK
FROM eclipse-temurin:21-jdk-jammy
#Directorio
WORKDIR /app
#JAR compilado
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
#puerto
EXPOSE 8080
#
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
