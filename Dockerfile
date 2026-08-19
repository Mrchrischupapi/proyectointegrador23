FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -f "Proyecto Integrador original/proyectointegrador/pom.xml"

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build ["/app/Proyecto Integrador original/proyectointegrador/target/", "/tmp/target/"]
RUN cp /tmp/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
