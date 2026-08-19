FROM maven:3.8.5-openjdk-17
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -f "Proyecto Integrador original/proyectointegrador/pom.xml"
EXPOSE 8080
CMD ["sh", "-c", "java -jar $(find . -name '*.jar' ! -name '*sources.jar' | head -n 1)"]
