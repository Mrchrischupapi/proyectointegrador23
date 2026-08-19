FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -f "Proyecto integrador original/proyectointegrador/pom.xml"

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build ["/app/Proyecto integrador original/proyectointegrador/target/proyectointegrador-0.0.1-SNAPSHOT.jar", "app.jar"]
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
