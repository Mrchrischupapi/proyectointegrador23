# Etapa 1: Compilación
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN cd "Proyecto integrador original/proyectointegrador/proyectointegrador" && mvn clean package -DskipTests
RUN cp "Proyecto integrador original/proyectointegrador/proyectointegrador/target/"*.jar app.jar

# Etapa 2: Ejecución limpia
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/app.jar app.jar
# Copia la carpeta wallet directamente a la ruta /app/wallet en la imagen final
COPY --from=build "/app/Proyecto integrador original/proyectointegrador/proyectointegrador/src/main/resources/wallet" /app/wallet
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
