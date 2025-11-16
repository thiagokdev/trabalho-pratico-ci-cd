# Etapa 1: Construção (Build)
# Usamos uma imagem Maven oficial para compilar o projeto
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Execução (Run)
# Trocamos 'openjdk' por 'eclipse-temurin' que é a versão moderna e correta
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/cleanservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
