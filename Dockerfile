# Etapa 1: Construcción (Build)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Copiamos el archivo de configuración de dependencias
COPY pom.xml .
# Copiamos el código fuente
COPY src ./src
# Compilamos el proyecto omitiendo las pruebas para que sea más rápido
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copiamos elPara subir tu proyecto a Render utilizando Docker con Java 17, la mejor opción es usar un **Dockerfile multi-etapa**. Esto hace que Render compile tu código usando Maven y luego solo guarde el archivo ejecutable, haciendo que tu aplicación sea mucho más rápida y ligera.

Aquí tienes el proceso completo paso a paso:

**1. Crea el Dockerfile**
En IntelliJ IDEA, haz clic derecho sobre la carpeta principal de tu proyecto (`hm`), selecciona **New > File** y llámalo exactamente **`Dockerfile`** (sin ninguna extensión, con la 'D' mayúscula). Pega el siguiente código:

```dockerfile
# Etapa 1: Construcción (Build)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compila el proyecto y omite las pruebas para que sea más rápido
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copia el archivo .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar
# Exponemos el puerto 8081 que configuraste en tu application.properties
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]