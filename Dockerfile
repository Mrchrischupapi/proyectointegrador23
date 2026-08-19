FROM maven:3.9.6-eclipse-temurin-21
WORKDIR /app
COPY . .
RUN cd "Proyecto integrador original/proyectointegrador/proyectointegrador" && mvn clean package -DskipTests
EXPOSE 8080
CMD ["sh", "-c", "JAR_PATH=$(find . -name '*.jar' ! -name '*sources.jar' | head -n 1) && java -jar \"$JAR_PATH\""]
