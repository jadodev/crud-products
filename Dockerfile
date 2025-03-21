# Etapa 1: Construcción
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# Copiar archivos del proyecto y construir el JAR
COPY --chown=gradle:gradle . .
RUN gradle build -x test --no-daemon

# Etapa 2: Ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiar el JAR generado en la etapa anterior
COPY --from=builder /app/build/libs/*.jar app.jar

# Exponer el puerto en el que corre Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
