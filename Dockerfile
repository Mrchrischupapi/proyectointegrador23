FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -Dmaven.test.skip=true --fail-never -f "$(find . -name pom.xml | head -n 1)"

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app /app
RUN cp "$(find /app -name "*.jar" ! -name "*sources.jar" | head -n 1)" app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
