FROM maven:3.8.5-openjdk-17
WORKDIR /app
COPY . .
RUN POM_PATH=$(find . -maxdepth 3 -name pom.xml | head -n 1) && mvn clean package -DskipTests -f "$POM_PATH"
EXPOSE 8080
CMD ["sh", "-c", "java -jar $(find . -name '*.jar' ! -name '*sources.jar' | head -n 1)"]
